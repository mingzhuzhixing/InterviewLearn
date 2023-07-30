package com.v.module_widget.color_track;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R2;
import com.v.module_widget.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("NonConstantResourceId")
public class ViewPageActivity extends BaseTitleBarActivity {
    private final String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};

    @BindView(R2.id.ll_tab_layout)
    public LinearLayout llIndicatorLayout;

    @BindView(R2.id.viewpager)
    public ViewPager viewPager;

    private List<ColorTrackTextView> mIndicators;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_page;
    }

    @Override
    protected boolean isNeedButterKnife() {
        return true;
    }

    @Override
    public void initData() {
        mIndicators = new ArrayList<>();
    }

    @Override
    protected String setTitle() {
        return "滑动viewpager字体变色";
    }

    @Override
    public void processLogical() {
        initViewPager();
        initIndicator();
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        for (String item : items) {
            fragments.add(ItemFragment.newInstance(item));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ColorTrackTextView textViewLeft = mIndicators.get(position);
                textViewLeft.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                textViewLeft.setProgress(1 - positionOffset);

                if (position < items.length - 1) {
                    ColorTrackTextView textViewRight = mIndicators.get(position + 1);
                    textViewRight.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    textViewRight.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 初始化可变色的指示器
     */
    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            //设置颜色
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setText(items[i]);
            colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setLayoutParams(params);
            //把心得加入Linearlayout容器中
            llIndicatorLayout.addView(colorTrackTextView);
            mIndicators.add(colorTrackTextView);
        }
    }

}