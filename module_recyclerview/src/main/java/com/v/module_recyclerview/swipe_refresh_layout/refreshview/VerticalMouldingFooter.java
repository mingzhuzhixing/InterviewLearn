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

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.v.module_recyclerview.R;
import com.v.module_recyclerview.swipe_refresh_layout.drawable.VerticalMouldingDrawable;

/**
 *自定义上拉加载
 */
public class VerticalMouldingFooter extends LinearLayout implements RefreshFooter {
    private ImageView img_footer;
    private ImageView mImageView;
    private TextView mTextView;
    private VerticalMouldingDrawable footerDrawable;
    private String loadingMessage = "书友请稍等...";
    private String loadingFinsh = "加载完成";
    private String nomoreMessage = "到底啦，看看其他一样精彩哦";

    public VerticalMouldingFooter(Context context) {
        super(context);
        initView(context);
    }

    public VerticalMouldingFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VerticalMouldingFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public VerticalMouldingFooter setAnimationWidth(int width) {
        if (this.footerDrawable != null) {
            this.footerDrawable.setW(width);
        }
        return this;
    }

    public VerticalMouldingFooter setAnimationColor(int color) {
        if (this.footerDrawable != null) {
            this.footerDrawable.setColor(color);
        }
        return this;
    }

    public VerticalMouldingFooter setTextViewColor(int color) {

        if (this.mTextView != null) {
            this.mTextView.setTextColor(color);
        }
        return this;
    }

    public VerticalMouldingFooter setLoadingMessage(String message) {

        this.loadingMessage = message;
        return this;
    }

    //底部图片可见状态
    public VerticalMouldingFooter setSlognImgVisible(boolean isvisible) {

        if (isvisible) {
            this.img_footer.setVisibility(View.VISIBLE);
        } else {
            this.img_footer.setVisibility(View.GONE);
        }
        return this;
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.loading_vm_footer, this);
        mImageView = (ImageView) view.findViewById(R.id.img_loading_footer);
        mTextView = (TextView) view.findViewById(R.id.tv_loading_footer);
        img_footer = (ImageView) view.findViewById(R.id.img_footer);
        footerDrawable = new VerticalMouldingDrawable();
        footerDrawable.setColor(0x808080);
        footerDrawable.setW(context.getResources().getDimension(R.dimen.x4));
        footerDrawable.setRowW(context.getResources().getDimension(R.dimen.x4));
        mImageView.setImageDrawable(footerDrawable);

        mTextView.setTextColor(Color.parseColor("#808080"));
        mArrowView = new ImageView(context);
    }

    ImageView mArrowView;
    protected boolean mNoMoreData = false;

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData;
//            final View arrowView = mArrowView;
            if (noMoreData) {
//                mTextView.setText(nomoreMessage);
                mTextView.setText("");
                img_footer.setVisibility(VISIBLE);
                mImageView.setVisibility(GONE);
            } else {
                img_footer.setVisibility(GONE);
            }
        }
        return true;
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
        if (mNoMoreData) return;
        final View progressView = mImageView;
        if (progressView.getVisibility() != VISIBLE) {
            progressView.setVisibility(VISIBLE);
            Drawable drawable = mImageView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            } else {
                progressView.animate().rotation(36000).setDuration(100000);
            }
        }
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        if (mNoMoreData) return;
        final View progressView = mImageView;
        mImageView.setVisibility(VISIBLE);
        if (progressView.getVisibility() != VISIBLE) {
            progressView.setVisibility(VISIBLE);
            Drawable drawable = mImageView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            } else {
                progressView.animate().rotation(36000).setDuration(100000);
            }
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (mNoMoreData) return 500;
        final View progressView = mImageView;
        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof Animatable) {
            if (((Animatable) drawable).isRunning()) {
                ((Animatable) drawable).stop();
            }
        } else {
            progressView.animate().rotation(0).setDuration(0);
        }
        progressView.setVisibility(GONE);
        mTextView.setText(loadingFinsh);
        return 0;//延迟500毫秒之后再弹回
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        final Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof Animatable) {
            if (((Animatable) drawable).isRunning()) {
                ((Animatable) drawable).stop();
            }
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
//        LogUtil.e("dada_ui","newState:"+newState);
        if (mNoMoreData) return;
        final Drawable drawable = mImageView.getDrawable();

        switch (newState) {
            case None:
                break;
            case Loading:
            case LoadReleased:
                mTextView.setText(loadingMessage);
                if (drawable instanceof Animatable) {
                    if (!((Animatable) drawable).isRunning()) {
                        ((Animatable) drawable).start();
                    }
                }
                break;
            case ReleaseToLoad:
//                mTextView.setText(nomoreMessage);
                mTextView.setText("");
                img_footer.setVisibility(VISIBLE);
                if (drawable instanceof Animatable) {
                    if (!((Animatable) drawable).isRunning()) {
                        ((Animatable) drawable).stop();
                    }
                }
                break;
        }
    }
}
