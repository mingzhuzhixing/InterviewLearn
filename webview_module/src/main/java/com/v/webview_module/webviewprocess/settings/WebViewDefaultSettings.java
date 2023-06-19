package com.v.webview_module.webviewprocess.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.v.webview_module.BuildConfig;

public class WebViewDefaultSettings {

    public static WebViewDefaultSettings getInstance() {
        return WebViewClassHolder.instance;
    }

    private static class WebViewClassHolder {
        static WebViewDefaultSettings instance = new WebViewDefaultSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setSettings(WebView webView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.enableSlowWholeDocumentDraw();
        }
        WebSettings mWebSettings = webView.getSettings();
        //支持js
        mWebSettings.setJavaScriptEnabled(true);
        //支持缩放
        mWebSettings.setSupportZoom(true);
        //设置支持缩放
        mWebSettings.setBuiltInZoomControls(false);

        if (isNetworkConnected(webView.getContext())) {
            //默认网络缓存
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //关闭webview中缓存
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //硬件加速兼容想问题有点多
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }

        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
//        mWebSettings.setAppCacheEnabled(true);
        //支持自动加载图片
        mWebSettings.setLoadsImagesAutomatically(true);
        //多窗口
        mWebSettings.setSupportMultipleWindows(false);
        //是否阻塞加载网络图片 协议http or https
        mWebSettings.setBlockNetworkImage(false);
        //允许加载本地文件html file协议
        mWebSettings.setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //通过file url 加载的 javascript 读取其他的本地文件
            mWebSettings.setAllowFileAccessFromFileURLs(false);
            //通过file url 加载的 javascript 读取其他的本地文件
            mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        //支持通过JS打开新窗口
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //支持内容重新布局
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mWebSettings.setSavePassword(false);
        mWebSettings.setSaveFormData(false);
        // 缩放至屏幕的大小
        mWebSettings.setLoadWithOverviewMode(true);
        //将图片调整到适合webview的大小
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setDefaultFontSize(16);
        //设置webview 支持的最小字体大小，默认8
        mWebSettings.setMinimumFontSize(10);
        mWebSettings.setGeolocationEnabled(true);

        String appCacheDir = webView.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        mWebSettings.setDatabasePath(appCacheDir);
//        mWebSettings.setAppCachePath(appCacheDir);
//        mWebSettings.setAppCacheMaxSize(1024 * 1024 * 80);

        //用户可以自己设置useragent
        //mWebSettings.setUserAgentString("webprogress/build you agent info");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
    }

    /**
     * 判断网络连接情况
     */
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        } else {
            return false;
        }
    }
}
