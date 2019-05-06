package com.v.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.v.paystandard.PayInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Class description here
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

        className = getIntent().getStringExtra("className");
        try {
            Class activityClass=getClassLoader().loadClass(className);
            Constructor constructor=activityClass.getConstructor(new Class[]{});
            Object instance=constructor.newInstance(new Object[]{});

            //标准
            payInterface= (PayInterface) instance;
            payInterface.attach(this);

            //传递参数
            Bundle bundle=new Bundle();
            payInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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
