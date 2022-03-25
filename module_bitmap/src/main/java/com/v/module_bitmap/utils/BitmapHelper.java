package com.v.module_bitmap.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BitmapHelper {

    /**
     * 从assets中获取图片转成bitmap
     */
    public static Bitmap getAssetsBitmap(Context context, String fileName) {
        try {
            if (context == null || TextUtils.isEmpty(fileName)) {
                return null;
            }
            InputStream inputStream = context.getAssets().open(fileName);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从资源文件中获取图片转成bitmap
     */
    public static Bitmap getResourceBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable == null) {
            return null;
        }
        //第一种方式
//        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        drawable.draw(canvas);
//        return bitmap;

        //第二种方式
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }

    /**
     * 从sdcard中获取图片转成bitmap
     */
    public static Bitmap getSdCardBitmap(String fileName) {
        //Environment.getExternalStorageDirectory()得到："/mnt/sdcard"，即找到了sd卡的根目录
        Bitmap bitmap = null;
        try {
            String pathDir = Environment.getExternalStorageDirectory() + "/images/";
            File filePath = new File(pathDir);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String imagePath = pathDir + fileName;
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                return null;
            }
            bitmap = BitmapFactory.decodeFile(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 从网络中获取图片转成bitmap
     */
    public static Bitmap getNetWorkBitmap(String path) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            if (TextUtils.isEmpty(path)) {
                return null;
            }
            URL url = new URL(path);
            inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            URL iconUrl = new URL(url);
//            URLConnection conn = iconUrl.openConnection();
//            HttpURLConnection http = (HttpURLConnection) conn;
//
//            int length = http.getContentLength();
//
//            conn.connect();
//            // 获得图像的字符流
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is, length);
//            bitmap = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();// 关闭流
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        return bitmap;
    }
}
