package com.v.module_widget.screem_view;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 下拉菜单列表
 */
public class ListDataScreenViewActivity extends BaseTitleBarActivity {
    private ListDataScreenView mListDataScreenView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_data_screen_view;
    }

    @Override
    protected String setTitle() {
        return "下拉菜单列表";
    }

    @Override
    protected void initData() {
        mListDataScreenView = findViewById(R.id.list_data_screen_view);
    }

    @Override
    protected void processLogical() {
        mListDataScreenView.setAdapter(new ListScreenMenuAdapter(this));
    }
}