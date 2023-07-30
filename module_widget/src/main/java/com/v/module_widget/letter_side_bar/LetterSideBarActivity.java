package com.v.module_widget.letter_side_bar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 字符侧边栏bar
 */
public class LetterSideBarActivity extends BaseTitleBarActivity {
    private TextView tvShowLetter;
    private LetterSideBar letterSideBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_letter_side_bar;
    }

    @Override
    protected String setTitle() {
        return "字符侧边栏bar";
    }

    @Override
    public void initData() {
        tvShowLetter = findViewById(R.id.tv_show_letter);
        letterSideBar = findViewById(R.id.letter_side_bar);
    }

    @Override
    public void processLogical() {
        letterSideBar.setOnLetterTouchListener(new LetterSideBar.LetterTouchListener() {
            @Override
            public void callback(String letter, boolean isShow) {
                if (isShow) {
                    tvShowLetter.setText(letter);
                    tvShowLetter.setVisibility(View.VISIBLE);
                } else {
                    tvShowLetter.setVisibility(View.GONE);
                }
            }
        });
    }
}