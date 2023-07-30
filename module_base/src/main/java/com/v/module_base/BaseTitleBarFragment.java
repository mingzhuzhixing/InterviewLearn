package com.v.module_base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类  带有标题
 */
@SuppressLint("NonConstantResourceId")
public abstract class BaseTitleBarFragment extends BaseFragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_base_title_bar, container, false);

        //设置布局
        FrameLayout mFlContainer = view.findViewById(R.id.fl_container);
        if (getLayoutId() > 0) {
            mFlContainer.addView(getLayoutInflater().inflate(getLayoutId(), null));
            if (isNeedButterKnife()) {
                unbinder = ButterKnife.bind(this, view);
            }
        }

        //设置标题
        TextView mTvTile = view.findViewById(R.id.tv_title);
        mTvTile.setText(TextUtils.isEmpty(setTitle()) ? "" : setTitle());

        //返回
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick(v);
            }
        });

        initData();
        processLogical();

        return view;
    }

    /**
     * 返回
     */
    public void onBackClick(View view) {

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
