package com.v.module_widget.love_layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.DrawableRes;

import com.v.module_widget.R;

import java.util.Random;

/**
 * 直播点赞效果
 */
public class LoveLayout extends RelativeLayout {
    //资源
    private final int[] mImageRes;

    //随机数
    private final Random mRandom;

    private int mWidth, mHeight;

    private int mPicWidth, mPicHeight;

    private final Interpolator[] mInterpolator;

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();

        mImageRes = new int[]{R.mipmap.ic_live_yellow_heart, R.mipmap.ic_live_smile_face_heart, R.mipmap.ic_live_red_heart,
                R.mipmap.ic_live_thumbs_up_yellow, R.mipmap.ic_live_smile_face, R.mipmap.ic_live_orange_heart,
                R.mipmap.ic_live_grin_face_eyes, R.mipmap.ic_live_smile_face_sunglas};

        mInterpolator = new Interpolator[]{new AccelerateDecelerateInterpolator(), new AccelerateInterpolator(),
                new DecelerateInterpolator(), new LinearInterpolator()};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取空间的宽高
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 添加一个点赞的view
     */
    public void addLove() {
        //添加一个Imageview在底部
        ImageView ivLove = new ImageView(getContext());
        // 随机获取一个资源
        int favorRes = mImageRes[mRandom.nextInt(mImageRes.length - 1)];
        //设置图片资源   随机
        ivLove.setImageResource(favorRes);
        //怎么添加到底部 中心
        RelativeLayout.LayoutParams params = createLayoutParams(favorRes);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        ivLove.setLayoutParams(params);
        addView(ivLove);

        AnimatorSet animator = getAnimator(ivLove);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //执行完毕之后移除
                removeView(ivLove);
            }
        });
        animator.start();
    }

    @SuppressLint("Recycle")
    private AnimatorSet getAnimator(ImageView imageView) {

        AnimatorSet allAnimatorSet = new AnimatorSet();

        AnimatorSet innerAnimator = new AnimatorSet();
        //添加效果：有放大和透明变化
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1.0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1.0f);
        innerAnimator.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        innerAnimator.setDuration(350);
        //innerAnimator.start();

        //运行路径动画  playSequentially 按顺序执行
        allAnimatorSet.playSequentially(innerAnimator, getBezierAnimator(imageView));

        return allAnimatorSet;
    }

    /**
     * 这个路径不是一个简单的路径
     */
    private Animator getBezierAnimator(ImageView imageView) {
        PointF point0 = new PointF(mWidth / 2.0f - mPicWidth / 2.0f, mHeight - mPicHeight);
        PointF point1 = getPointF(1);
        PointF point2 = getPointF(2);
        PointF point3 = new PointF(mRandom.nextInt(mWidth - mPicWidth), 0);
        LoveTypeEvaluator typeEvaluator = new LoveTypeEvaluator(point1, point2);
        //ofFloat 第一个参数 LoveTypeEvaluator  第二个参数 p0  第三个参数 p3
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(typeEvaluator, point0, point3);
        //加一些随机的差值器
        bezierAnimator.setInterpolator(mInterpolator[mRandom.nextInt(mInterpolator.length - 1)]);
        bezierAnimator.setDuration(2000);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                //透明度
                float t = animation.getAnimatedFraction();
                if (pointF != null && imageView != null) {
                    imageView.setX(pointF.x);
                    imageView.setY(pointF.y);
                    imageView.setAlpha(1 - t + 0.2f);
                }
            }
        });
        return bezierAnimator;
    }

    private PointF getPointF(int index) {
        return new PointF(mRandom.nextInt(mWidth - mPicWidth), mRandom.nextInt(mHeight / 2) + (index - 1) * (mHeight / 2.0f));
    }

    /**
     * 生成 配置参数
     */
    private RelativeLayout.LayoutParams createLayoutParams(int crystalLeaf) {
        // 获取图片信息
        this.getPictureInfo(crystalLeaf);
        // 初始化布局参数
        return new RelativeLayout.LayoutParams((int) mPicWidth, (int) mPicHeight);
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
}
