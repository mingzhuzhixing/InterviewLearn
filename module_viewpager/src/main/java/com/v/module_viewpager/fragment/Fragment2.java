package com.v.module_viewpager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.v.module_viewpager.R;
import com.v.module_viewpager.adapter.MyViewPagerAdapter;
import com.v.module_viewpager.widget.DispatchTouchViewPager;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        DispatchTouchViewPager viewPager = view.findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentSub4());
        fragments.add(new FragmentSub5());
        fragments.add(new FragmentSub6());
        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(), fragments));
        return view;
    }
}
