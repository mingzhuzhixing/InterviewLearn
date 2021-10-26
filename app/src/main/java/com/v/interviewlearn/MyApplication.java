package com.v.interviewlearn;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.youshu.network_module.HttpHelper;
import com.youshu.network_module.xutils.XutilsProcessor;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化切换网络框架
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XutilsProcessor(this));
    }
}
