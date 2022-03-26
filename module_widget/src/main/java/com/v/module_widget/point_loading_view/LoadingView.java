package com.v.module_widget.point_loading_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

import com.v.module_utils.DensityUtils;

/**
 * ClassName: LoadingView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_widget.point_loading_view
 * @date 2022/3/25 11:33 上午
 */
public class LoadingView extends RelativeLayout {
    private CircleView mLeftView, mMiddleView, mRightView;
    //移动距离
    private int mTranslationDistance = 20;
    private final int DURATION_TIME = 300;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);
        mTranslationDistance = DensityUtils.dip2px(context, mTranslationDistance);

        mLeftView = getCircleView(context, Color.RED);
        mMiddleView = getCircleView(context, Color.BLUE);
        mRightView = getCircleView(context, Color.GREEN);

        addView(mLeftView);
        addView(mRightView);
        addView(mMiddleView);

        post(new Runnable() {
            @Override
            public void run() {
                //让布局实例化好只有再去开启动画
                outerAnimation();
            }
        });
    }

    public CircleView getCircleView(Context context, int color) {
        CircleView circleView = new CircleView(context, color);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                DensityUtils.dip2px(context, 10), DensityUtils.dip2px(context, 10));
        layoutParams.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(layoutParams);
        return circleView;
    }

    /**
     * 向外运动动画
     */
    private void outerAnimation() {
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftView, "translationX", 0, -mTranslationDistance);
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightView, "translationX", 0, mTranslationDistance);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(DURATION_TIME);
        animatorSet.setInterpolator(new DecelerateInterpolator(2f));
        animatorSet.playTogether(leftAnimator, rightAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                innerAnimation();
            }
        });
        animatorSet.start();
    }

    /**
     * 向里运动动画
     */
    private void innerAnimation() {
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftView, "translationX", -mTranslationDistance, 0);
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightView, "translationX", mTranslationDistance, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(DURATION_TIME);
        animatorSet.setInterpolator(new AccelerateInterpolator(2f));
        animatorSet.playTogether(leftAnimator, rightAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //修改颜色
                exchangeColor(mLeftView);
                exchangeColor(mMiddleView);
                exchangeColor(mRightView);

                outerAnimation();
            }
        });
        animatorSet.start();
    }

    /**
     * 改版颜色
     */
    public void exchangeColor(CircleView view) {
        switch (view.getCurrentColor()) {
            case Color.RED:
                view.exchangeColor(Color.BLUE);
                break;
            case Color.BLUE:
                view.exchangeColor(Color.GREEN);
                break;
            case Color.GREEN:
                view.exchangeColor(Color.RED);
                break;
        }
    }

    //优化。。。。。
    //动画释放
}
