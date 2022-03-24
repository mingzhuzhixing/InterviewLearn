package com.v.module_widget.screem_view;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 筛选菜单等等 Adapter
 */
public abstract class BaseMenuAdapter {

    //好比微信公众号  注册的订阅用户放入到集合中
    protected final ArrayList<MenuObserver> mObservers = new ArrayList<>();

    public void registerDataSetObserver(MenuObserver observer) {
        mObservers.add(observer);
    }

    public void unregisterDataSetObserver(MenuObserver observer) {
        mObservers.remove(observer);
    }

    public void closeMenu() {
        for (MenuObserver mObserver : mObservers) {
            mObserver.closeMenu();
        }
    }

    //获取总共有多少条
    public abstract int getCount();

    //获取当前的tabview
    public abstract View getTabView(int position, ViewGroup parent);

    //获取菜单的内容
    public abstract View getMenuView(int position, ViewGroup parent);

    //打开菜单栏
    public void menuSelectStyle(View tabView) {

    }

    //关闭菜单栏
    public void menuNormalStyle(View tabView) {

    }
}
