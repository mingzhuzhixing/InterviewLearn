package com.youshu.network_module.listener;

public interface ICallback {

    void onSuccess(String result);

    void onFailure(String e);
}
