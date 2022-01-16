package com.v.webview_module.command;
import com.alibaba.fastjson.JSONObject;

/**
 * 命令接口
 */
public interface Command {
    String commandName();

    void execute(JSONObject object);
}
