package com.v.module_widget.letter_side_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

/**
 * 字符侧边栏bar
 */
public class LetterSideBar extends View {
    private Paint mNormalPaint;
    private Paint mFocusPaint;
    private int mPaddingTop;
    private final String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int mCurrentPosition = -1;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        int normalColor = array.getColor(R.styleable.LetterSideBar_normalColor, Color.BLACK);
        int focusColor = array.getColor(R.styleable.LetterSideBar_focusColor, Color.RED);
        int textSize = (int) array.getDimension(R.styleable.LetterSideBar_letterTextSize, 15);
        mPaddingTop = (int) array.getDimension(R.styleable.LetterSideBar_letterPaddingTop, 15);

        array.recycle();

        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(normalColor);
        mNormalPaint.setTextSize(textSize);

        mFocusPaint = new Paint();
        mFocusPaint.setAntiAlias(true);
        mFocusPaint.setColor(focusColor);
        mFocusPaint.setTextSize(textSize);
        mFocusPaint.setFakeBoldText(true);
    }

    float mTextHeight;
    float mTextWidth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量文字的宽度
        mTextWidth = mNormalPaint.measureText(mLetters[12]); //Return the width of the text.
        Rect rect = new Rect();
        mNormalPaint.getTextBounds(mLetters[0], 0, mLetters[0].length(), rect);
        mTextHeight = rect.height();

        //计算宽度 == 左右的padding + 字母宽度
        int width = getPaddingLeft() + getPaddingRight() + (int) mTextWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i < mLetters.length + 1; i++) {
            //获取打那个字母宽度
            float textWidth = mNormalPaint.measureText(mLetters[i - 1]);
            //设置字符左右居中
            if ((i - 1) == mCurrentPosition) {
                //设置字符选中的颜色
                canvas.drawText(mLetters[i - 1], (mTextWidth - textWidth) / 2.0f, mTextHeight * i + mPaddingTop * i, mFocusPaint);
            } else {
                canvas.drawText(mLetters[i - 1], (mTextWidth - textWidth) / 2.0f, mTextHeight * i + mPaddingTop * i, mNormalPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String currentLetter = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算当前触摸字母的位置
                int position = (int) (event.getY() / (mTextHeight + mPaddingTop)) + 1;

                if (position < 0) {
                    position = 0;
                }
                if (position > mLetters.length) {
                    position = mLetters.length - 1;
                }
                if (mCurrentPosition == position) {
                    return true;
                }
                //设置回调
                if (mListener != null) {
                    currentLetter = mLetters[position];
                    mListener.callback(currentLetter, true);
                }
                mCurrentPosition = position;
                invalidate();
                break;

            case MotionEvent.ACTION_UP: //抬起
                if (mListener != null) {
                    mListener.callback(currentLetter, false);
                }
                break;
        }
        return true;
    }

    private LetterTouchListener mListener;

    public void setOnLetterTouchListener(LetterTouchListener listener) {
        this.mListener = listener;
    }


    public interface LetterTouchListener {
        void callback(String letter, boolean isShow);
    }
}
