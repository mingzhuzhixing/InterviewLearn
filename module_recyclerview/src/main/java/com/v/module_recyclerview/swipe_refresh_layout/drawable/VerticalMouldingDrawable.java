package com.v.module_recyclerview.swipe_refresh_layout.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import com.scwang.smartrefresh.layout.internal.ProgressDrawable;

import java.util.ArrayList;

/**
 * ClassName: VerticalMouldingDrawable
 * Description: loading动画
 *
 * @author wangbingqi
 * @package_name com.youshu.module_baseui.view.loadingview
 * @date 2021/10/19 10:34 AM
 */
public class VerticalMouldingDrawable extends ProgressDrawable implements Animatable, ValueAnimator.AnimatorUpdateListener {

    protected int mProgressDegree = 0;
    protected ValueAnimator mValueAnimator;
    protected  float vW=6;
    protected  float row_w=6;
    protected int speed=3;
    protected int[] frames={6,4,2,4,6,4};
    ArrayList<Integer> frameList=new ArrayList<>();

    public VerticalMouldingDrawable(){
        mValueAnimator = ValueAnimator.ofInt(4*speed);
        mValueAnimator.setDuration(800);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        setFragmes(speed);
    }

    public void setSpeed(int speed){
        this.speed=speed;
    }
    public void setW(float w){
        this.vW=w;
    }

    public void setRowW(float w){
        this.row_w=w;
    }

    private void setFragmes(int speed){
        frameList.clear();
        int s=(frames[0]-frames[1])*10/speed;
        for(int i=0;i<2;i++) {
            for(int j=0;j<speed;j++){
                int max=frames[i]*10-s*j;
                frameList.add(max);
            }
        }
        frameList.add(frames[2]*10);
        int size=frameList.size();
        for(int i=size-2;i>0;i--){
            int v=frameList.get(i);
            frameList.add(v);
        }
        int size1=frameList.size();
        for(int i=0;i<size1;i++){
            int v=frameList.get(i);
            frameList.add(v);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        final Drawable drawable=VerticalMouldingDrawable.this;
        final Rect bounds = drawable.getBounds();
        final int width = bounds.width();
        final int height = bounds.height();
        final int r = Math.max(1, width / 20);

        canvas.save();
        mPaint.setAlpha(255);
        float vH=frameList.get(mProgressDegree)*vW/10;
        fillRoundRect(mPaint,canvas,(width)/2-vW/2,(height)/2-vH/2,vW,vH,r,r);
        float vH2=frameList.get(mProgressDegree+1*speed)*vW/10;
        fillRoundRect(mPaint,canvas,(width)/2-vW/2-row_w*2,(height)/2-vH2/2,vW,vH2,r,r);
        fillRoundRect(mPaint,canvas,(width)/2-vW/2+row_w*2,(height)/2-vH2/2,vW,vH2,r,r);
        float vH3=frameList.get(mProgressDegree+2*speed)*vW/10;
        fillRoundRect(mPaint,canvas,(width)/2-vW/2-row_w*4,(height)/2-vH3/2,vW,vH3,r,r);
        fillRoundRect(mPaint,canvas,(width)/2-vW/2+row_w*4,(height)/2-vH3/2,vW,vH3,r,r);
        canvas.restore();

    }


    public static void fillRoundRect(Paint paint, Canvas canvas, float x, float y, float width, float height, float rx, float ry) {
        canvas.drawRoundRect(new RectF(x, y, x + width, y + height), rx, ry,
                paint);
    }

    @Override
    public void start() {
        if (!mValueAnimator.isRunning()) {
            mValueAnimator.addUpdateListener(this);
            mValueAnimator.start();
        }
    }

    @Override
    public void stop() {
        if (mValueAnimator.isRunning()) {
            mValueAnimator.removeAllListeners();
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.cancel();
        }
    }

    @Override
    public boolean isRunning() {
        return mValueAnimator.isRunning();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int value = (int) valueAnimator.getAnimatedValue();
        mProgressDegree = value;
        final Drawable drawable = VerticalMouldingDrawable.this;
        drawable.invalidateSelf();
    }
}
