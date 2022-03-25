package com.v.module_rxjava.listener;

import android.view.View;

import java.util.Calendar;

/**
 * ClassName: OnMultiClickListener
 * Description: 传统防多次点击
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module.listener
 * @date 2021/6/25 10:38 AM
 */
public abstract class OnMultiClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 3000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onMultiClick(v);
        }
    }

    public abstract void onMultiClick(View v);
}
