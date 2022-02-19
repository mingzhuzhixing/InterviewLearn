package com.v.video_module.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.v.video_module.R;

/**
 * ClassName: LiveVideoView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/14 2:06 下午
 */
public class LiveVideoView extends RelativeLayout {
    private Context mContext;

    public LiveVideoView(Context context) {
        this(context, null);
    }

    public LiveVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_live_video_view, this, true);
    }
}
