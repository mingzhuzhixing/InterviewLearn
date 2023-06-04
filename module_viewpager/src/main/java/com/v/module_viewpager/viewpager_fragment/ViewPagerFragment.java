package com.v.module_viewpager.viewpager_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.v.module_viewpager.R;
import com.v.module_viewpager.adapter.MyViewPagerAdapter;
import com.v.module_viewpager.fragment.Fragment1;
import com.v.module_viewpager.fragment.Fragment2;
import com.v.module_viewpager.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(), fragments));

        return view;
    }

}