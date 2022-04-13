package com.v.module_widget.event_dispatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.v.module_widget.R;

public class EventDispatch2Activity extends AppCompatActivity {
    private static final String TAG="EventDispatch2Activity";
    private MyLayout mMyLayout;
    private MyButton mMyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch2);

        mMyButton=findViewById(R.id.button);
        mMyLayout=findViewById(R.id.linearlayout_test);

        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "MyButton-onClick");
            }
        });

//        mMyButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        Log.e(TAG,"MyButton-onTouch-ACTION_DOWN");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        Log.e(TAG,"MyButton-onTouch-ACTION_UP");
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });

        mMyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"MyLayout-onClick");
            }
        });

//        mMyLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        Log.e(TAG,"MyLayout-onTouch-ACTION_DOWN");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        Log.e(TAG,"MyLayout-onTouch-ACTION_UP");
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG,"EventDispatch2Activity-dispatchTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"EventDispatch2Activity-onTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG,"EventDispatch2Activity-onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     *
     *
     04-19 10:59:44.328 12170-12170/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 10:59:44.331 12170-12170/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 10:59:44.331 12170-12170/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 10:59:44.332 12170-12170/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
     04-19 10:59:44.333 12170-12170/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_DOWN
     04-19 10:59:44.333 12170-12170/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN
     04-19 10:59:44.444 12170-12170/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 10:59:44.445 12170-12170/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
     04-19 10:59:44.445 12170-12170/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP
     04-19 10:59:44.445 12170-12170/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
     04-19 10:59:44.446 12170-12170/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_UP
     04-19 10:59:44.446 12170-12170/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_UP
     04-19 10:59:44.450 12170-12170/com.v.designmode E/EventDispatch2Activity: MyButton-onClick


     *
     * 修改 onInterceptTouchEvent()方法 donw return false
     04-19 11:44:26.366 23310-23310/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 11:44:26.370 23310-23310/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 11:44:26.371 23310-23310/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 11:44:26.371 23310-23310/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
     04-19 11:44:26.372 23310-23310/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_DOWN
     04-19 11:44:26.372 23310-23310/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN
     04-19 11:44:26.480 23310-23310/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 11:44:26.481 23310-23310/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
     04-19 11:44:26.481 23310-23310/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP
     04-19 11:44:26.482 23310-23310/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
     04-19 11:44:26.482 23310-23310/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_UP
     04-19 11:44:26.482 23310-23310/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_UP
     04-19 11:44:26.485 23310-23310/com.v.designmode E/EventDispatch2Activity: MyButton-onClick


     * 修改 onInterceptTouchEvent()方法 donw return true  相当于点击了MyLayout空间
     04-19 11:45:42.782 23992-23992/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 11:45:42.786 23992-23992/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 11:45:42.786 23992-23992/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 11:45:42.787 23992-23992/com.v.designmode E/EventDispatch2Activity: MyLayout-onTouch-ACTION_DOWN
     04-19 11:45:42.787 23992-23992/com.v.designmode E/MyLayout: MyLayout-onTouchEvent-ACTION_DOWN
     04-19 11:45:42.880 23992-23992/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 11:45:42.882 23992-23992/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
     04-19 11:45:42.882 23992-23992/com.v.designmode E/EventDispatch2Activity: MyLayout-onTouch-ACTION_UP
     04-19 11:45:42.882 23992-23992/com.v.designmode E/MyLayout: MyLayout-onTouchEvent-ACTION_UP
     04-19 11:45:42.886 23992-23992/com.v.designmode E/EventDispatch2Activity: MyLayout-onClick


     * 修改 onInterceptTouchEvent()方法 up return false
     04-19 11:50:36.163 25862-25862/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 11:50:36.164 25862-25862/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 11:50:36.164 25862-25862/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 11:50:36.165 25862-25862/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
     04-19 11:50:36.165 25862-25862/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_DOWN
     04-19 11:50:36.165 25862-25862/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN
     04-19 11:50:36.314 25862-25862/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 11:50:36.316 25862-25862/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
     04-19 11:50:36.316 25862-25862/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP
     04-19 11:50:36.317 25862-25862/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
     04-19 11:50:36.317 25862-25862/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_UP
     04-19 11:50:36.318 25862-25862/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_UP
     04-19 11:50:36.323 25862-25862/com.v.designmode E/EventDispatch2Activity: MyButton-onClick

     *修改 onInterceptTouchEvent()方法 up return true
     04-19 11:53:18.767 26498-26498/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_DOWN
     04-19 11:53:18.771 26498-26498/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_DOWN
     04-19 11:53:18.771 26498-26498/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_DOWN
     04-19 11:53:18.771 26498-26498/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
     04-19 11:53:18.773 26498-26498/com.v.designmode E/EventDispatch2Activity: MyButton-onTouch-ACTION_DOWN
     04-19 11:53:18.773 26498-26498/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN
     04-19 11:53:18.846 26498-26498/com.v.designmode E/EventDispatch2Activity: EventDispatch2Activity-dispatchTouchEvent-ACTION_UP
     04-19 11:53:18.847 26498-26498/com.v.designmode E/MyLayout: MyLayout-dispatchTouchEvent-ACTION_UP
     04-19 11:53:18.847 26498-26498/com.v.designmode E/MyLayout: MyLayout-onInterceptTouchEvent-ACTION_UP

     *
     *
     *
     *
     *
     *
     */
}
