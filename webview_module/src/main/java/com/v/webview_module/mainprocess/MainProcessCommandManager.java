package com.v.webview_module.mainprocess;

import android.os.RemoteException;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.v.webview_module.IWebviewProcessToMainProcessInterface;
import com.v.webview_module.command.Command;

import java.util.HashMap;
import java.util.ServiceLoader;

public class MainProcessCommandManager extends IWebviewProcessToMainProcessInterface.Stub {
    private static final String TAG = "MainProcessCommandManager";
    private static MainProcessCommandManager sInstance;

    private final HashMap<String, Command> commandMap = new HashMap<>();

    public static MainProcessCommandManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandManager.class) {
                sInstance = new MainProcessCommandManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            commandMap.put(command.commandName(), command);
        }
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) throws RemoteException {
        Log.i(TAG, "handleWebCommand() commandName:" + commandName + " jsonParams:" + jsonParams);
        try {
            Command command = commandMap.get(commandName);
            if (command != null) {
                command.execute(JSON.parseObject(jsonParams));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
