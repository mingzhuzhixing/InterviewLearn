package com.v.module_recyclerview.swipe_refresh_layout.refreshview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.v.module_recyclerview.R;
import com.v.module_recyclerview.swipe_refresh_layout.drawable.VerticalMouldingDrawable;

/**
 * 自定义下拉刷新
 */
public class VerticalMouldingHeaderJson extends LinearLayout implements RefreshHeader {
    private LottieAnimationView mLottieView;
    private TextView mTextView;
    private String loadingMessage = "有书，终身教育服务平台";
    private OnRefreshTextHelper onRefreshTextHelper;

    public VerticalMouldingHeaderJson(Context context) {
        super(context);
        initView(context);
    }

    public VerticalMouldingHeaderJson(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VerticalMouldingHeaderJson(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public VerticalMouldingHeaderJson setRefreshTextHelper(OnRefreshTextHelper onRefreshTextHelper) {
        this.onRefreshTextHelper = onRefreshTextHelper;
        return this;
    }

    public VerticalMouldingHeaderJson setAnimationWidth(int width) {
        return this;
    }

    public VerticalMouldingHeaderJson setAnimationColor(int color) {
        return this;
    }

    public VerticalMouldingHeaderJson setTextViewColor(int color) {
        if (this.mTextView != null) {
            this.mTextView.setTextColor(color);
        }
        return this;
    }

    public VerticalMouldingHeaderJson setTextViewVisible(boolean isVisible) {
        if (this.mTextView != null) {
            if (isVisible) {
                this.mTextView.setVisibility(VISIBLE);
            } else {
                this.mTextView.setVisibility(GONE);
            }
        }
        return this;
    }

    public VerticalMouldingHeaderJson setLoadingMessage(String message) {

        this.loadingMessage = message;
        return this;
    }

    public VerticalMouldingHeaderJson setAnimationWidthRowWidth(int width) {
        return this;
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.loading_vm_header_json, this);
        mLottieView = (LottieAnimationView) view.findViewById(R.id.img_loading_header);
        mTextView = (TextView) view.findViewById(R.id.tv_loading_header);
        mLottieView.setRepeatCount(LottieDrawable.INFINITE);
        mLottieView.setAnimation("lottie/shuaxin.json");
        mTextView.setTextColor(Color.parseColor("#808080"));
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
//        final View progressView = mImageView;
//        if (progressView.getVisibility() != VISIBLE) {
//            progressView.setVisibility(VISIBLE);
//            Drawable drawable = mImageView.getDrawable();
//            if (drawable instanceof Animatable) {
//                ((Animatable) drawable).start();
//            } else {
//                progressView.animate().rotation(36000).setDuration(100000);
//            }
//        }
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
//        final View progressView = mImageView;
//        if (progressView.getVisibility() != VISIBLE) {
//            progressView.setVisibility(VISIBLE);
//            Drawable drawable = mImageView.getDrawable();
//            if (drawable instanceof Animatable) {
//                ((Animatable) drawable).start();
//            } else {
//                progressView.animate().rotation(36000).setDuration(100000);
//            }
//        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
//        final View progressView = mImageView;
//        Drawable drawable = mImageView.getDrawable();
//        if (drawable instanceof Animatable) {
//            if (((Animatable) drawable).isRunning()) {
//                ((Animatable) drawable).stop();
//            }
//        } else {
//            progressView.animate().rotation(0).setDuration(0);
//        }
//        progressView.setVisibility(GONE);
        return 500;//延迟500毫秒之后再弹回
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mLottieView != null && mLottieView.isAnimating()) {
            mLottieView.cancelAnimation();
        }
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
//            case None:
            case PullDownToRefresh:
//            case ReleaseToRefresh:  //开始假造
//            case PullDownCanceled: //取消加载
                mTextView.setText(onRefreshTextHelper != null ? onRefreshTextHelper.getText() : loadingMessage);
                if (mLottieView != null && !mLottieView.isAnimating()) {
                    mLottieView.playAnimation();
                }
                break;
            case RefreshFinish:
                if (onRefreshTextHelper == null) {
                    mTextView.setText("加载完成");
                }
                if (mLottieView != null && mLottieView.isAnimating()) {
                    mLottieView.cancelAnimation();
                }
                break;
        }
    }


}