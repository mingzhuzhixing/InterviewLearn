package com.v.base_module;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.kingja.loadsir.core.LoadSir;
import com.v.base_module.callback.CustomCallback;
import com.v.base_module.callback.EmptyCallback;
import com.v.base_module.callback.ErrorCallback;
import com.v.base_module.callback.LoadingCallback;
import com.v.base_module.callback.TimeoutCallback;
import com.v.thread_module.thread.ThreadPool;

public class BaseApplication extends Application {

    public static BaseApplication sApplication;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        //初始化线程
        ThreadPool.startup();

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
