package com.v.webview_module.webchromeclient;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.v.webview_module.listener.WebViewCallBack;

public class MyWebChromeClient extends WebChromeClient {
    private final String TAG = "MyWebChromeClient";
    private final WebViewCallBack mCallBack;

    public MyWebChromeClient(WebViewCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mCallBack != null) {
            mCallBack.onReceivedTitle(title);
        } else {
            Log.i(TAG, "mCallBack is null");
        }
    }
}
