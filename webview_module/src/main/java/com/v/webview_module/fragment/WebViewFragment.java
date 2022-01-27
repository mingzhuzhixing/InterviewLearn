package com.v.webview_module.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.v.base_module.callback.ErrorCallback;
import com.v.base_module.callback.LoadingCallback;
import com.v.webview_module.R;
import com.v.webview_module.WebViewMainActivity;
import com.v.webview_module.constant.Constants;
import com.v.webview_module.databinding.FragmentWebViewBinding;
import com.v.webview_module.listener.WebViewCallBack;

/**
 * WebViewFragment
 */
public class WebViewFragment extends Fragment implements WebViewCallBack {
    private final String TAG = "WebViewFragment";
    private FragmentWebViewBinding mBinding;
    private LoadService mLoadService;
    private boolean mIsSupportRefresh = false;
    private String mUrl = "";

    public static WebViewFragment newInstance(String url, boolean supportRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_URL, url);
        bundle.putBoolean(Constants.EXTRA_SUPPORT_REFRESH, supportRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);

        if (getArguments() != null) {
            mIsSupportRefresh = getArguments().getBoolean(Constants.EXTRA_SUPPORT_REFRESH, false);
            mUrl = getArguments().getString(Constants.EXTRA_URL);
        }

        initRefreshLayout();

        mBinding.webview.loadUrl(mUrl);
        mBinding.webview.registerListener(this);

        mLoadService = LoadSir.getDefault().register(mBinding.getRoot(), new Callback.OnReloadListener() {
            @Override
            public void onReload(View view) {
                Log.i(TAG, "onReload()");
                mBinding.webview.reload();
            }
        });

        return mLoadService.getLoadLayout();
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        mBinding.refreshLayout.setEnableLoadMore(false);
        mBinding.refreshLayout.setEnableRefresh(mIsSupportRefresh);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.i(TAG, "onRefresh()");
                mBinding.webview.reload();
            }
        });
    }

    @Override
    public void onPageStarted(String url) {
        mBinding.refreshLayout.finishRefresh();
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onPageFinished(String url, boolean isLoadError) {
        mBinding.refreshLayout.finishRefresh();
        if (isLoadError) {
            mBinding.refreshLayout.setEnableRefresh(false);
        } else {
            mBinding.refreshLayout.setEnableRefresh(mIsSupportRefresh);
        }
        if (mLoadService != null) {
            if (isLoadError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
