package com.v.network_architecture;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, Map<String, Object> params, ICallback callback);

}
