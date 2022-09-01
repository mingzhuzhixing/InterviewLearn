package com.v.module_bitmap.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: BitmapUtils
 * Description:
 *
 * @author zhuming
 * @package_name com.v.bitmap_module
 * @date 2022/1/6 2:00 下午
 */
public class BitmapUtils {
    /**
     * 尺寸压缩（通过缩放图片像素来减少图片占用内存大小）
     */
    public static void sizeCompress(Bitmap bmp, File file) {
        // 尺寸压缩倍数,值越大，图片尺寸越小
        int ratio = 8;
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采样率压缩（设置图片的采样率，降低图片像素）
     */
    public static void samplingRateCompress(String filePath, File file) {
        // 数值越高，图片像素越低
        int inSampleSize = 8;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        //options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
        //采样率
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片压缩：质量压缩方法
     *
     * @param beforeBitmap 要压缩的图片
     * @return 压缩后的图片
     * <p>
     * 通过Bitmap#compress方法，修改quality的值，来改变Bitmap生成的字节流的大小。这种方法不会改变Bitmap占用的内存大小。
     * 质量压缩不会减少图片的像素，它是在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的。图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
     * 这里改变的是bitmap对应的字节数组的大小，适合去传递二进制的图片数据，比如微信分享
     */
    public static Bitmap compressByQuality(Bitmap beforeBitmap, final boolean recycle) {
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        try {
            // 可以捕获内存缓冲区的数据，转换成字节数组。
            bos = new ByteArrayOutputStream();
            if (beforeBitmap != null) {
                printBitmapInfo(beforeBitmap, "compressByQuality beforeBitmap");
                // 第一个参数：图片压缩的格式；第二个参数：压缩的比率；第三个参数：压缩的数据存放到bos中
                beforeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                // 循环判断压缩后的图片大小是否满足要求，这里限制100kb，若不满足则继续压缩，每次递减10%压缩
                int options = 100;
                while (bos.toByteArray().length / 1024 > 100) {
                    bos.reset();// 置为空
                    beforeBitmap.compress(Bitmap.CompressFormat.JPEG, options, bos);
                    options -= 10;
                }
                if (recycle && !beforeBitmap.isRecycled()) {
                    beforeBitmap.recycle();
                }
                Log.i("wechat", "compressByQuality toByteArray:" + bos.toByteArray().length);
                // 从bos中将数据读出来 转换成图片
                bis = new ByteArrayInputStream(bos.toByteArray());
                Bitmap afterBitmap = BitmapFactory.decodeStream(bis);
                printBitmapInfo(afterBitmap, "compressByQuality afterBitmap");
                return afterBitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beforeBitmap;
    }

    /**
     * 按采样大小压缩
     *
     * @param beforeBitmap The source of bitmap.
     * @param sampleSize   主要用于获取Bitmap的缩略图，例如：inSampleSize=2，那么bitmap的宽度和高度为原来尺寸的1/2。像素总数则为原来的1/4。
     *                     Any value <= 1 is treated the same as 1. 看了下代码，在Native层解码生成SKBitmap的像素数据时，会根据图片原始宽高除以inSampleSize，得到缩略图的宽高。
     * @return the compressed bitmap
     */
    public static Bitmap compressBySampleSize(final Bitmap beforeBitmap, final int sampleSize) {
        return compressBySampleSize(beforeBitmap, sampleSize, false);
    }

    public static Bitmap compressBySampleSize(final Bitmap beforeBitmap, final int sampleSize, final boolean recycle) {
        ByteArrayOutputStream baos = null;
        try {
            if (beforeBitmap == null) {
                return null;
            }
            printBitmapInfo(beforeBitmap, "compressBySampleSize beforeBitmap");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = sampleSize;
            baos = new ByteArrayOutputStream();
            beforeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            if (recycle && !beforeBitmap.isRecycled()) {
                beforeBitmap.recycle();
            }
            Bitmap afterBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            printBitmapInfo(afterBitmap, "compressBySampleSize afterBitmap");
            return afterBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beforeBitmap;
    }

    /**
     * 更改Bitmap.Config格式
     * ption.inPreferredConfig = Bitmap.Config.ARGB_4444
     * //  option.inPreferredConfig = Bitmap.Config.RGB_565 // 对透明度没要求的话可以试一下rgb_565
     */
    public static Bitmap compressByConfig(final Bitmap beforeBitmap, Bitmap.Config config) {
        return compressByConfig(beforeBitmap, config, false);
    }

    public static Bitmap compressByConfig(final Bitmap beforeBitmap, Bitmap.Config config, boolean recycle) {
        ByteArrayOutputStream baos = null;
        try {
            if (beforeBitmap == null) {
                return null;
            }
            printBitmapInfo(beforeBitmap, "compressByConfigSize beforeBitmap");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;
            baos = new ByteArrayOutputStream();
            beforeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            if (recycle && !beforeBitmap.isRecycled()) {
                beforeBitmap.recycle();
            }
            Bitmap afterBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            printBitmapInfo(afterBitmap, "compressByConfigSize afterBitmap");
            return afterBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beforeBitmap;
    }

    /**
     * val descBmpConfigJpg =
     * "height:${bmpBmpConfigJpg?.height},\nwidth:${bmpBmpConfigJpg?.width},\nallocationByteCount:${bmpBmpConfigJpg?.allocationByteCount}byte,\n" +
     * "byteCount:${bmpBmpConfigJpg?.byteCount}byte,\nrowBytes:${bmpBmpConfigJpg?.rowBytes}byte,\ndensity:${bmpBmpConfigJpg?.density}"
     */
    private static void printBitmapInfo(Bitmap bitmap, String msg) {
        StringBuilder builder = new StringBuilder();
        builder.append(msg).append(",");
        if (bitmap != null) {
            builder.append("width:").append(bitmap.getWidth()).append(",");
            builder.append("height:").append(bitmap.getHeight()).append(",");
            builder.append("allocationByteCount:").append(bitmap.getAllocationByteCount() / 1024).append("kb").append(",");
            builder.append("byteCount:").append(bitmap.getByteCount() / 1024).append("kb").append(",");
            builder.append("rowBytes:").append(bitmap.getRowBytes()).append("byte").append(",");
            builder.append("density:").append(bitmap.getDensity()).append(",");
            builder.append("config:").append(bitmap.getConfig());
            Log.i("wechat", builder.toString());
        } else {
            Log.i("wechat", "printBitmapInfo bitmap is null");
        }
    }

}
