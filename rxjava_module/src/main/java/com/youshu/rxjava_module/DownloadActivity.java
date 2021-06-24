package com.youshu.rxjava_module;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 下载图片
 */
public class DownloadActivity extends AppCompatActivity {
    private ImageView ivShowImage;
    private static final String PATH = "https://img0.baidu.com/it/u=1330784235,4146572500&fm=26&fmt=auto&gp=0.jpg";
    private static final String TAG = "DownloadActivity";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ivShowImage = findViewById(R.id.iv_show_image);
        progressDialog = new ProgressDialog(DownloadActivity.this);
        progressDialog.setTitle("正在下载图片");
    }

    public void downloadImage(View view) {
        Observable.just(PATH)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        URL url = new URL(s);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            return BitmapFactory.decodeStream(inputStream);
                        }
                        return null;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        //处理图片加水印
                        return addTextWatermark(bitmap, "这是给图片添加水印", 30, Color.RED, true);
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        //这是处理log日志
                        Log.i(TAG, "这是处理log日志");
                        return bitmap;
                    }
                })
                .compose(RxJavaUtils.<Bitmap>applySchedulers())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (progressDialog != null) {
                            progressDialog.show();
                        }
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        ivShowImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    /**
     * 给一张Bitmap添加水印文字。
     *
     * @param bitmap   源图片
     * @param content  水印文本
     * @param textSize 水印字体大小 ，单位pix。
     * @param color    水印字体颜色。
     * @param recycle  是否回收
     * @return 已经添加水印后的Bitmap。
     */
    public static Bitmap addTextWatermark(Bitmap bitmap, String content, int textSize, int color, boolean recycle) {
        if (bitmap == null || content == null)
            return null;
        Bitmap ret = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas canvas = new Canvas(ret);
        paint.setColor(color);
        paint.setTextSize(textSize);
        Rect bounds = new Rect();
        paint.getTextBounds(content, 0, content.length(), bounds);

        int x = (ret.getWidth() - bounds.width()) / 2;
        int y = (ret.getHeight() + bounds.height()) / 2;

        canvas.drawText(content, x, y, paint);
        if (recycle && !bitmap.isRecycled())
            bitmap.recycle();
        return ret;
    }

    /**
     * 进入rxjava 和 retrofit混合使用页
     */
    public void entryRetrofit(View view) {
        startActivity(new Intent(this, UserInfoActivity.class));
    }
}