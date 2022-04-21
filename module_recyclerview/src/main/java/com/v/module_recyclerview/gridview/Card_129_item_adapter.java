package com.v.module_recyclerview.gridview;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.v.module_glide.GlideUtils;
import com.v.module_recyclerview.R;

import java.util.logging.Logger;

import butterknife.BindView;

/**
 * Card_129_item_adapter
 *
 * @author zhuming
 */
public class Card_129_item_adapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * 图片展示类型
     * 1 表示长方形大图
     * 2 表示方图
     */
    private int type;
    private int largeImageMaxHeight = 0;
    private int largeImageMinHeight = 0;
    private int largeImageWidth = 0;
    private int smallImageSize = 0;

    public void setType(int type) {
        this.type = type;
    }

    private Context mContext;


    public Card_129_item_adapter(Activity context, int layoutResId) {
        super(layoutResId);
        mContext = context;
        largeImageMaxHeight = context.getResources().getDimensionPixelSize(R.dimen.y453);
        largeImageMinHeight = context.getResources().getDimensionPixelSize(R.dimen.y234);
        largeImageWidth = context.getResources().getDimensionPixelSize(R.dimen.x340);
        smallImageSize = context.getResources().getDimensionPixelSize(R.dimen.y224);
    }

    public Card_129_item_adapter(Activity context, int layoutResId, float imageWidth) {
        super(layoutResId);
        mContext = context;
        largeImageMaxHeight = context.getResources().getDimensionPixelSize(R.dimen.y453);
        largeImageMinHeight = context.getResources().getDimensionPixelSize(R.dimen.y234);
        largeImageWidth = context.getResources().getDimensionPixelSize(R.dimen.x340);
        smallImageSize = (int) imageWidth;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, String s) {
        RoundedImageView rvImage = holder.getView(R.id.rv_129_image);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) rvImage.getLayoutParams();
        int placeImage = R.drawable.shape_bg_faf6f6_r4;
        int errorImage = R.drawable.quesheng_error;
        if (type == 1) {
            // 表示长方形大图
            layoutParams.width = largeImageWidth;
            rvImage.setMinimumHeight(largeImageMinHeight);
            rvImage.setMaxHeight(largeImageMaxHeight);
            try {
                GlideUtils.loadBitmap(mContext, s, new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            if (resource == null) {
                                rvImage.setImageResource(errorImage);
                                return;
                            }
                            //获取当前图片的高
                            int height = resource.getHeight();
                            if (height > largeImageMaxHeight) {
                                layoutParams.height = largeImageMaxHeight;
                            } else if (height < largeImageMinHeight) {
                                layoutParams.height = largeImageMinHeight;
                            } else {
                                layoutParams.height = height;
                            }
                            rvImage.setLayoutParams(layoutParams);
                            rvImage.setImageBitmap(resource);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        try {
                            rvImage.setImageResource(errorImage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                rvImage.setImageResource(errorImage);
            }
        } else {
            // 表示方图
            layoutParams.height = smallImageSize;
            layoutParams.width = smallImageSize;
            rvImage.setLayoutParams(layoutParams);
            GlideUtils.loadImage(mContext, s, rvImage, placeImage, R.drawable.quesheng_error);
        }
    }
}
