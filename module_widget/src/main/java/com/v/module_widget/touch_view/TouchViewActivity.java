package com.v.module_widget.touch_view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.v.module_widget.R;

public class TouchViewActivity extends AppCompatActivity {
    private final static String TAG = "TouchView";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view);

        TextView tv_touch = findViewById(R.id.tv_touch);

        tv_touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "OnTouchListener-->" + event.getAction());
                return false;
            }
        });

        tv_touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick-->");
            }
        });
    }
}