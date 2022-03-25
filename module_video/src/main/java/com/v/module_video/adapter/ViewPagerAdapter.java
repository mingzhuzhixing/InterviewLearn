package com.v.module_video.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * ClassName: ViewPagerAdapter
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/14 6:25 下午
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mViewList;
    private List<String> mTitleList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mViewList) {
        super(fm);
        this.mViewList = mViewList;
        notifyDataSetChanged();
    }

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mViewList, List<String> mTitleList) {
        super(fm);
        this.mViewList = mViewList;
        this.mTitleList = mTitleList;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            super.destroyItem(container, position, object);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null && mTitleList.size() != 0) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }
}
