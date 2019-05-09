package com.v.rendere_optimizate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 18:05
 */
public class WaveView extends View {

    private final Paint mPaint = new Paint();
    private final Path mFirstPath = new Path();
    private final Path mSecondPath = new Path();

    /**
     * 两条正弦波之间的波，振幅比较低的那一条
     */
    private final Path mCenterPath = new Path();

    /**
     * 采样点的数量，越高越精细
     * 但高于一定限度后人艳察觉不出来
     */
    private static final int SAMPLINT_SIZE = 64;

    /**
     * 采样点
     */
    private float[] mSamplingX;

    private float[] mMapx;
    private int mWidth;
    private int mHeight;
    private int mCenterHeight;

    /**
     * 振幅
     */
    private int mAmplitude;

    /**
     * 波峰和两条路径的交叉点的记录，包括起点和终点，用于绘制渐变
     *
     * @param context
     */
    private final float[][] mCrestAndCrosspints = new float[9][];
    private final RectF rectF = new RectF();
    private final Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private final int mBanckGroundColor = Color.rgb(24, 33, 41);
    private final int mCenterPathColor = Color.argb(64, 255, 255, 255);

    private long startTime = System.currentTimeMillis();

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //防抖动
        mPaint.setDither(true);

        //抗锯齿
        mPaint.setAntiAlias(true);

        for (int i = 0; i < 9; i++) {
            mCrestAndCrosspints[i] = new float[2];

        }
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mSamplingX == null) {
            mWidth = canvas.getWidth();
            mHeight = canvas.getHeight();


            mCenterHeight = mHeight == 0 ? 50 : mHeight >> 1;//1/2
            mAmplitude = mWidth == 0 ? 30 : mWidth >> 3;//1/8

            mSamplingX = new float[SAMPLINT_SIZE + 1];
            mMapx = new float[SAMPLINT_SIZE + 1];
            float gap = mWidth / (float) SAMPLINT_SIZE;

            float x;
            for (int i = 0; i < SAMPLINT_SIZE; i++) {
                x = i * gap;
                mSamplingX[i] = x;

                //映射到[-2,2]
                mMapx[i] = (x / (float) mWidth) * 4 - 2;
            }
        }

        canvas.drawColor(Color.rgb(24, 23, 41));

        //复位
        mFirstPath.rewind();
        mSecondPath.rewind();
        mCenterPath.rewind();

        mFirstPath.moveTo(0, mCenterHeight);
        mSecondPath.moveTo(0, mCenterHeight);
        mCenterPath.moveTo(0, mCenterHeight);

        float offset = (System.currentTimeMillis() - startTime) / 500f;

        float x;
        float curv = 0;
        for (int i = 0; i <= SAMPLINT_SIZE; i++) {
            x = mSamplingX[i];
            curv = i < SAMPLINT_SIZE ? (float) (mAmplitude * calculate(mMapx[i], offset)) : 0;
            mFirstPath.lineTo(x,mCenterHeight+curv);
            mSecondPath.lineTo(x,mCenterHeight-curv);
            mCenterPath.lineTo(x,(mCenterHeight+curv)/5);
        }
    }


    private double calculate(float mapX, float offset) {

        offset %= 2;

        double sinFunx = Math.sin(0.75 * Math.PI * mapX - offset * Math.PI);

        double recessionFun = Math.pow((4 / (4 + Math.pow(mapX, 4))), 2.5);

        return sinFunx * recessionFun;
    }


}
