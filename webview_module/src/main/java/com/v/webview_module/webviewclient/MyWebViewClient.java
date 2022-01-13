package com.v.webview_module.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.v.webview_module.listener.WebViewCallBack;

public class MyWebViewClient extends WebViewClient {
    private final String TAG = "MyWebViewClient";
    private final WebViewCallBack mCallBack;

    public MyWebViewClient(WebViewCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mCallBack != null) {
            mCallBack.onPageStarted(url);
        } else {
            Log.i(TAG, "mCallBack is null");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mCallBack != null) {
            mCallBack.onPageFinished(url);
        } else {
            Log.i(TAG, "mCallBack is null");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mCallBack != null && error != null) {
            mCallBack.onError(error.getErrorCode());
        } else {
            Log.i(TAG, "mCallBack or error is null");
        }
    }
}
