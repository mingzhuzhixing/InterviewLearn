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
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
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
                if ("showToast".equals(paramBean.getName())) {
                    String message = paramBean.getParam().getString("message");
                    Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
                } else if ("openPage".equals(paramBean.getName())) {
                    String target_class = paramBean.getParam().getString("target_class");

                    //第一种方式：
                    //Intent intent = new Intent();
                    //intent.setComponent(new ComponentName(BaseApplication.sApplication, target_class));
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //BaseApplication.sApplication.startActivity(intent);

                    //第二种方式:
                    WebViewProcessCommandDispatcher.getInstance().executeCommand(paramBean.getName(), target_class);
                }
            }
        }
    }
}
