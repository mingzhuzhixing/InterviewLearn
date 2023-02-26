package com.v.module_video.ali_player;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.v.module_utils.DensityUtils;
import com.v.module_utils.ScreenUtils;
import com.v.module_video.R;
import com.v.module_video.ali_player.adapter.ViewPagerAdapter;
import com.v.module_video.ali_player.fragment.LiveChatFragment;
import com.v.module_video.ali_player.fragment.LiveTeacherFragment;
import com.v.module_video.ali_player.view.LiveVideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * 有书直播项目demo
 *
 */
public class AliVideoActivity extends AppCompatActivity {
    private LiveVideoView rl_video;
    private int screenW, screenH;//视频的宽度和高度
    private TextView tv_animation;
    private int mVideoHeight;

    /**
     * 加载半屏贴片广告内容容器
     */
    public FrameLayout webfragment_container;
    public FrameLayout WebFragment_container_inner;
    public FrameLayout fl_allsceen_featurecontainer;

    public TextView tv_tiepian_ad, tv_tuiguang_ad, tv_reply_msg;

    private ViewPager viewpager;

    /**
     * 横竖屏 1 横屏  2 竖屏
     */
    private int mIsFullScreen = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_video);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 获取屏幕的宽度和高度
        screenW = ScreenUtils.getScreenWidthPixels(this);
        screenH = ScreenUtils.getScreenHeightPixels(this);

        TextView tv_all_screen = findViewById(R.id.tv_all_screen);
        rl_video = findViewById(R.id.rl_video);
        tv_animation = findViewById(R.id.tv_animation);
        webfragment_container = findViewById(R.id.webfragment_container);
        WebFragment_container_inner = findViewById(R.id.WebFragment_container_inner);
        fl_allsceen_featurecontainer = findViewById(R.id.fl_allsceen_featurecontainer);
        fl_allsceen_featurecontainer.setLayoutParams(new ConstraintLayout.LayoutParams(DensityUtils.dip2px(this, 310), screenW));

        tv_tiepian_ad = findViewById(R.id.tv_tiepian_ad);
        tv_tuiguang_ad = findViewById(R.id.tv_tuiguang_ad);
        tv_reply_msg = findViewById(R.id.tv_reply_msg);

        viewpager = findViewById(R.id.viewpager);
        List<Fragment> lists = new ArrayList<>();
        lists.add(new LiveChatFragment());
        lists.add(new LiveTeacherFragment());
        List<String> titles = new ArrayList<>();
        titles.add("聊天");
        titles.add("老师");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), lists, titles);
        viewpager.setAdapter(adapter);

        mVideoHeight = screenW * 9 / 16;

        rl_video.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mVideoHeight));

        tv_all_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOrientation();
            }
        });

        findViewById(R.id.tv_half_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsFullScreen == 1){
                    return;
                }
                if (webfragment_container.getVisibility() == View.VISIBLE) {
                    hideHalfSceenDingyueWithAnimation();
                } else {
                    showHalfSceenDingyueWithAnimation("", 0);
                }
            }
        });


        findViewById(R.id.tv_all_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsFullScreen == 2){
                    return;
                }
                if (fl_allsceen_featurecontainer.getVisibility() == View.VISIBLE) {
                    hideAllSceenDingYueWithAnimation();
                } else {
                    showAllSceenDingyueWithAnimation("", 0);
                }
            }
        });

        rl_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fl_allsceen_featurecontainer.getVisibility() == View.VISIBLE) {
                    hideAllSceenDingYueWithAnimation();
                } else if (webfragment_container.getVisibility() == View.VISIBLE) {
                    hideHalfSceenDingyueWithAnimation();
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changeOrientation() {
        try {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {//当前竖屏--->横屏
                mIsFullScreen = 1;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                rl_video.setLayoutParams(new ConstraintLayout.LayoutParams(screenH, screenW-70));

                setAnimationTopMargin(100);

                setTiePianAdTopMargin(60);

                setTuiGuangAdTopMargin(160);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {//当前横屏--->竖屏
                mIsFullScreen = 2;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                rl_video.setLayoutParams(new ConstraintLayout.LayoutParams(screenW, mVideoHeight));

                setAnimationTopMargin(350);

                setTiePianAdTopMargin(350);

                setTuiGuangAdTopMargin(450);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAnimationTopMargin(int dipView) {
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) tv_animation.getLayoutParams();
        layoutParams2.topMargin = DensityUtils.dip2px(this, dipView);
        tv_animation.setLayoutParams(layoutParams2);
    }

    private void setTiePianAdTopMargin(int dipView) {
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) tv_tiepian_ad.getLayoutParams();
        layoutParams3.topMargin = DensityUtils.dip2px(this, dipView);
        tv_tiepian_ad.setLayoutParams(layoutParams3);
    }

    private void setTuiGuangAdTopMargin(int dipView) {
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) tv_tuiguang_ad.getLayoutParams();
        layoutParams3.topMargin = DensityUtils.dip2px(this, dipView);
        tv_tuiguang_ad.setLayoutParams(layoutParams3);
    }


    /**
     * 显示半屏动画
     *
     * @param url  url
     * @param type 类型 区分加载的是WebGongDuFragment 为0 还是LiveShopBagFragment 为1
     */
    public void showHalfSceenDingyueWithAnimation(String url, int type) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(webfragment_container, "y", screenH, rl_video.getHeight());
        objectAnimator.addListener(animatorListenerShow);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    public void hideHalfSceenDingyueWithAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(webfragment_container, "y", rl_video.getHeight(), screenH);
        objectAnimator.addListener(animatorListenerHide);
        objectAnimator.setDuration(150);
        objectAnimator.start();
    }

    /**
     * 显示全屏的弹框动画
     */
    public void showAllSceenDingyueWithAnimation(String url, int type) {
        int widthPixels = ScreenUtils.getWidthPixels(this);
        float width = DensityUtils.dip2px(this, 310);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fl_allsceen_featurecontainer, "x", widthPixels, widthPixels - width);
        objectAnimator.addListener(animatorListenerShow);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    public void hideAllSceenDingYueWithAnimation() {
        int widthPixels = ScreenUtils.getWidthPixels(this);
        float width = DensityUtils.dip2px(this, 310);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fl_allsceen_featurecontainer, "x", widthPixels - width, widthPixels);
        objectAnimator.addListener(animatorListenerHide);
        objectAnimator.setDuration(150);
        objectAnimator.start();
    }

    private final Animator.AnimatorListener animatorListenerShow = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            try {
                Log.i("landspace", "animatorListenerShow onAnimationStart():" + mIsFullScreen);
                if (mIsFullScreen == 1) {
                    //loadDingYueFragment(url, type);
                    fl_allsceen_featurecontainer.setVisibility(View.VISIBLE);
                } else if (mIsFullScreen == 2) {
                    //loadDingYueFragment(url, type);
                    webfragment_container.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                //正常逻辑处理，不需要上报
                e.printStackTrace();
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            try {
                Log.i("landspace", "animatorListenerShow onAnimationEnd():" + mIsFullScreen);
                if (mIsFullScreen == 1) {
                    fl_allsceen_featurecontainer.setVisibility(View.VISIBLE);
                } else if (mIsFullScreen == 2) {
                    webfragment_container.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                //正常逻辑处理，不需要上报
                e.printStackTrace();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private final Animator.AnimatorListener animatorListenerHide = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            try {
                Log.i("landspace", "animatorListenerHide onAnimationEnd():" + mIsFullScreen);
                if (mIsFullScreen == 1) {
                    fl_allsceen_featurecontainer.setVisibility(View.GONE);
                } else if (mIsFullScreen == 2) {
                    webfragment_container.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                //正常容错逻辑，不需要上报
                e.printStackTrace();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            try {
                if (mIsFullScreen == 1) {
                    fl_allsceen_featurecontainer.setVisibility(View.GONE);
                } else if (mIsFullScreen == 2) {
                    webfragment_container.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                //正常容错逻辑，不需要上报
                e.printStackTrace();
            }
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}