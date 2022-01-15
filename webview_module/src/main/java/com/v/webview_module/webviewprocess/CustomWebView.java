package com.v.webview_module.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.v.webview_module.bean.JsParamBean;
import com.v.webview_module.listener.WebViewCallBack;
import com.v.webview_module.webviewprocess.settings.WebViewDefaultSettings;
import com.v.webview_module.webviewprocess.webchromeclient.MyWebChromeClient;
import com.v.webview_module.webviewprocess.webviewclient.MyWebViewClient;

public class CustomWebView extends WebView {
    private final String TAG = "CustomWebView";

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        WebViewDefaultSettings.getInstance().setSettings(this);
        this.addJavascriptInterface(this, "webViewJs");
    }

    /**
     * 注册监听
     */
    public void registerListener(WebViewCallBack callBack) {
        this.setWebViewClient(new MyWebViewClient(callBack));
        this.setWebChromeClient(new MyWebChromeClient(callBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, "jsParam:" + jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            JsParamBean paramBean = JSON.parseObject(jsParam, JsParamBean.class);
            if (paramBean != null && paramBean.getParam() != null) {
                String message = paramBean.getParam().getString("message");
                Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
