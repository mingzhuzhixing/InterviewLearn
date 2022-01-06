package com.v.bitmap_module;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.v.glide_module.GlideUtils;

/**
 * glide使用
 */
public class BitmapMainActivity extends AppCompatActivity {
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
            "https://img1.baidu.com/it/u=3796593454,4087161325&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"};

    private Bitmap bitmapCut = null;

    private int index = 0;
    private ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_main);
        ivCover = findViewById(R.id.img);
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
                        Log.i("tag1234","bitmap释放");
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
        Log.i("tag1234","gc回收");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (null != bitmapCut && !bitmapCut.isRecycled()) {
                bitmapCut.recycle();
                bitmapCut = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}