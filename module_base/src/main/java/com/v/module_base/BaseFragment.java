package com.v.module_base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类  带有标题
 */
@SuppressLint("NonConstantResourceId")
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayoutId() > 0) {
            view = inflater.inflate(getLayoutId(), container, false);
        } else {
            view = new View(mActivity);
        }

        if (isNeedButterKnife()) {
            unbinder = ButterKnife.bind(this, view);
        }

        initData();
        processLogical();

        return view;
    }

    /**
     * 页面layoutId
     */
    protected abstract int getLayoutId();

    protected abstract String setTitle();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 处理逻辑
     */
    protected abstract void processLogical();

    /**
     * 是否需要ButterKnife
     */
    protected boolean isNeedButterKnife() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
