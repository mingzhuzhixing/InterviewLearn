package com.v.ioc;

import android.view.View;

import com.v.ioc.annotation.ContentView;
import com.v.ioc.annotation.EventBase;
import com.v.ioc.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这就是第三方
 */
public class InjectUtils {

    public static void inject(Object context) {
        //注入的功能就在这里完成
        injectLayout(context);

        injectView(context);

        injectClick(context);
    }

    /**
     * 布局的注入
     */
    private static void injectLayout(Object context) {
        int layoutId = 0;
        Class<?> clazz = context.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            layoutId = contentView.value();

            //反射去执行setContentView(id)
            try {
                Method method = context.getClass().getMethod("setContentView", int.class);
                method.invoke(context, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 控件注入
     */
    private static void injectView(Object context) {
        Class<?> clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                //如果是写了注解的，需要进行注入
                int viewId = injectView.value();
                try {
                    //反射执行findViewById
                    Method method = context.getClass().getMethod("findViewById", int.class);
                    View view = (View) method.invoke(context, viewId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectClick(Object context) {
        Class<?> clazz = context.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method :methods){
            //具备通用性
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation:annotations){
                //得到onClick上一级的eventBase
                Class<? extends Annotation> annotationClass = annotation.annotationType();
                EventBase eventBase = annotationClass.getAnnotation(EventBase.class);
                //如果没有eventBase,则表示当前方法不是一个事件处理程序
                if(eventBase==null){
                    continue;
                }else {
                    //否则就是时间处理回调的函数
                    //是的就是开始得到相关的事件要素

                    //订阅关系
                    String listenerSetter=eventBase.listenerSetter();

                    //事件
                    Class<?> listenerType= eventBase.listenerType();

                    //事件处理程序
                    String callbackMethod=eventBase.callbackMetod();

                    Method valueMethod=null;

                    try {
                        //反射得到id,再根据id好得到对应的view
                        valueMethod=annotationClass.getDeclaredMethod("value");
                        int[] viewId= (int[]) valueMethod.invoke(annotation);

                        //对每一ID都需要事件绑定
                        for (int id:viewId){
                            //找到id对应的对象
                            Method findViewById=clazz.getMethod("findViewById",int.class);
                            View view= (View) findViewById.invoke(context,id);
                            if(view==null){
                                continue;
                            }
                            //得到ID对应的VIEW后
                            //开始在这个view上执行监听（使用代理，去View.OnClickListener()代理这个接口）
                            //需要执行activity 上执行onClick()
                            ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler(context, method);
                            //去View.OnClickListener()==listenerType 代理这个接口
                            Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                    new Class[]{listenerType}, invocationHandler);
                            //执行方法
                            Method onClickMethod = view.getClass().getMethod(listenerSetter, listenerType);
                            onClickMethod.invoke(view,proxy);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
