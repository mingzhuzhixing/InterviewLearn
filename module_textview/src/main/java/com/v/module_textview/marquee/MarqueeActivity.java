package com.v.module_textview.marquee;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_textview.R;


public class MarqueeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        TextView tv = findViewById(R.id.tv);
        tv.setTextSize(30);
    }
}