package com.v.webview_module.autoservice;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.v.common_module.autoservice.IWebViewService;
import com.v.webview_module.WebViewMainActivity;
import com.v.webview_module.constant.Constants;
import com.v.webview_module.fragment.WebViewFragment;

@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {

    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        Intent intent = new Intent(context, WebViewMainActivity.class);
        intent.putExtra(Constants.EXTRA_URL, url);
        intent.putExtra(Constants.EXTRA_TITLE, title);
        intent.putExtra(Constants.EXTRA_SHOW_ACTION_BAR, isShowActionBar);
        context.startActivity(intent);
    }

    @Override
    public Fragment getWebViewFragment(String url) {
        return WebViewFragment.newInstance(url, false);
    }
}
