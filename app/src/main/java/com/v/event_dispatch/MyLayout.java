package com.v.event_dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-19 10:27
 */

public class MyLayout extends LinearLayout {
    private static final String TAG = "MyLayout";

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyLayout-onTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyLayout-onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }

        boolean b=super.onTouchEvent(event);
//        boolean b= false;
        Log.e(TAG, "MyLayout-onTouchEvent-返回值："+b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyLayout-dispatchTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyLayout-dispatchTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        boolean b=super.dispatchTouchEvent(event);
//        boolean b=false;
        Log.e(TAG, "MyLayout-dispatchTouchEvent-返回值："+b);
        /**
         *
         * 返回false
         04-19 14:32:29.087 3631-3631/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
         04-19 14:32:29.092 3631-3631/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
         04-19 14:32:29.092 3631-3631/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：false
         04-19 14:32:29.096 3631-3631/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_DOWN
         04-19 14:32:29.193 3631-3631/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
         04-19 14:32:29.193 3631-3631/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_UP


         * 返回super.dispatchTouchEvent(event)-->true
         04-19 14:28:18.008 537-537/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
         04-19 14:28:18.010 537-537/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
         04-19 14:28:18.010 537-537/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
         04-19 14:28:18.010 537-537/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
         04-19 14:28:18.018 537-537/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-返回值：true
         04-19 14:28:18.018 537-537/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：true
         04-19 14:28:18.133 537-537/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
         04-19 14:28:18.134 537-537/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
         04-19 14:28:18.134 537-537/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP
         04-19 14:28:18.134 537-537/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
         04-19 14:28:18.135 537-537/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-返回值：true
         04-19 14:28:18.135 537-537/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：true
         */
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyLayout-onInterceptTouchEvent-ACTION_DOWN");
                break;
//                return true;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyLayout-onInterceptTouchEvent-ACTION_UP");
                break;
//                return true;
            default:
                break;
        }

//        boolean  b=super.onInterceptTouchEvent(event);
        boolean  b=true;
        Log.e(TAG, "MyLayout-onInterceptTouchEvent-返回值："+b);

        /**
         * 直接返回true
         04-19 16:21:51.470 4570-4570/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
         04-19 16:21:51.474 4570-4570/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
         04-19 16:21:51.475 4570-4570/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
         04-19 16:21:51.475 4570-4570/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-返回值：true
         04-19 16:21:51.476 4570-4570/com.v.designmode E/MyLayout: MyLayout-onTouchEvent-ACTION_DOWN
         04-19 16:21:51.477 4570-4570/com.v.designmode E/MyLayout: MyLayout-onTouchEvent-返回值：false
         04-19 16:21:51.477 4570-4570/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：false
         04-19 16:21:51.480 4570-4570/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_DOWN
         04-19 16:21:51.533 4570-4570/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
         04-19 16:21:51.534 4570-4570/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_UP
         *
         *
         */
        return b;
    }
}
