package com.v.url_module.like_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.util.Pools;


import com.v.url_module.PoolViews;
import com.v.url_module.like_view.evaluator.CurveEvaluatorRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @ClassName: AnimationLayout
 * @Author: KaiSenGao
 * @CreateDate: 2020/12/24 10:58
 * @Description:
 */
public abstract class AnimationLayout extends FrameLayout implements IAnimationLayout {

    protected final Random mRandom = new Random();

    protected int mViewWidth, mViewHeight;

    protected float mPicWidth, mPicHeight;

    protected List<AnimatorSet> mAnimatorSets;

    protected CurveEvaluatorRecord mEvaluatorRecord;

    protected Pools.SimplePool<View> mSimplePool;

    public AnimationLayout(@NonNull Context context) {
        this(context, null);
    }

    public AnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 初始化
        this.init();
    }

    /**
     * 初始化
     */
    protected void init() {
        // 动画集合
        if (mAnimatorSets == null) {
            this.mAnimatorSets = new ArrayList<>();
        }
        // 贝塞尔曲线缓存类
        if (mEvaluatorRecord == null) {
            this.mEvaluatorRecord = new CurveEvaluatorRecord();
        }
        if (mSimplePool == null) {
            this.mSimplePool = new Pools.SimplePool<>(50);
        }
    }

    /**
     * 获取资源图片信息
     *
     * @param resId 资源Id
     */
    public void getPictureInfo(@DrawableRes int resId) {
        // 获取图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只读取图片，不加载到内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), resId, options);
        // 获取图片的宽高
        this.mPicWidth = options.outWidth;
        this.mPicHeight = options.outHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mViewWidth = getMeasuredWidth();
        this.mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.mViewWidth = getMeasuredWidth();
        this.mViewHeight = getMeasuredHeight();
    }

    /**
     * 贝塞尔曲线路径更新事件
     */
    protected static class CurveUpdateLister implements ValueAnimator.AnimatorUpdateListener {

        private final View mChild;

        protected CurveUpdateLister(View child) {
            this.mChild = child;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF value = (PointF) animation.getAnimatedValue();
            this.mChild.setX(value.x);
            this.mChild.setY(value.y);
            this.mChild.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    /**
     * 动画结束监听器,用于释放无用的资源
     */
    protected class AnimationEndListener extends AnimatorListenerAdapter {

        private View mChild;

        private AnimatorSet mAnimatorSet;

        protected AnimationEndListener(View child, AnimatorSet animatorSet) {
            try {
                this.mChild = child;
                this.mAnimatorSet = animatorSet;
                // 缓存
                mAnimatorSets.add(mAnimatorSet);
            } catch (Exception e) {
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            try {
                // 移除View
                removeView(mChild);
                // 移除缓存
                mAnimatorSets.remove(mAnimatorSet);
                mSimplePool.release(mChild);
                // 释放View
                //this.mChild = null;
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            // 销毁资源
            this.destroy();
        } catch (Exception e) {
        }
    }

    /**
     * 销毁资源
     */
    public void destroy() {
        // 移除所有View
        this.removeAllViews();
        // 取消动画 释放资源
        if (mAnimatorSets != null) {
            // 取消动画
            for (AnimatorSet animatorSet : mAnimatorSets) {
                animatorSet.getListeners().clear();
                animatorSet.cancel();
            }
            this.mAnimatorSets.clear();
            this.mAnimatorSets = null;
        }
        // 清空缓存路径
        if (mEvaluatorRecord != null) {
            this.mEvaluatorRecord.destroy();
            this.mEvaluatorRecord = null;
        }
    }
}