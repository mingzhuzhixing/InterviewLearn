package com.v.module_viewpager.viewpager2_recyclerview;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import com.v.module_base.BaseTitleBarActivity;
import com.v.module_viewpager.R;
import com.v.module_viewpager.adapter.MyViewPager2Adapter;
import com.v.module_viewpager.fragment.Fragment1;
import com.v.module_viewpager.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Viewpager2 和 Recyclerview滑动冲突
 */
public class Viewpager2RecyclerviewActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager2_recyclerview;
    }

    @Override
    protected String setTitle() {
        return "Viewpager2 和 Recyclerview滑动冲突";
    }

    @Override
    public void initData() {
        super.initData();

        ViewPager2 viewPager = findViewById(R.id.view_pager2);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new RecyclerviewFragment());
        fragments.add(new Fragment3());

        MyViewPager2Adapter adapter = new MyViewPager2Adapter(this);
        adapter.addFragments(fragments);

        viewPager.setAdapter(adapter);
    }
}