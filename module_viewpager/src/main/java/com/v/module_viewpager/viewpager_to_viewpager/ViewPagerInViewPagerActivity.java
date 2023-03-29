package com.v.module_viewpager.viewpager_to_viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.v.module_viewpager.R;
import com.v.module_viewpager.adapter.MyViewPagerAdapter;
import com.v.module_viewpager.fragment.Fragment1;
import com.v.module_viewpager.fragment.Fragment2;
import com.v.module_viewpager.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerInViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_in_view_pager);

        viewPager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragments));
    }
}