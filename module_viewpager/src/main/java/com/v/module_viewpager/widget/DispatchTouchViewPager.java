package com.v.module_viewpager.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 处理 ViewPager2 嵌套 fragment中ViewPager滑动冲突
 */
public class DispatchTouchViewPager extends ViewPager {
    private float downX;

    public DispatchTouchViewPager(Context context) {
        super(context);
    }

    public DispatchTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean isDispatchTouch = super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                int curPosition = this.getCurrentItem();
                int count = this.getAdapter() != null ? this.getAdapter().getCount() : 0;
                //Log.d("zm1234", "curPosition:=" + curPosition + " downX:" + downX + " x2:" + moveX + " result:" + (moveX - downX));
                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
                if (curPosition == 0) {
                    if (moveX - downX > 100) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        isDispatchTouch = false;
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        isDispatchTouch = true;
                    }
                } else if (curPosition == count - 1) {
                    if (moveX - downX < -100) {
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
                break;
        }
        return isDispatchTouch;
    }
}
