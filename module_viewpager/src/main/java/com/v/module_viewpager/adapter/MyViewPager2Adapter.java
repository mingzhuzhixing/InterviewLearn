package com.v.module_viewpager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyViewPager2Adapter extends FragmentStateAdapter {

    private List<Fragment> mViewList;
    private List<String> mTitleList;

    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragments(List<Fragment> fragment) {
        this.mViewList = fragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getItemCount() {
        return mViewList.size();
    }
}
