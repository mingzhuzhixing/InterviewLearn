package com.v.webview_module.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

import com.v.base_module.BaseApplication;
import com.v.webview_module.IWebviewProcessToMainProcessInterface;

public class MainProcessCommandManager extends IWebviewProcessToMainProcessInterface.Stub {
    private static final String TAG = "MainProcessCommandManager";

    private static MainProcessCommandManager sInstance;

    public static MainProcessCommandManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandManager.class) {
                sInstance = new MainProcessCommandManager();
            }
        }
        return sInstance;
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) throws RemoteException {
        Log.i(TAG, "handleWebCommand() commandName:" + commandName + " jsonParams:" + jsonParams);
        executeCommand(commandName, jsonParams);
    }

    private void executeCommand(String commandName, String params) {
        if ("openPage".equals(commandName)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, params));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
