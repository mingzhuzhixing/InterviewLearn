package com.v.module_optimize;

import android.content.Intent;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_optimize.ui.ViewStubActivity;

public class MainOptimizeActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_optimize;
    }

    @Override
    protected String setTitle() {
        return "性能优化";
    }

    /**
     * viewstub使用
     */
    public void viewStubClick(View view) {
        startActivity(new Intent(this, ViewStubActivity.class));
    }
}