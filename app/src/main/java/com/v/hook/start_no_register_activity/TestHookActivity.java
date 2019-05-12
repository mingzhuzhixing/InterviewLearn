package com.v.hook.start_no_register_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.v.interviewlearn.R;

public class TestHookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hook);


        HookActivityUtil hookActivityUtil = new HookActivityUtil(this,TestHookRegisteredActivity.class);
        try {
            hookActivityUtil.hookStartActivity();
            hookActivityUtil.hookLaunchActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void open(View view) {
        //TestHookUnRegisteredActivity是没有注册的Activity，这个是我们真正要启动的Activity
        startActivity(new Intent(getApplicationContext(),TestHookUnRegisteredActivity.class));
    }
}
