package com.v.module_widget.motion_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

public class MotionLayoutView2 extends LinearLayout {

    public MotionLayoutView2(Context context) {
        this(context, null);
    }

    public MotionLayoutView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MotionLayoutView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_motion_view_2, this, true);
    }
}
