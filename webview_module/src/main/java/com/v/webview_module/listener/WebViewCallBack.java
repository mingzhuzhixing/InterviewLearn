package com.v.webview_module.listener;

public interface WebViewCallBack {

    void onPageStarted(String url);

    void onPageFinished(String url, boolean isLoadError);

    void onReceivedTitle(String title);

    void onError(int code);

}