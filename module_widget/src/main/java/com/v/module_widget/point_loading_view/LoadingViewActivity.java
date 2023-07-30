package com.v.module_widget.point_loading_view;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

public class LoadingViewActivity extends BaseTitleBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading_view;
    }

    @Override
    protected String setTitle() {
        return "loading_view";
    }

}