package com.v.textview_module.marquee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.v.textview_module.R;

public class MarqueeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        TextView tv = findViewById(R.id.tv);
        tv.setTextSize(30);
    }
}