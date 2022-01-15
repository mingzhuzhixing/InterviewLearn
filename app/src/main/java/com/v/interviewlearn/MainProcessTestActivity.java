package com.v.interviewlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * webview 通信测试打开主进程的测试activity
 */
public class MainProcessTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process_test);
    }
}