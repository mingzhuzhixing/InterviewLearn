package com.v.module_viewpager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class ChildViewPager extends ViewPager {
    private static final String TAG = "xujun";
    private float x1, x2;

    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        boolean isInterceptTouch = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "isInterceptTouch:=" + isInterceptTouch+" ev.getAction():"+ev.getAction());
        return isInterceptTouch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean isDispatchTouch = super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE :
                x2 = ev.getX();
                int curPosition = this.getCurrentItem();
                int count = this.getAdapter().getCount();
                Log.d(TAG, "curPosition:=" + curPosition + " x1:" + x1 + " x2:" + x2 + " result:" + (x2 - x1));
                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
                if (curPosition == 0) {
                    if (x2 - x1 > 100) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        isDispatchTouch = false;
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        isDispatchTouch = true;
                    }
                } else if (curPosition == count - 1) {
                    if (x2 - x1 < -100) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        isDispatchTouch = false;
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        isDispatchTouch = true;
                    }
                } else {
                    //其他情况，由孩子拦截触摸事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                    isDispatchTouch = false;
                }
        }
        Log.w(TAG, "isDispatchTouch:=" + isDispatchTouch+" ev.getAction():"+ev.getAction());
        return isDispatchTouch;
    }
}
