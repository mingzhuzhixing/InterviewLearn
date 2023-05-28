package com.v.module_widget.scroll_number;


import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;
import com.v.module_widget.scroll_number.library.MultiScrollNumber;

import java.util.Random;

/**
 * 滚动数字控件ScrollNumber
 * https://github.com/a-voyager/ScrollNumber
 */
public class ScrollNumberActivity extends BaseTitleBarActivity {
    MultiScrollNumber scrollNumber;
    private int index = 2048;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_number;
    }

    @Override
    protected String setTitle() {
        return "滚动数字控件ScrollNumber";
    }

    @Override
    protected void initData() {
        super.initData();
        scrollNumber = findViewById(R.id.scroll_number);
        scrollNumber.setNumber(2048);
        scrollNumber.setTextColors(new int[]{R.color.blue01, R.color.red01, R.color.green01, R.color.purple01});
        scrollNumber.setTextSize(64);
        scrollNumber.setInterpolator(new DecelerateInterpolator());
        scrollNumber.setScrollVelocity(30);
    }

    public void addClick(View view) {
        index = index + (int) (Math.random() * 100);
        scrollNumber.setNumber(2048, index);
    }
}