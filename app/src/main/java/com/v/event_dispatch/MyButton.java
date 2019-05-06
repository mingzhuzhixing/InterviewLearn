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
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton-dispatchTouchEvent-ACTION_DOWN");
                break;
//                return false;

            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton-dispatchTouchEvent-ACTION_UP");
                break;
//                return false;
            default:
                break;
        }
        boolean b=super.dispatchTouchEvent(event);
//        boolean b=false;

        Log.e(TAG, "MyButton-dispatchTouchEvent-返回值："+b);
        /**
         *
         * 直接返回值 false
         04-19 15:12:28.085 15020-15020/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
         04-19 15:12:28.087 15020-15020/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
         04-19 15:12:28.087 15020-15020/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
         04-19 15:12:28.087 15020-15020/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
         04-19 15:12:28.088 15020-15020/com.v.designmode E/MyLayout: MyLayout-onTouchEvent-ACTION_DOWN
         04-19 15:12:28.089 15020-15020/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：false
         04-19 15:12:28.091 15020-15020/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_DOWN
         04-19 15:12:28.225 15020-15020/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
         04-19 15:12:28.226 15020-15020/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_UP



         * 返回super.dispatchTouchEvent(event)-->true
         04-19 15:10:30.683 13825-13825/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
         04-19 15:10:30.685 13825-13825/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
         04-19 15:10:30.686 13825-13825/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
         04-19 15:10:30.686 13825-13825/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
         04-19 15:10:30.686 13825-13825/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN
         04-19 15:10:30.692 13825-13825/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-返回值：true
         04-19 15:10:30.692 13825-13825/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：true
         04-19 15:10:30.847 13825-13825/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
         04-19 15:10:30.847 13825-13825/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
         04-19 15:10:30.848 13825-13825/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP
         04-19 15:10:30.848 13825-13825/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
         04-19 15:10:30.848 13825-13825/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_UP
         04-19 15:10:30.848 13825-13825/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-返回值：true
         04-19 15:10:30.849 13825-13825/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：true


         */
        return b;
    }


    /** 上面 dispatchTouchEvent返回false
     04-19 14:46:58.458 7897-7897/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 14:46:58.460 7897-7897/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 14:46:58.460 7897-7897/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 14:46:58.460 7897-7897/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
     04-19 14:46:58.460 7897-7897/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-返回值：false
     04-19 14:46:58.461 7897-7897/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-返回值：false
     04-19 14:46:58.462 7897-7897/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_DOWN
     04-19 14:46:58.558 7897-7897/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 14:46:58.559 7897-7897/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-onTouchEvent-ACTION_UP
     */
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
        boolean b=super.onTouchEvent(event);
//        boolean b=false;
        Log.e(TAG, "MyButton-onTouchEvent-返回值："+b);
        return b;
    }
}
