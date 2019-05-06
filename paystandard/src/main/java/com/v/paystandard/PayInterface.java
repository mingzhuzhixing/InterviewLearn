package com.v.paystandard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-06 15:04
 */
public interface PayInterface {

    /**
     * 注入上下文
     * @param proxyActivity
     */
    public void attach(Activity proxyActivity);

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResme();

    public void onPause();

    public void onStop();

    public void onDestory();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackpressed();
}
