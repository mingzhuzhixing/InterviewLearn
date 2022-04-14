package com.v.module_android_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.module_android_basic.picker_view.PickerViewActivity;

public class AndroidBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_basic);
    }

    public void timePickerViewClick(View view) {
        startActivity(new Intent(this, PickerViewActivity.class));
    }
}