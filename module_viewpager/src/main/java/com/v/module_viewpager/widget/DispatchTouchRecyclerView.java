package com.v.module_viewpager.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 处理 ViewPager2 嵌套 fragment中RecyclerView滑动冲突
 */
public class DispatchTouchRecyclerView extends RecyclerView {
    private int mLastX;
    private int mLastY;
    private float downX;

    public DispatchTouchRecyclerView(@NonNull Context context) {
        super(context);
    }

    public DispatchTouchRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchTouchRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //处理触摸事件的分发 是从dispatchTouchEvent开始的
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean isDispatchTouch = super.dispatchTouchEvent(event);
        //触摸点相对于其所在组件原点的X坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手按下屏幕,父布局没有作用,进行拦截让父布局ViewPager禁用拦截功能,从而让父布局忽略事件后的一切行为
                //requestDisallowInterceptTouchEvent(true)表示：getParent() 获取到父视图 父视图不拦截触摸事件
                //孩子不希望父视图拦截触摸事件
                downX = event.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //水平移动的增量
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                float moveX = event.getX();
                int itemCount = this.getAdapter() != null ? this.getAdapter().getItemCount() : 0;
                int firstCompletePosition = 0, lastCompletePosition = 0;
                LayoutManager layoutManager = getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutMgr = (LinearLayoutManager) layoutManager;
                    firstCompletePosition = layoutMgr.findFirstCompletelyVisibleItemPosition();
                    lastCompletePosition = layoutMgr.findLastCompletelyVisibleItemPosition();
                }
                //Log.i("zm1234", "firstCompletePosition: " + firstCompletePosition + " lastCompletePosition:" + lastCompletePosition + " itemCount:" + itemCount);
                //requestDisallowInterceptTouchEvent(false)表示：孩子希望父视图拦截触摸事件,也就是让DispatchTouchRecyclerView拦截触摸事件，进行左右滑动
                //Math.abs绝对值
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    //当竖直增量大于水平增量时，表示竖直滑动，此时需要父View去处理事件,所以不拦截让父布局ViewPager使用拦截功能,从而让父布局完成事件后的一切行为
                    getParent().requestDisallowInterceptTouchEvent(false);
                    isDispatchTouch = false;
                } else {
                    //孩子希望父视图拦截触摸事件,也就是让DispatchTouchRecyclerView拦截触摸事件，进行左右滑动
                    if (firstCompletePosition == 0) {
                        if (moveX - downX > 100) { //向右
                            getParent().requestDisallowInterceptTouchEvent(false);
                            isDispatchTouch = false;
                        } else {
                            getParent().requestDisallowInterceptTouchEvent(true);
                            isDispatchTouch = true;
                        }
                    } else if (lastCompletePosition == itemCount - 1) {
                        if (moveX - downX < -100) { //向左
                            getParent().requestDisallowInterceptTouchEvent(false);
                            isDispatchTouch = false;
                        } else {
                            getParent().requestDisallowInterceptTouchEvent(true);
                            isDispatchTouch = true;
                        }
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        isDispatchTouch = true;
                    }
                }
                break;
        }
        mLastX = x;
        mLastY = y;
        return isDispatchTouch;
    }
}
