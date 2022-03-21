package com.v.module_widget.tag_layout;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract class TagLayoutAdapter {
    //1、有多少个条目
    public abstract int getCount();

    //2、getView通过position
    public abstract View getView(int position, ViewGroup parent);

    //3、观察者模式即使通知更新
    public abstract void unRegisterDataSetObserver(DataSetObserver observable);

    public abstract void registerDataSetObserver(DataSetObserver observable);
}
