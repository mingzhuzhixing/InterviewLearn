package com.v.mvp_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mvpOnclick(View view) {
        startActivity(new Intent(this,MvpActivity.class));
    }

    public void mvp3Onclick(View view) {
        startActivity(new Intent(this,Mvp3Activity.class));
    }
}