package com.v.module_android_basic.hook.start_no_register_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.v.interviewlearn.R;

/**
 * TestHookRegisteredActivity是在AndroidManifest中注册的一个傀儡Activity，是个空的，没有实际功能
 */
public class TestHookRegisteredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hook_registered);
    }
}
