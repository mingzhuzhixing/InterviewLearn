package com.v.module_video.ali_player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v.url_module.MediaControllerInterface;

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

    private MediaControllerInterface mediaController;

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
        TextView tvBack = findViewById(R.id.tv_back);
    }

    public void setView(){
//     直播全屏的顶部view
       AllScreenTopView allScreenTopView = new AllScreenTopView(getContext()) ;
       allScreenTopView.setMediaController(mediaController);
//        /**
//         * 直播全屏的底部view
//         */
//        private LiveAllScreenBottomView liveAllScreenBottomView;
//
//        /**
//         * 直播半屏的顶部view
//         */
//        private HalfScreenTopView halfScreenTopView;
//        /**
//         * 直播半屏的底部view
//         */
//        private LiveHalfScreenBottomView liveHalfScreenBottomView;
//
//        /**
//         * 回放半屏的底部view
//         */
//        private ReLiveHalfScreenBottomView reLiveHalfScreenBottomView;
//
//        /**
//         * 回放全屏的底部view
//         */
//        private ReLiveAllScreenBottomView reLiveAllScreenBottomView;
    }
}
