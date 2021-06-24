package com.youshu.rxjava_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youshu.rxjava_module.http.HttpRetrofit;

public class UserInfoActivity extends AppCompatActivity {
    WanAndroidApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        api = HttpRetrofit.createRetrofit().create(WanAndroidApi.class);
    }
}