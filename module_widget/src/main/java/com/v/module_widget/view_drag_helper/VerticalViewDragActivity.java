package com.v.module_widget.view_drag_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直拖动
 */
public class VerticalViewDragActivity extends BaseTitleBarActivity {
    private ListView mListView;
    private List<String> mItems;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vertical_view_drag;
    }

    @Override
    protected String setTitle() {
        return "垂直拖动";
    }

    @Override
    public void initData() {
        mListView = findViewById(R.id.listview);
        mItems = new ArrayList<>();
    }

    @Override
    public void processLogical() {
        for (int i = 0; i < 20; i++) {
            mItems.add("item" + i);
        }
        mListView.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Object getItem(int position) {
                return mItems.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                TextView textView = new TextView(VerticalViewDragActivity.this);
                textView.setLayoutParams(params);
                textView.setText(mItems.get(position));
                return textView;
            }
        });
    }
}