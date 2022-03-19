package com.v.module_widget.rating_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

/**
 * 评分控件 RatingBar
 */
public class RatingBarView extends View {
    private Bitmap mStarNormalBitmap;
    private Bitmap mStarFocusBitmap;
    private int mStarCount = 5;
    private int mStartPadding = 15;
    private int mCurrentGrade = -1;

    public RatingBarView(Context context) {
        this(context, null);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        int starNormalId = array.getResourceId(R.styleable.RatingBarView_starNormal, 0);
        int starFocusId = array.getResourceId(R.styleable.RatingBarView_starFocus, 0);

        if (starNormalId == 0 || starFocusId == 0) {
            throw new RuntimeException("请设置属性starNormal 或 starFocus");
        }
        mStarCount = array.getInteger(R.styleable.RatingBarView_starCount, mStarCount);
        mStartPadding = (int) array.getDimension(R.styleable.RatingBarView_starPadding, mStartPadding);
        array.recycle();

        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);
        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), starFocusId);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //高度  一张图片的高度
        int width = mStarNormalBitmap.getWidth() * mStarCount + (mStartPadding * (mStarCount - 1));
        int height = mStarNormalBitmap.getHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制图片
        for (int i = 0; i < mStarCount; i++) {
            //Bitmap bitmap, float left, float top, @Nullable Paint paint
            int dx = i * mStarNormalBitmap.getWidth() + mStartPadding * i;
            //结合第二个步骤，戳的手mCurrentGrade值是不断变化的
            if (mCurrentGrade > i) {
                canvas.drawBitmap(mStarFocusBitmap, dx, 0, null);
            } else {
                canvas.drawBitmap(mStarNormalBitmap, dx, 0, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //移动 按下 抬起 狐狸逻辑都是一样的 判断手指的位置，根据当前位置计算出分数，再去刷新显示
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
            case MotionEvent.ACTION_MOVE://移动
                //case MotionEvent.ACTION_UP://抬起
                float moveX = event.getX(); //event.getX()相对于控制的x位置   event.getRawX();//获取屏幕的x位置

                int currentGrade = (int) (moveX / (mStarNormalBitmap.getWidth() + mStartPadding)) + 1;
                if (currentGrade < 0) {
                    currentGrade = 0;
                }
                if (currentGrade > mStarCount) {
                    currentGrade = mStarCount;
                }
                //再去刷新显示
                //减少onDraw()调用优化：1、去掉up/down事件.2、分数相同就不要再去绘制了
                if (currentGrade == mCurrentGrade) {
                    return true;
                }
                mCurrentGrade = currentGrade;
                invalidate(); //ondraw()

                break;
        }
        //必须要返回true,不能使用super.onTouchEvent(event)。  否则将不执行move
        return true;
    }
}
