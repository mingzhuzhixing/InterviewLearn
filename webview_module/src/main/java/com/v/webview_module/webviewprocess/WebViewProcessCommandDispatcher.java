package com.v.webview_module.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.v.base_module.BaseApplication;
import com.v.webview_module.IWebviewProcessToMainProcessInterface;
import com.v.webview_module.mainprocess.MainProcessCommandService;

/**
 * webview进程命令分发器
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private final String TAG = "WebViewProcessCommandDispatcher";
    private static WebViewProcessCommandDispatcher sInstance;
    private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    /**
     * 初始化aidl连接
     */
    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        boolean bindService = BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "initAidlConnection() bindService:" + bindService);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(TAG, "onServiceConnected()");
        iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG, "onServiceDisconnected()");
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        Log.i(TAG, "onBindingDied()");
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params) {
        try {
            Log.i(TAG, "executeCommand() commandName:" + commandName + " params:" + params);
            if (iWebviewProcessToMainProcessInterface != null) {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
