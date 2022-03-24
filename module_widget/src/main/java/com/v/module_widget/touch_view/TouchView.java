package com.v.module_widget.touch_view;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchView extends View {
    private final static String TAG = "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @return  super.onTouchEvent(event) 执行 OnTouchListener.DOWN -> OnTouchListener.MOVE--> OnTouchListener.UP -> onClickListener
     *          true 执行 OnTouchListener.DOWN -> OnTouchListener.MOVE--> OnTouchListener.UP不会执行 onClickListener
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent-->" + event.getAction());
        return super.onTouchEvent(event);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent-->" + event.getAction());

//        super.dispatchTouchEvent(event);  //这种情况都会执行
//        return super.dispatchTouchEvent(event);

        //return true; return false; 这种情况都不会执行，因为没有调用super.dispatchTouchEvent(event)  所以onTouchEvent(event) 和 li.mOnTouchListener.onTouch(this, event)方法都不会调用
        return true;
    }
}