package com.v.module_textview.ellipsize_view;

import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_textview.R;

public class SingleLineActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_line;
    }

    @Override
    protected String setTitle() {
        return "单行文字测试";
    }

    @Override
    protected void initData() {
        TextView textview = findViewById(R.id.tv_content);
        textview.setText("不宽度,不确定字数不宽度,不确定字数不宽不确定字数不宽");
    }

    @Override
    protected void processLogical() {

    }
}