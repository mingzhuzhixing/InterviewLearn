package com.youshu.network_module.listener;

import com.youshu.network_module.listener.ICallback;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, Map<String, Object> params, ICallback callback);

}
