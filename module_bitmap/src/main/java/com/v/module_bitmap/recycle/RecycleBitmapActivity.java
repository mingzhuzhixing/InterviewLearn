package com.v.module_bitmap.recycle;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.v.module_bitmap.R;
import com.v.module_bitmap.R2;
import com.v.module_bitmap.utils.BitmapUtils;
import com.v.module_glide.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName: RecycleBitmapActiviy
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_bitmap.load
 * @date 2022/11/13 13:21
 */
@SuppressLint("NonConstantResourceId")
public class RecycleBitmapActivity extends AppCompatActivity {

    @BindView(R2.id.img)
    public ImageView ivCover;

    private final String[] array = {"https://img0.baidu.com/it/u=985192759,2265250910&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=333",
            "https://img2.baidu.com/it/u=220990409,124547830&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://img0.baidu.com/it/u=4051233098,4122077873&fm=253&fmt=auto&app=120&f=JPEG?w=1024&h=682",
            "https://img0.baidu.com/it/u=1536568586,2855078970&fm=253&fmt=auto&app=138&f=JPEG?w=311&h=500",
            "https://img0.baidu.com/it/u=3712997108,442011921&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://img1.baidu.com/it/u=2861109323,1369751855&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=625",
            "https://img1.baidu.com/it/u=4095883048,1780243536&fm=253&fmt=auto&app=138&f=JPEG?w=450&h=780",
            "https://img0.baidu.com/it/u=2362141127,3794652085&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://img0.baidu.com/it/u=2627496060,1933351908&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://img0.baidu.com/it/u=76521878,3199938898&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://img0.baidu.com/it/u=2866200409,4132400541&fm=253&fmt=auto&app=120&f=JPEG?w=450&h=780",
            "https://img1.baidu.com/it/u=3796593454,4087161325&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg5.51tietu.net%2Fpic%2F2019-082004%2F5gbe3mjih1t5gbe3mjih1t.jpg",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg5.51tietu.net%2Fpic%2F2019-082122%2F1k1rpqlfcqd1k1rpqlfcqd.jpg",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2F1111%2F03041Q50536%2F1P304150536-7.jpg"};

    private Bitmap bitmapCut = null;

    private int index = 0;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bitmap);
        unbinder = ButterKnife.bind(this);

        String posterPath = "https://avatar.youshu.cc/rd/wx/act/20200806/5f2ba19e788f62278.jpg";
        GlideUtils.loadImageForTarget(this, posterPath, R.drawable.zhanweitu13, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> glideAnimation) {
                try {
                    if (ivCover == null) {
                        return;
                    }
                    if (null == resource) {
                        ivCover.setImageResource(R.drawable.zhanweitu13);
                        return;
                    }
                    int width = resource.getWidth();
                    int height = resource.getHeight();
                    try {
                        if (bitmapCut != null && !bitmapCut.isRecycled()) {
                            bitmapCut.recycle();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bitmap afterBitmap = BitmapUtils.compressBySampleSize(resource, 2);
                    Bitmap afterBitmap2 = BitmapUtils.compressByQuality(resource, false);
                    Bitmap bitmapConfig = BitmapUtils.compressByConfig(resource, Bitmap.Config.ARGB_4444);

                    if (width < height) {
                        bitmapCut = Bitmap.createBitmap(afterBitmap, 0, 0, width, width);
                    } else if (width > height) {
                        bitmapCut = Bitmap.createBitmap(resource, 0, 0, height, height);
                    } else {
                        bitmapCut = Bitmap.createBitmap(resource);
                    }
                    Log.i("wechat", "压缩后图片的大小5:" + (bitmapCut.getByteCount() / 1024) + "kb 宽度为" + bitmapCut.getWidth() + "高度为" + bitmapCut.getHeight());

                    if (null == bitmapCut) {
                        ivCover.setImageResource(R.drawable.zhanweitu13);
                    } else {
                        ivCover.setImageBitmap(bitmapCut);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                try {
                    ivCover.setImageResource(R.drawable.zhanweitu13);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void showBimap(View view) {
        if (index >= array.length) {
            index = 0;
        }
        String url = array[index];
        index++;
        GlideUtils.loadImageForTarget(this, url, R.drawable.zhanweitu13, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> glideAnimation) {
                try {
                    if (isDestroyed() || ivCover == null) {
                        return;
                    }
                    if (null == resource) {
                        ivCover.setImageResource(R.drawable.zhanweitu13);
                        return;
                    }
                    int width = resource.getWidth();
                    int height = resource.getHeight();
                    if (bitmapCut != null && !bitmapCut.isRecycled()) {
                        Log.i("tag1234", "bitmap释放");
                        bitmapCut.recycle();
                    }
                    if (width < height) {
                        bitmapCut = Bitmap.createBitmap(resource, 0, 0, width, width);
                    } else if (width > height) {
                        bitmapCut = Bitmap.createBitmap(resource, 0, 0, height, height);
                    } else {
                        bitmapCut = resource;
                    }
                    if (null == bitmapCut) {
                        ivCover.setImageResource(R.drawable.zhanweitu13);
                    } else {
                        ivCover.setImageBitmap(bitmapCut);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                try {
                    if (isDestroyed() || ivCover == null) {
                        return;
                    }
                    ivCover.setImageResource(R.drawable.zhanweitu13);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void gcRecycled(View view) {
        System.gc();
        Log.i("tag1234", "gc回收");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (null != bitmapCut && !bitmapCut.isRecycled()) {
                bitmapCut.recycle();
                bitmapCut = null;
            }

            if (unbinder != null) {
                unbinder.unbind();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

