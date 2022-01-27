package com.v.webview_module.bean;

import com.alibaba.fastjson.JSONObject;

public class JsParamBean {
    //{"name":"showToast","param":{"message":"this is a from html"}}
    private String name;
    private JSONObject param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getParam() {
        return param;
    }

    public void setParam(JSONObject param) {
        this.param = param;
    }
}
