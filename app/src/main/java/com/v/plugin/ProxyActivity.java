package com.v.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.v.paystandard.PayInterface;

/**
 * 替身acitivity 用于满天过海
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-06 15:26
 */
public class ProxyActivity extends Activity {

    //com.v.taopiaopiao.MainActivity
    private String className;
    PayInterface payInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //执行插件apk中的activity的加载


        //拿到需要启动的第三方插件的activity的名字
        className = getIntent().getStringExtra("className");
        try {
            //得到第三方插件的activity的类对象
            Class aClass=PluginManager.getInstance().getDexClassLoader().loadClass(className);

            Object newInstance=aClass.newInstance();
            //判断这个acitivity是否是PayInterface 标准接口的实现
            if(newInstance instanceof PayInterface){
                //面向接口
                payInterface=(PayInterface)newInstance;

                //将代理activity的实力传递鬼第三方activity（上下文）
                payInterface.attach(this);


                Bundle bundle=new Bundle();
                //调用第三方插件apk中的OnCreate()方法
                payInterface.onCreate(bundle);
            }


//            Class activityClass=getClassLoader().loadClass(className);
//            Constructor constructor=activityClass.getConstructor(new Class[]{});
//            Object instance=constructor.newInstance(new Object[]{});

            //标准
//            payInterface= (PayInterface) instance;
//            payInterface.attach(this);

            //传递参数
//            Bundle bundle=new Bundle();
//            payInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        //接受 插件 传过来的 activity 全类名
        String className1=intent.getStringExtra("className");
        Intent intent1=new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",className1);
        super.startActivity(intent1);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        payInterface.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        payInterface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        payInterface.onResme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payInterface.onDestory();
    }
}
