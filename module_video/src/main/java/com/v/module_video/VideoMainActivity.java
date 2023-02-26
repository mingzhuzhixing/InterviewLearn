package com.v.module_video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_video.ali_player.AliVideoActivity;
import com.v.module_video.nicevideoplayer.ChangeClarityActivity;
import com.v.module_video.nicevideoplayer.RecyclerViewActivity;
import com.v.module_video.nicevideoplayer.TinyWindowPlayActivity;
import com.v.module_video.nicevideoplayer.UseInFragActivity;

/**
 * 视频播放器
 */
public class VideoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main);
    }

    /**
     * 阿里播放器
     */
    public void onAliVideo(View view) {
        startActivity(new Intent(this, AliVideoActivity.class));
    }

    public void onNiceRecyclerView(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void onChangeClarity(View view) {
        startActivity(new Intent(this, ChangeClarityActivity.class));
    }

    public void onTinyWindowPlay(View view) {
        startActivity(new Intent(this, TinyWindowPlayActivity.class));
    }

    public void onUseInFrag(View view) {
        startActivity(new Intent(this, UseInFragActivity.class));
    }
}