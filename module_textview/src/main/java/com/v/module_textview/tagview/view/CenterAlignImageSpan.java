package com.v.module_textview.tagview.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ClassName: CenterAlignImageSpan
 * Description:
 *
 * @author zhuming
 * @package_name com.v.tagtextview_module.view
 * @date 2022/3/2 5:19 下午
 */
public class CenterAlignImageSpan extends ImageSpan {

    private final int mMarginLeft;
    private final int mMarginRight;

    public CenterAlignImageSpan(Drawable drawable) {
        this(drawable, 0, 0);
    }

    public CenterAlignImageSpan(Drawable drawable, int marginLeft, int marginRight) {
        super(drawable);
        mMarginLeft = marginLeft;
        mMarginRight = marginRight;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Drawable b = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        x = mMarginLeft + x;
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return mMarginLeft + super.getSize(paint, text, start, end, fm) + mMarginRight;
    }
}