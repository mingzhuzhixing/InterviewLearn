package com.v.designmode;

import android.app.Application;

import com.v.network_architecture.HttpHelper;
import com.v.network_architecture.xutils.XutilsProcessor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XutilsProcessor(this));
    }
}
