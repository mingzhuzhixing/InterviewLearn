package com.v.interviewlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v.event_dispatch.EventDispatchActivity;
import com.v.network_architecture.TestNetworkActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditText;
    private Button mButton;
    private ImageView mImageView;


    private LinearLayout mLinearLayout;
    private FrameLayout mFrameLayout;
    private RelativeLayout mRelativeLayout;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv_content);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).run();

    }


    public void updateUI(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                mTextView.setText("你好3");
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText("你好");
//            }
//        }).run();
    }

    /**
     * 进入事件分发
     */
    public void eventDispatch(View view) {
        startActivity(new Intent(this, EventDispatchActivity.class));
    }


    /**
     * 网络请求框架
     */
    public void networkRequest(View view) {
        startActivity(new Intent(this, TestNetworkActivity.class));
    }
}
