package com.v.module_recyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kjt on 2020/5/7
 */
public class MyRecylcerView extends RecyclerView {

    public MyRecylcerView(Context context) {
        super(context);
    }

    public MyRecylcerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecylcerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        return false;
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }
}
