package com.v.module_widget.love_layout;

import android.view.View;
import android.widget.RelativeLayout;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 直播点赞效果
 */
public class LoveLayoutActivity extends BaseTitleBarActivity {
    private LoveLayout mLoveLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_love_layout;
    }

    @Override
    protected String setTitle() {
        return "直播点赞效果";
    }

    @Override
    protected void initData() {
        mLoveLayout = findViewById(R.id.love_layout);
    }

    @Override
    protected void processLogical() {

    }

    /**
     * 添加
     */
    public void addLoveClick(View view) {
        mLoveLayout.addLove();
    }
}