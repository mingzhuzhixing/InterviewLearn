package com.v.module_picker_view.wheel_view;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public final class LoopViewGestureListener extends SimpleOnGestureListener {
    private final WheelView wheelView;

    public LoopViewGestureListener(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.wheelView.scrollBy(velocityY);
        return true;
    }
}