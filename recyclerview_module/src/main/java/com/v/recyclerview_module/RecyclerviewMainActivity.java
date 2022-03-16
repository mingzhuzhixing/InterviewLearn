package com.v.recyclerview_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.recyclerview_module.snap_helper.SnapHelperActivity;

public class RecyclerviewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);
    }

    public void snapHelper(View view) {
        startActivity(new Intent(this, SnapHelperActivity.class));
    }
}