package com.v.module_video.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v.module_video.R;
import com.v.module_video.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class AllScreenTopView extends RelativeLayout {
    @BindView(R2.id.iv_allsceen_topview_back)
    public ImageView iv_allsceen_topview_back;

    @BindView(R2.id.tv_allsceen_topview_title)
    public TextView tv_allsceen_topview_title;

    @BindView(R2.id.iv_allsceen_topview_setting)
    public ImageView iv_allsceen_topview_setting;

    @BindView(R2.id.iv_allsceen_topview_share)
    public ImageView iv_allsceen_topview_share;

    @BindView(R2.id.tv_allsceen_topview_quality)
    public TextView tv_allsceen_topview_quality;

    @BindView(R2.id.tv_topview_speakername)
    public TextView tv_topview_speakername;

    @BindView(R2.id.tv_topview_attention)
    public TextView tv_topview_attention;

    @BindView(R2.id.civ_topview_headimg)
    public ImageView civ_topview_headimg;


    com.v.url_module.MediaControllerInterface mediaController;

    public void setMediaController(com.v.url_module.MediaControllerInterface mediaController){
        this.mediaController = mediaController;
    }


    public AllScreenTopView(Context context) {
        super(context);
        init(context);
    }

    public AllScreenTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllScreenTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {
        inflate(context, R.layout.item_allsceen_topview, this);
        ButterKnife.bind(this);
    }

    public void addViewToParentLayout(ViewGroup parentLayout) {
        if (parentLayout != null) {
            if (parentLayout instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) parentLayout;
                FrameLayout.LayoutParams fllp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                frameLayout.addView(this, fllp);
            }
        }
    }

    public void removeViewFromParentLayout(ViewGroup parentLayout) {
        if (parentLayout != null) {
            if (parentLayout instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) parentLayout;
                frameLayout.removeView(this);
            }
        }
    }

    public void setListener(OnClickListener onClickListener) {
        iv_allsceen_topview_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController!=null){
                    mediaController.onBack();
                }
            }
        });
        iv_allsceen_topview_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController!=null){
                    mediaController.onSetting();
                }
            }
        });
        iv_allsceen_topview_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController!=null){
                    mediaController.onShare();
                }
            }
        });
        tv_allsceen_topview_quality.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController!=null){
                    mediaController.onQuality();
                }
            }
        });
        tv_topview_attention.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaController!=null){
                    mediaController.onAttention();
                }
            }
        });
    }

//    public void setData(LiveRoomFeedbackMessage bean) {
//        tv_allsceen_topview_title.setText(bean.live_title);
//        GlideUtils.loadImage(Utilities.getApplicationContext(), bean.anchor_avatar_url, civ_topview_headimg, -1, 0);
//        tv_topview_speakername.setText(bean.anchor_name);
//        //关注状态
//        changAttentionState(bean);
//    }


//    public void changAttentionState(LiveRoomFeedbackMessage bean) {
//        if ("1".equals(bean.anchor_is_follow)) {
//            tv_topview_attention.setText("已关注");
//            tv_topview_attention.setTextColor(Color.WHITE);
//            tv_topview_attention.setBackgroundResource(R.drawable.bg_liveallsceen_attentioned);
//        } else {
//            tv_topview_attention.setText("关注");
//            tv_topview_attention.setTextColor(Color.WHITE);
//            tv_topview_attention.setBackgroundResource(R.drawable.bg_liveallsceen_attention);
//        }
//    }

    public void updateQuality(String title) {
        tv_allsceen_topview_quality.setText(title);
    }

    public void showViewWithAnimation() {
        float height = getResources().getDimension(R.dimen.y180);
        setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "y", 0, height);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    public void hideViewWithAnimation() {
        float height = getResources().getDimension(R.dimen.y180);
        setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "y", height, 0);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    AllScreenTopView.this.setVisibility(View.GONE);
                } catch (Exception e) {
                    //正常容错逻辑，不需要上报
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.setDuration(150);
        objectAnimator.start();
    }
}
