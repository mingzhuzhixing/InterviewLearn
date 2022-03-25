package com.v.module_dagger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.v.module_dagger.http.HttpRetrofit;

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