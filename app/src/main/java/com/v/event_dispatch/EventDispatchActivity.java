package com.v.event_dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.v.interviewlearn.R;


public class EventDispatchActivity extends AppCompatActivity {

    private static final String TAG = "EventDispatchActivity";
    MyButton mMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        mMyButton=findViewById(R.id.button);

        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"MyButton-onClick");
            }
        });

        mMyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG,"MyButton-onTouch-ACTION_DOWN");
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.e(TAG,"MyButton-onTouch-ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"EventDispatchActivity-dispatchTouchEvent-ACTION_DOWN");
                break;
//                return true;
                //注意：在事件分发的down事件里面return false   不会继续向下把down事件分发子view,这样的话他的子类view不能响应
                //注意：在事件分发的down事件里面return true   不会继续向下把down事件分发子view,这样的话他的子类view不能响应
                //return false 表示不向下分发，  return true 表示消费调事件  这句话是针对ViewGroup

            case MotionEvent.ACTION_UP:
                Log.e(TAG,"EventDispatchActivity-dispatchTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"EventDispatchActivity-onTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(TAG,"EventDispatchActivity-onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}


/**
 *
 *
 *
 04-18 18:25:59.095 15165-15165/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_DOWN
 04-18 18:25:59.097 15165-15165/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
 04-18 18:25:59.098 15165-15165/com.v.designmode E/EventDispatchActivity: MyButton-onTouch-ACTION_DOWN
 04-18 18:25:59.098 15165-15165/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_DOWN

 04-18 18:25:59.181 15165-15165/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_UP
 04-18 18:25:59.182 15165-15165/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_UP
 04-18 18:25:59.182 15165-15165/com.v.designmode E/EventDispatchActivity: MyButton-onTouch-ACTION_UP
 04-18 18:25:59.182 15165-15165/com.v.designmode E/MyButton: MyButton-onTouchEvent-ACTION_UP

 04-18 18:25:59.185 15165-15165/com.v.designmode E/EventDispatchActivity: MyButton-onClick




 * activity的dispathTouchevent down return false
 04-18 18:36:46.145 27012-27012/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_DOWN
 04-18 18:36:46.229 27012-27012/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_UP
 04-18 18:36:46.230 27012-27012/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-onTouchEvent-ACTION_UP


 * activity的dispathTouchevent down return true
 04-18 18:44:52.263 1478-1478/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_DOWN
 04-18 18:44:52.400 1478-1478/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_UP
 04-18 18:44:52.401 1478-1478/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-onTouchEvent-ACTION_UP


 * 修改了MyButton activity的dispathTouchevent  down return false
 04-18 18:57:39.451 11881-11881/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_DOWN
 04-18 18:57:39.456 11881-11881/com.v.designmode E/MyButton: MyButton-dispatchTouchEvent-ACTION_DOWN
 04-18 18:57:39.459 11881-11881/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-onTouchEvent-ACTION_DOWN
 04-18 18:57:39.550 11881-11881/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-dispatchTouchEvent-ACTION_UP
 04-18 18:57:39.551 11881-11881/com.v.designmode E/EventDispatchActivity: EventDispatchActivity-onTouchEvent-ACTION_UP

 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
