package com.v.module_video.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.v.module_video.R;

/**
 * ClassName: MideaControllView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/17 5:26 下午
 */
public class MediaControllerView extends RelativeLayout {
    public MediaControllerView(Context context) {
        this(context,null);
    }

    public MediaControllerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MediaControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_live_video_controller,this, true);
    }
}
