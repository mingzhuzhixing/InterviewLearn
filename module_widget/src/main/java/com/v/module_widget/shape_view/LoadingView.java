package com.v.module_widget.shape_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.v.module_utils.DensityUtils;
import com.v.module_widget.R;

public class LoadingView extends LinearLayout {
    //上面的形状
    private ShapeView mShapeView;
    //中间的阴影
    private View mShadowView;
    //动画执行的时间
    private final int ANIMATOR_DURATION = 350;

    private int mTranslationYDistance = 0;
    //是否停止动画
    private boolean mIsStopAnimator = false;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTranslationYDistance = DensityUtils.dip2px(getContext(), 80);
        initLayout();
    }

    private void initLayout() {
        //第一种实例化view
//        View view = inflate(getContext(), R.layout.ui_loading_view, null);
//        addView(view);

        //第二种实例化view
        //this 代表 把ui_loading_view布局 加载到LoadingView中
        inflate(getContext(), R.layout.ui_loading_view, this);
        mShapeView = findViewById(R.id.shape_view);
        mShadowView = findViewById(R.id.shadow_view);
        fallAnimator();
    }

    /**
     * 开始下落动画
     * <p>
     * android:interpolator="@android:anim/accelerate_interpolator" 设置动画为加速动画(动画播放中越来越快)
     * android:interpolator="@android:anim/decelerate_interpolator" 设置动画为减速动画(动画播放中越来越慢)
     * android:interpolator="@android:anim/accelerate_decelerate_interpolator" 设置动画为先加速在减速(开始速度最快 逐渐减慢)
     * android:interpolator="@android:anim/anticipate_interpolator" 先反向执行一段，然后再加速反向回来（相当于我们弹簧，先反向压缩一小段，然后在加速弹出）
     * android:interpolator="@android:anim/anticipate_overshoot_interpolator" 同上先反向一段，然后加速反向回来，执行完毕自带回弹效果（更形象的弹簧效果）
     * android:interpolator="@android:anim/bounce_interpolator" 执行完毕之后会回弹跳跃几段（相当于我们高空掉下一颗皮球，到地面是会跳动几下）
     * android:interpolator="@android:anim/cycle_interpolator" 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)
     * android:interpolator="@android:anim/linear_interpolator" 线性均匀改变
     * android:interpolator="@android:anim/overshoot_interpolator" 加速执行，结束之后回弹
     * <p>
     * animation.setInterpolator(new AccelerateInterpolator());
     * animation.setInterpolator(new DecelerateInterpolator());
     * animation.setInterpolator(new AccelerateDecelerateInterpolator());
     * animation.setInterpolator(new AnticipateInterpolator());
     * animation.setInterpolator(new AnticipateOvershootInterpolator());
     * animation.setInterpolator(new BounceInterpolator());
     * animation.setInterpolator(new CycleInterpolator(2));
     * animation.setInterpolator(new LinearInterpolator());
     * animation.setInterpolator(new OvershootInterpolator());
     */
    @SuppressLint("Recycle")
    private void fallAnimator() {
        if (mIsStopAnimator) {
            return;
        }
        //动画作用在谁身上

        //下落移动动画
        ObjectAnimator tranAnimator = ObjectAnimator.ofFloat(mShapeView, "translationY", 0, mTranslationYDistance);
//        tranAnimator.setDuration(ANIMATOR_DURATION);
//        tranAnimator.start();
        //配合中间阴影缩小
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1.0f, 0.3f);
//        scaleAnimator.setDuration(ANIMATOR_DURATION);
//        scaleAnimator.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(tranAnimator, scaleAnimator);
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        //下落完之后上抛
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //super.onAnimationEnd(animation);
                //改变形状
                mShapeView.exchange();
                //开始上抛
                starUpAnimation();
            }
        });
        animatorSet.start();
    }

    /**
     * 执行上抛动画
     */
    private void starUpAnimation() {
        if (mIsStopAnimator) {
            return;
        }
        ObjectAnimator tranAnimator = ObjectAnimator.ofFloat(mShapeView, "translationY", mTranslationYDistance, 0);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.3f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(tranAnimator, scaleAnimator);
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        //上抛完之后下落
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //super.onAnimationStart(animation);
                //开始旋转
                starRotationAnimator();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //super.onAnimationEnd(animation);
                fallAnimator();
            }
        });
        animatorSet.start();
    }

    /**
     * 旋转
     */
    private void starRotationAnimator() {
        ObjectAnimator rotationAnimator = null;
        switch (mShapeView.getCurrentShape()) {
            case circle:
                //画圆形
                rotationAnimator = ObjectAnimator.ofFloat(mShapeView, "rotation", 0.0f, 0.0f);
                break;

            case square:
                //画正方形
                rotationAnimator = ObjectAnimator.ofFloat(mShapeView, "rotation", 0.0f, 180.0f);
                break;

            case triangle:
                //画三角
                rotationAnimator = ObjectAnimator.ofFloat(mShapeView, "rotation", 0.0f, -120.0f);
                break;
        }

        rotationAnimator.setDuration(ANIMATOR_DURATION);
        rotationAnimator.setInterpolator(new DecelerateInterpolator());
        rotationAnimator.start();
    }
    //正方型180  三角形 60

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);//不要在去摆放和计算，少走一些系统的方法
        if (visibility == VISIBLE) {
            return;
        }
        //清理动画
        mShapeView.clearAnimation();
        mShadowView.clearAnimation();

        //把loadingView 从父布局移动
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this); // 从父布局移除
            removeAllViews(); //移除自己所有的View
        }

        mIsStopAnimator = true;
    }
}
