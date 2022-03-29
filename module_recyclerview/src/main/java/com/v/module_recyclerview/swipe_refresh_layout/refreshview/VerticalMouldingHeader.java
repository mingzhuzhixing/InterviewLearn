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
public class VerticalMouldingHeader extends LinearLayout implements RefreshHeader {
    private ImageView mImageView;
    private TextView mTextView;
    private VerticalMouldingDrawable headerDrawable;
    private String loadingMessage="有书，终身教育服务平台";
    private OnRefreshTextHelper onRefreshTextHelper;

    public VerticalMouldingHeader(Context context) {
        super(context);
        initView(context);
    }

    public VerticalMouldingHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VerticalMouldingHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public VerticalMouldingHeader setRefreshTextHelper(OnRefreshTextHelper onRefreshTextHelper){
        this.onRefreshTextHelper=onRefreshTextHelper;
        return  this;
    }

    public VerticalMouldingHeader setAnimationWidth(int width){
        if(this.headerDrawable!=null) {
            this.headerDrawable.setW(width);
        }
        return this;
    }

    public VerticalMouldingHeader setAnimationColor(int color){
        if(this.headerDrawable!=null) {
            this.headerDrawable.setColor(color);
        }
        return  this;
    }

    public VerticalMouldingHeader setTextViewColor(int color){
        if(this.mTextView!=null){
            this.mTextView.setTextColor(color);
        }
        return  this;
    }

    public VerticalMouldingHeader setTextViewVisible(boolean isVisible){
        if(this.mTextView!=null){
            if(isVisible){
                this.mTextView.setVisibility(VISIBLE);
            }else{
                this.mTextView.setVisibility(GONE);
            }
        }
        return  this;
    }

    public VerticalMouldingHeader setLoadingMessage(String message){

        this.loadingMessage=message;
        return  this;
    }

    public VerticalMouldingHeader setAnimationWidthRowWidth(int width){
        if(this.headerDrawable!=null) {
            this.headerDrawable.setRowW(width);
        }
        return  this;
    }

    private void initView(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.loading_vm_header, this);
        mImageView=(ImageView)view.findViewById(R.id.img_loading_footer);
        mTextView=(TextView) view.findViewById(R.id.tv_loading_footer);
        headerDrawable=new VerticalMouldingDrawable();
        headerDrawable.setColor(0x808080);
        headerDrawable.setW(context.getResources().getDimension(R.dimen.x4));
        headerDrawable.setRowW(context.getResources().getDimension(R.dimen.x5));
        mImageView.setImageDrawable(headerDrawable);
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
        final Drawable drawable = mImageView.getDrawable();

        switch (newState) {
//            case None:
            case PullDownToRefresh:
//            case ReleaseToRefresh:  //开始假造
//            case PullDownCanceled: //取消加载
                mTextView.setText(onRefreshTextHelper!=null?onRefreshTextHelper.getText():loadingMessage);
                if (drawable instanceof Animatable) {
                    if (!((Animatable) drawable).isRunning()) {
                        ((Animatable) drawable).start();
                    }
                }
                break;
            case RefreshFinish:
                if(onRefreshTextHelper==null) {
                    mTextView.setText("加载完成");
                }
                if (drawable instanceof Animatable) {
                    if (!((Animatable) drawable).isRunning()) {
                        ((Animatable) drawable).stop();
                    }
                }
                break;
        }
    }





}