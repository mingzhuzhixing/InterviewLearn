package com.v.hook.start_no_register_activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookActivityUtil {
    public static final String EXTRA_ORIGIN_INTENT = "EXTRA_ORIGIN_INTENT";
    private static final String TAG = "HookActivityUtil";
    private Context context;
    private Class clazz;

    public HookActivityUtil(Context context, Class clazz) {
        this.context = context;
        this.clazz = clazz;
    }


    /**
     * 这个方法可以跳过activity启动对AndroidManifest文件的检查，成功将检查的过程替换成了一个已注册的傀儡Activity，startActivity过程成功
     * 但是这一步进行完只能保证程序不会奔溃，但是启动的activity确实那个傀儡，而不是我们的目标activity
     * 那么下一步就是在launchActivity的时候重新将目标Activity也就是没有注册的那个Activity启动起来，这个过程在hookLaunchActivity方法中
     * ActivityManagerNative
     */
    public void hookStartActivity() throws Exception {
        Class aClass = Class.forName("android.app.IActivityManager");

        //动态代理传入需要被代理的接口，动态代理以接口为切入点，回调中传入的是这个接口实现类的一个实例，这两个参数是进行动态代理的前提
        //从源码中可以找到这个实现类的实例是Singleton中的mInstance对象，所以接下来我们需要通过一系列手段获取到这个实例

        //获取ActivityManagerNative中的gDefault

        Class<?> amClass = Class.forName("android.app.ActivityManagerNative");
        Field gDefault = amClass.getDeclaredField("gDefault");
        gDefault.setAccessible(true);
        Object gDefaultObj = gDefault.get(null);//静态传null

        Class<?> singleTonClass = Class.forName("android.util.Singleton");
        Field mInstance = singleTonClass.getDeclaredField("mInstance");
        mInstance.setAccessible(true);

        Object iamInstance = mInstance.get(gDefaultObj);

        Object o = Proxy.newProxyInstance(HookActivityUtil.class.getClassLoader(), new Class[]{aClass}, new StartActivityInvocationHandler(iamInstance));

        //反射将mInstance实例替换为我们得到的代理对象
        mInstance.set(gDefaultObj, o);
    }


    public void hookLaunchActivity() throws Exception {
        //获取ActivityThread实例
        Class aClass = Class.forName("android.app.ActivityThread");
        //ActivityThread类中有一个sCurrentActivityThread变量就是他的实例
        Field aClassDeclaredField = aClass.getDeclaredField("sCurrentActivityThread");
        aClassDeclaredField.setAccessible(true);
        Object sCurrentActivityThread = aClassDeclaredField.get(null);


        //获取ActivityThread中的Handler mH

        Field mhField = aClass.getDeclaredField("mH");
        mhField.setAccessible(true);
        Object mHandler = mhField.get(sCurrentActivityThread);

        Class<?> handlerClass = Class.forName("android.os.Handler");


        //Handler的handleMessage方法是通过Handler内部的mCallback调用的，mCallback也就是通常传入的那个回调
        //我们拦截到handleMessage这个方法，在这里将假的intent重新替换回来
        Field mCallback = handlerClass.getDeclaredField("mCallback");
        mCallback.setAccessible(true);
        mCallback.set(mHandler, new HandlerCallBack());
    }


    class HandlerCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            //没发一个消息都会走一次这个方法
            //ActivityThread中的内部类Handler中的LaunchActivity常量 = 100
            //public static final int LAUNCH_ACTIVITY = 100;
            if (msg.what == 100) {
                handleLaunchActivity(msg);
            }
            return false;
        }
    }


    private void handleLaunchActivity(Message msg) {
        //看源码可以知道这个Object就是类ActivityClientRecord
        //final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
        Object record = msg.obj;
        //从中获取intent
        try {
            Field intentField = record.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent safeIntent = (Intent) intentField.get(record);
            //从这个intent中获取我们绑定的原本的intent
            Intent originIntent = safeIntent.getParcelableExtra(EXTRA_ORIGIN_INTENT);
            //将傀儡intent重新替换为原来的intent，打开我们需要的未注册的activity
            if (originIntent != null) {
                intentField.set(record, originIntent);
            }

            /**
             * 程序写到这里还是不完善的，目前只在被启动的activity继承自Activity的时候有效，如果继承自
             * AppCompatActivity就会报错，
             *
             * Caused by: java.lang.IllegalArgumentException: android.content.pm.PackageManager$NameNotFoundException: ComponentInfo{com.app.rzm/com.app.rzm.test.TestHookUnRegisteredActivity}
             * at android.support.v4.app.NavUtils.getParentActivityName(NavUtils.java:285)
             * at android.support.v7.app.AppCompatDelegateImplV9.onCreate(AppCompatDelegateImplV9.java:158)
             * at android.support.v7.app.AppCompatDelegateImplV14.onCreate(AppCompatDelegateImplV14.java:58)
             * at android.support.v7.app.AppCompatActivity.onCreate(AppCompatActivity.java:72)
             * at com.app.rzm.test.TestHookUnRegisteredActivity.onCreate(TestHookUnRegisteredActivity.java:12)
             * at android.app.Activity.performCreate(Activity.java:6366)
             * at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1126)
             * at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2661)
             * at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2779)
             * at android.app.ActivityThread.-wrap11(ActivityThread.java)
             * at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1593)
             * at android.os.Handler.dispatchMessage(Handler.java:111)
             * at android.os.Looper.loop(Looper.java:207)
             * at android.app.ActivityThread.main(ActivityThread.java:5979)
             * at java.lang.reflect.Method.invoke(Native Method)
             * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:939)
             * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:800)
             *
             * 找到AppCompatDelegateImplV9 onCreate方法这里有一个NavUtils.getParentActivityName((Activity) mOriginalWindowCallback)
             *
             * 找到源码中的位置在NavUtils方法中
             *
             * Return the fully qualified class name of sourceActivity's parent activity as specified by
             * a {@link #PARENT_ACTIVITY} &lt;meta-data&gt; element within the activity element in
             * the application's manifest.
             *
             * @param sourceActivity Activity to fetch a parent class name for
             * @return The fully qualified class name of sourceActivity's parent activity or null if
             *         it was not specified
             *
             * @Nullable
             *   public static String getParentActivityName(Activity sourceActivity) {
             *       try {
             *           return getParentActivityName(sourceActivity, sourceActivity.getComponentName());
             *       } catch (PackageManager.NameNotFoundException e) {
             *           // Component name of supplied activity does not exist...?
             *           throw new IllegalArgumentException(e);
             *       }
             *   }
             * Return the fully qualified class name of a source activity's parent activity as specified by
             * a {@link #PARENT_ACTIVITY} &lt;meta-data&gt; element within the activity element in
             * the application's manifest. The source activity is provided by componentName.
             *
             * @param context Context for looking up the activity component for the source activity
             * @param componentName ComponentName for the source Activity
             * @return The fully qualified class name of sourceActivity's parent activity or null if
             *         it was not specified
             *
             *   @Nullable
             *  public static String getParentActivityName(Context context, ComponentName componentName)
             *   throws PackageManager.NameNotFoundException {
             *       PackageManager pm = context.getPackageManager();
             *       ActivityInfo info = pm.getActivityInfo(componentName, PackageManager.GET_META_DATA);
             *       String parentActivity = IMPL.getParentActivityName(context, info);
             *       return parentActivity;
             *   }
             *
             *   可以看到继承自AppCompatActivity的话pm.getActivityInfo再次执行了，这个方法最初在startActivity的时候
             *   已经调用了一次，从存储Activity的集合中根据全类名查找这个Activity，如果找不到抛出Activity没有在AndroidManifest
             *   中注册的异常信息，这个操作其实和前边是一样的，会再次调用PackageManagerService的getActivityInfo方法，因为
             *   没有注册导致集合中没有这个activity再次出错
             *
             *   PackageManager pm = context.getPackageManager();
             *   错误就发生在这里
             *   ActivityInfo info = pm.getActivityInfo(componentName, PackageManager.GET_META_DATA);
             */
            //错误发生在getActivityInfo方法中，所以我们可以再次进行动态代理在这个方法执行的时候继续把傀儡扔过去让他
            //取获取activity info， 所以就要对IPackageManger进行代理，并且获取到它的实现类的实例，这里通过ActivityThread
            //中的getPackageManager方法去获取

            // 兼容AppCompatActivity报错问题
            Class<?> forName = Class.forName("android.app.ActivityThread");
            Field field = forName.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object activityThread = field.get(null);
            // 我自己执行一次那么就会创建PackageManager，系统再获取的时候就是下面的iPackageManager
            Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
            Object iPackageManager = getPackageManager.invoke(activityThread);

            PackageManagerHandler handler = new PackageManagerHandler(iPackageManager);
            Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{iPackageManagerIntercept}, handler);

            // 获取 sPackageManager 属性
            Field iPackageManagerField = activityThread.getClass().getDeclaredField("sPackageManager");
            iPackageManagerField.setAccessible(true);
            iPackageManagerField.set(activityThread, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class PackageManagerHandler implements InvocationHandler {
        private Object mActivityManagerObject;

        public PackageManagerHandler(Object iActivityManagerObject) {
            this.mActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Log.e("TAG", "methodName = " + method.getName());
            if (method.getName().startsWith("getActivityInfo")) {
                ComponentName componentName = new ComponentName(context, clazz);
                //将getActivityInfo方法中的第一个参数componentName替换为一个可以监测到的也就是我们上边用过的那个
                args[0] = componentName;
            }
            return method.invoke(mActivityManagerObject, args);
        }
    }


    class StartActivityInvocationHandler implements InvocationHandler {

        private final Object object;

        public StartActivityInvocationHandler(Object instance) {
            this.object = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LogUtils.d(TAG, "method --> " + method.getName());
            if ("startActivity".equals(method.getName())) {
                //获取原来的intent(IActivityManager中startActivity方法第三个参数就是intent)
                Intent originIntent = (Intent) args[2];
                //创建一个安全的intent(这个intent中包含一个在本地进行了注册的Activity作为一个临时替换)
                Intent safeIntent = new Intent(context, clazz);
                //将这个intent替换掉原来的intent
                args[2] = safeIntent;
                //将原来的intent绑定到这个intent中待launch activity的时候使用
                safeIntent.putExtra(EXTRA_ORIGIN_INTENT, originIntent);
            }
            return method.invoke(object, args);
        }
    }

}
