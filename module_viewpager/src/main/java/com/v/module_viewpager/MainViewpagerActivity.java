package com.v.module_viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.module_viewpager.viewpager2_fragment.ViewPager2Activity;
import com.v.module_viewpager.viewpager_fragment.ViewPagerActivity;
import com.v.module_viewpager.viewpager_to_viewpager.ViewPagerInViewPagerActivity;

public class MainViewpagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
    }

    public void onViewPagerClick(View view) {
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

    public void onViewPager2Click(View view) {
        startActivity(new Intent(this, ViewPager2Activity.class));
    }

    public void onViewPagerAViewPager(View view) {
        startActivity(new Intent(this, ViewPagerInViewPagerActivity.class));
    }
}