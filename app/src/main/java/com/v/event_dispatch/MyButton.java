package com.v.event_dispatch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-18 18:00
 */

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = "MyButton";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton-onTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton-onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton-dispatchTouchEvent-ACTION_DOWN");
//                break;
                return false;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton-dispatchTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

}
