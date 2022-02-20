package com.v.tinker_module;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.v.tinker_module.utils.FixDexUtils;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        FixDexUtils.loadFixedDex(this);
    }
}
