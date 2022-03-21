package com.v.module_widget.tag_layout;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Tag流式布局
 */
public class TagLayoutActivity extends BaseTitleBarActivity {
    private TagLayout tagLayout;
    private List<String> mItems;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tag_layout;
    }

    @Override
    protected String setTitle() {
        return "流式布局";
    }

    @Override
    protected void initData() {
        tagLayout = findViewById(R.id.tag_layout);
    }

    /**
     * cai
     */
    @Override
    protected void processLogical() {
        //标签 ，后台获取， 很多实现方式 加List<String>的集合
        mItems = new ArrayList<>();
        mItems.add("11111");
        mItems.add("11111111111");
        mItems.add("1111111");
        mItems.add("111");
        mItems.add("11111");
        mItems.add("1");
        mItems.add("1111111111111111111111");
        mItems.add("111111111");
        mItems.add("1111111111111");
        mItems.add("111111");
        mItems.add("111");

        tagLayout.setAdapter(new TagLayoutAdapter() {

            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView view = (TextView) LayoutInflater.from(TagLayoutActivity.this).inflate(R.layout.item_tag_layout, parent, false);
                view.setText(mItems.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TagLayoutActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }

            @Override
            public void unRegisterDataSetObserver(DataSetObserver observable) {

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observable) {

            }
        });
    }
}