package com.youshu.dagger_module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.youshu.dagger_module.http.HttpRetrofit;

import javax.inject.Inject;

public class DaggerMainActivity extends AppCompatActivity {

    @Inject
    HttpRetrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_main);


    }
}