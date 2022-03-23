package com.v.module_widget.screem_view;

import android.view.View;
import android.view.ViewGroup;

/**
 * 筛选菜单等等 Adapter
 */
public abstract class BaseMenuAdapter {
    //获取总共有多少条
    public abstract int getCount();

    //获取当前的tabview
    public abstract View getTabView(int position, ViewGroup parent);

    //获取菜单的内容
    public abstract View getMenuView(int position,ViewGroup parent);
}
