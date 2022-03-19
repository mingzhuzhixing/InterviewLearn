package com.v.interviewlearn.commands;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.auto.service.AutoService;
import com.v.module_base.BaseApplication;
import com.v.webview_module.command.Command;

/**
 * 打开页面命令
 */
@AutoService(Command.class)
public class ShowToastCommand implements Command {
    @Override
    public String commandName() {
        return "showToast";
    }

    @Override
    public void execute(final JSONObject object) {
        try {
            if (object == null || !object.containsKey("message")) {
                return;
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseApplication.sApplication, object.getString("message"), Toast.LENGTH_SHORT).show();
                }
            });
//            ThreadPool.runOnUi(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(BaseApplication.sApplication, object.getString("message"), Toast.LENGTH_SHORT).show();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
