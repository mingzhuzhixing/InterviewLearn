package com.v.module_widget.global_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.v.module_widget.R;

public class MusicBarView extends RelativeLayout {

    public MusicBarView(Context context) {
        this(context, null);
    }

    public MusicBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_music_bar, null);
    }
}
