package com.v.module_bitmap.load;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_bitmap.R;
import com.v.module_bitmap.R2;
import com.v.module_bitmap.utils.BitmapHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName: LoadBitmapActivity
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_bitmap.load
 * @date 2022/11/13 13:16
 */
@SuppressLint("NonConstantResourceId")
public class LoadBitmapActivity extends AppCompatActivity {

    @BindView(R2.id.iv_assets_image)
    public ImageView ivAssetsImage;

    @BindView(R2.id.iv_resource_image)
    public ImageView ivResourceImage;

    @BindView(R2.id.iv_sdcard_image)
    public ImageView ivSdcardImage;

    @BindView(R2.id.iv_network_image)
    public ImageView ivNetworkImage;

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

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        unbinder = ButterKnife.bind(this);

        Bitmap bitmap = BitmapHelper.getAssetsBitmap(this, "image_2.jpg");
        ivAssetsImage.setImageBitmap(bitmap);

        Bitmap resourceBitmap = BitmapHelper.getResourceBitmap(this, R.drawable.icon_image);
        ivResourceImage.setImageBitmap(resourceBitmap);

        Bitmap sdcardBitmap = BitmapHelper.getSdCardBitmap("meinu.jpg");
        ivSdcardImage.setImageBitmap(sdcardBitmap);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap netWorkBitmap = BitmapHelper.getNetWorkBitmap(array[array.length - 1]);
                ivNetworkImage.post(new Runnable() {
                    @Override
                    public void run() {
                        ivNetworkImage.setImageBitmap(netWorkBitmap);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (unbinder != null) {
                unbinder.unbind();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
