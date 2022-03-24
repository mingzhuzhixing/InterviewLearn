package com.v.module_widget.screem_view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v.module_widget.R;

public class ListScreenMenuAdapter extends BaseMenuAdapter {
    private String[] mItems = {"类型", "品牌", "价格", "更多"};

    private Context mContext;
    private LayoutInflater mInflater;

    public ListScreenMenuAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public View getTabView(int position, ViewGroup parent) {
        TextView tabView = (TextView) mInflater.inflate(R.layout.ui_list_data_screen_tab, parent, false);
        tabView.setText(mItems[position]);
        tabView.setTextColor(Color.BLACK);
        return tabView;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        TextView menuView = (TextView) mInflater.inflate(R.layout.ui_list_data_screen_menu, parent, false);
        menuView.setText(mItems[position]);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        return menuView;
    }

    @Override
    public void menuSelectStyle(View tabView) {
        if (tabView instanceof TextView) {
            ((TextView) tabView).setTextColor(Color.RED);
        }
    }

    @Override
    public void menuNormalStyle(View tabView) {
        if (tabView instanceof TextView) {
            ((TextView) tabView).setTextColor(Color.BLACK);
        }
    }
}
