package com.v.webview_module.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 主进程命令服务
 */
public class MainProcessCommandService extends Service {
    public final String TAG = "MainProcessCommandService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return MainProcessCommandManager.getInstance();
    }
}
