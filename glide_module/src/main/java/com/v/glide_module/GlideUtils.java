package com.v.glide_module;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.Executors;

/**
 * author:xw
 * Date:2018-08-22 10:27
 * Description:
 */
public class GlideUtils {
    public static int SCALETYPE_FITCENTER = 0;
    public static int SCALETYPE_CENTERCROP = 1;
    public static int SCALETYPE_NOTHING = 2;
    public static final int DEFALT_ID = R.color.gray;

    public static void loadImage(Activity activity, String url, ImageView iv, int ScaleType, int error_resid) {
        try {
            if (activity == null || activity.isDestroyed()) {
                return;
            }
            RequestBuilder<Bitmap> load = Glide.with(activity).asBitmap().load(url);
            if (SCALETYPE_FITCENTER == ScaleType) {
                load.fitCenter();
            } else if (SCALETYPE_CENTERCROP == ScaleType) {
                load.centerCrop();
            } else {
                load.centerCrop();
            }
            if (error_resid != 0) {
                load.placeholder(error_resid);
            } else {
                load.placeholder(DEFALT_ID);
            }
            load.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            //load.skipMemoryCache(true);
            load.dontAnimate().into(iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImage(Context context, String url, ImageView iv, int ScaleType, int error_resid) {
        try {
            if (context == null) {
                return;
            }
            RequestBuilder<Drawable> load = Glide.with(context).load(url);
            if (SCALETYPE_FITCENTER == ScaleType) {
                load.fitCenter();
            } else if (SCALETYPE_CENTERCROP == ScaleType) {
                load.centerCrop();
            } else if (SCALETYPE_NOTHING == ScaleType) {

            } else {
                load.centerCrop();
            }
            if (error_resid != 0) {
                load.placeholder(error_resid);
            } else {
                load.placeholder(DEFALT_ID);
            }
            load.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            load.dontAnimate().into(iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageForTarget(Activity activity, String url, int error_resid, Target<Bitmap> target) {
        byte[] decode = null;
        if (isBase64Img(url)) {
            url = url.split(",")[1];
            decode = Base64.decode(url, Base64.DEFAULT);
        }
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        RequestBuilder<Bitmap> bitmapTypeRequest = Glide.with(activity).asBitmap().load(decode == null ? url : decode);
        if (error_resid != 0) {
            bitmapTypeRequest.placeholder(error_resid);
        } else {
            bitmapTypeRequest.placeholder(DEFALT_ID);
        }
        bitmapTypeRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        bitmapTypeRequest.dontAnimate();
        bitmapTypeRequest.into(target);
    }

    public static void loadImageForTarget(Context context, String url, int error_resid, Target<Bitmap> target) {
        byte[] decode = null;
        if (isBase64Img(url)) {
            url = url.split(",")[1];
            decode = Base64.decode(url, Base64.DEFAULT);
        }
        if (context == null) {
            return;
        }
        RequestBuilder<Bitmap> bitmapTypeRequest = Glide.with(context).asBitmap().load(decode == null ? url : decode);
        if (error_resid != 0) {
            bitmapTypeRequest.placeholder(error_resid);
        } else {
            bitmapTypeRequest.placeholder(DEFALT_ID);
        }
        bitmapTypeRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        bitmapTypeRequest.dontAnimate();
        bitmapTypeRequest.into(target);
    }


    /**
     * 清除内存缓存
     *
     * @param context
     */
    public static void clearImageMemory(Context context) {
        if (context != null) {
            try {
                Glide.get(context).clearMemory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearDisImage(Context context) {
        if (context != null) {
            try {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (context != null) {
                            Glide.get(context).clearDiskCache();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }


    public static boolean isBase64Img(String imgurl) {
        if (!TextUtils.isEmpty(imgurl) && (imgurl.startsWith("data:image/png;base64,")
                || imgurl.startsWith("data:image/*;base64,") || imgurl.startsWith("data:image/jpg;base64,")
        )) {
            return true;
        }
        return false;
    }

    public static void loadRoundImage2(final Context context, final int cornerRadius, String url, int resId, final ImageView imageView) {
        try {
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .placeholder(resId)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //设置缓存
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            try {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                circularBitmapDrawable.setCornerRadius(cornerRadius); //设置圆角弧度
                                imageView.setImageDrawable(circularBitmapDrawable);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }

    }

    public static void loadRoundImage(final Context context, final int cornerRadius, String url, int resId, final ImageView imageView) {
        try {
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .placeholder(resId)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //设置缓存
                    .centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            //super.setResource(resource);
                            try {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                circularBitmapDrawable.setCornerRadius(cornerRadius); //设置圆角弧度
                                imageView.setImageDrawable(circularBitmapDrawable);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }

    }

    public static void loadOvalImage(final Activity activity, String url, int resId, final ImageView imageView) {
        if (activity != null && !activity.isDestroyed()) {
            Glide.with(activity)
                    .asBitmap()
                    .load(url)
                    .placeholder(resId)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //设置缓存
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            try {
                                if (activity == null || activity.isDestroyed()) {
                                    return;
                                }
                                Bitmap src = resource;
                                Bitmap dst;
                                //将长方形图片裁剪成正方形图片
                                if (src.getWidth() >= src.getHeight()) {
                                    dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2, 0, src.getHeight(), src.getHeight());
                                } else {
                                    dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2, src.getWidth(), src.getWidth());
                                }
                                //6.0.3 修改测试奔溃
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(activity.getResources(), dst);
                                circularBitmapDrawable.setCornerRadius(dst.getWidth() + 50); //设置圆角弧度
                                imageView.setImageDrawable(circularBitmapDrawable);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

    }


    public static void loadOvalImage(final Context context, String url, int resId, final ImageView imageView) {
        try {
            if (context != null) {
                Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .placeholder(resId)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //设置缓存
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                super.setResource(resource);
                                try {
                                    Bitmap src = resource;
                                    Bitmap dst;
                                    //将长方形图片裁剪成正方形图片
                                    if (src.getWidth() >= src.getHeight()) {
                                        dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2, 0, src.getHeight(), src.getHeight());
                                    } else {
                                        dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2, src.getWidth(), src.getWidth());
                                    }
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.create(context.getResources(), dst);
                                    circularBitmapDrawable.setCornerRadius(dst.getWidth() + 50); //设置圆角弧度
                                    imageView.setImageDrawable(circularBitmapDrawable);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }
    }


    /**
     * 图片高度自适应加载
     *
     * @param context
     * @param url
     * @param resId
     * @param imageView
     * @param width     图片固定的宽度
     */
    public static void loadOImageWithWidth(final Context context, String url, int resId, final ImageView imageView, int width) {
        try {
            if (context != null) {
                Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .placeholder(resId)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //
                        .centerCrop()
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                super.setResource(resource);
                                try {
                                    Bitmap src = resource;
//
                                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                                    layoutParams.width = width;
                                    layoutParams.height = (int) (width * (src.getHeight() * 1f / src.getWidth()));
                                    imageView.setLayoutParams(layoutParams);
                                    imageView.setImageBitmap(src);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }


    }

    public static void loadImageBitmap(Activity activity, String url, ImageView iv, int ScaleType, int error_resid) {
        try {
            RequestBuilder<Bitmap> load = Glide.with(activity).asBitmap().load(url);

            if (SCALETYPE_FITCENTER == ScaleType) {
                load.fitCenter();
            } else if (SCALETYPE_CENTERCROP == ScaleType) {
                load.centerCrop();
            } else {
                load.centerCrop();
            }
            if (error_resid != 0) {
                load.placeholder(error_resid);
            } else {
                load.placeholder(DEFALT_ID);
            }
            load.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            load.dontAnimate().into(iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载带边框的圆形图片
     *
     * @param context
     * @param url
     * @param view
     * @param borderColor
     * @param borderWidth border width(dp)
     * @param defaultImg
     */
    public static void loadCircleImage(Context context, String url, ImageView view, int borderColor, float borderWidth, int defaultImg) {
        if (context == null || TextUtils.isEmpty(url) || view == null) return;
        Glide.with(context).load(url).centerCrop().placeholder(defaultImg <= 0 ? R.drawable.zhanweitu04 : defaultImg)
                .transform(new GlideCircleTransform(context, borderWidth <= 0 ? 1 : borderWidth, borderColor))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate().into(view);
    }
}
