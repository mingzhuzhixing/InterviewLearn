package com.v.module_widget.curve_circle;

import android.annotation.SuppressLint;
import android.graphics.Outline;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

public class CurveCircleActivity extends BaseTitleBarActivity {
    CurveCircleLayout circle_layout;
    WaveProgressView progressView;
    WaveProgressView2 progressView2;
    WaveProgressView3 progressView3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_curve_circle;
    }

    @Override
    protected String setTitle() {
        return "圆形波浪线进度图";
    }

    private int count;
    private int progress = 450;
    private static final int START = 0x23;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    count++;
                    progress -= 4.5;//圆直径450，按比例count 1=progress 4.5
                    if (count <= 100) {
                        circle_layout.setCount(count);
                        circle_layout.setProgress(progress);
                        handler.sendEmptyMessageDelayed(START, 20);
                    } else {
                        count = 0;
                        progress = 450;
                        circle_layout.setProgress(progress);
                        circle_layout.setCount(count);
                        handler.sendEmptyMessageDelayed(START, 20);
                    }
                    break;
            }
        }
    };

    @Override
    public void initData() {
        super.initData();
        circle_layout = (CurveCircleLayout) findViewById(R.id.circle_layout);
        progressView = (WaveProgressView) findViewById(R.id.progressView);
        progressView2 = (WaveProgressView2) findViewById(R.id.progressView2);
        progressView3 = (WaveProgressView3) findViewById(R.id.progressView3);
        handler.sendEmptyMessage(START);

        progressView2.setProgress(50); // 设置进度为50
        progressView2.setMaxProgress(100); // 设置最大进度为100

        progressView3.setProgress(50); // 设置进度为50
        progressView3.setMaxProgress(100); // 设置最大进度为100

        progressView.setProgress(0.5f);
        progressView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        progressView.setClipToOutline(true);
    }
}
