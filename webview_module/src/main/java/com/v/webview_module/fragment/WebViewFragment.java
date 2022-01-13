package com.v.webview_module.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.v.webview_module.R;
import com.v.webview_module.WebViewMainActivity;
import com.v.webview_module.constant.Constants;
import com.v.webview_module.databinding.FragmentWebViewBinding;
import com.v.webview_module.listener.WebViewCallBack;
import com.v.webview_module.webchromeclient.MyWebChromeClient;
import com.v.webview_module.webviewclient.MyWebViewClient;

/**
 * WebViewFragment
 */
public class WebViewFragment extends Fragment implements WebViewCallBack {
    private FragmentWebViewBinding mBinding;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(getArguments().getString(Constants.EXTRA_URL));

        mBinding.webview.setWebViewClient(new MyWebViewClient(this));
        mBinding.webview.setWebChromeClient(new MyWebChromeClient(this));
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPageStarted(String url) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onReceivedTitle(String title) {
        if (getActivity() instanceof WebViewMainActivity) {
            ((WebViewMainActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void onError(int code) {

    }
}
