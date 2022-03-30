package com.v.module_utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;

import java.io.Closeable;

/**
 * ClassName: Utilities
 * Description:
 *
 * @date 2021/10/20 11:18 上午
 */
public class Utilities {
    private static Context mApplicationContext;
    private static Resources sResources = null;

    static String sVersionName = null;

    public static void initContext(Application context) {
        mApplicationContext = context;
    }

    public static Context getApplicationContext() {
        if (mApplicationContext == null) {
            throw new RuntimeException("没有初始化");
        } else {
            return mApplicationContext;
        }
    }

    public static Resources getResources() {
        if (sResources == null) {
            sResources = mApplicationContext.getResources();
        }
        return sResources;
    }

    public static void silentlyClose(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(sVersionName)) {
            try {
                sVersionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return sVersionName;
    }
}
