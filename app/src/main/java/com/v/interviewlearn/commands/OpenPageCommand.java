package com.v.interviewlearn.commands;

import android.content.ComponentName;
import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.google.auto.service.AutoService;
import com.v.module_base.BaseApplication;
import com.v.webview_module.command.Command;

/**
 * 打开页面命令
 */
@AutoService(Command.class)
public class OpenPageCommand implements Command {
    @Override
    public String commandName() {
        return "openPage";
    }

    @Override
    public void execute(JSONObject object) {
        try {
            if (object == null || !object.containsKey("target_class")) {
                return;
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, object.getString("target_class")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
