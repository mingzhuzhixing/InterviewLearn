package com.v.common_module.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * webview的服务接口
 * <p>
 * 依赖倒取原则：依赖接口但不依赖实现
 */
public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);

    void startLocalHtmlActivity(Context context);

    Fragment getWebViewFragment(String url);
}
