package com.v.member;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.v.annotations.BindPath;

@BindPath("main/main")
public class MMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmain);
    }
}
