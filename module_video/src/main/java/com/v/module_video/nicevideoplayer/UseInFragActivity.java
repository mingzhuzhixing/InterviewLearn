package com.v.module_video.nicevideoplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_nicevideoplayer.NiceVideoPlayerManager;
import com.v.module_video.R;

public class UseInFragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_in_frag);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new DemoFragenment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
