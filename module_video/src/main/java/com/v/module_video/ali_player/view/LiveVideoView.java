package com.v.module_video.ali_player.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.v.module_video.R;
import com.v.module_video.R2;
import com.v.module_video.ali_player.myaliyunplayer.VideoPlayer;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ClassName: LiveVideoView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/14 2:06 下午
 */
@SuppressLint("NonConstantResourceId")
public class LiveVideoView extends RelativeLayout implements com.v.url_module.MediaControllerInterface {
    //视频
    @BindView(R2.id.sv_mediaplayer_surfaceView)
    public SurfaceView sv_mediaplayer_surfaceView;
    //手势
    @BindView(R2.id.gesture_view)
    public VideoGestureRelativeLayout mGestureView;

    @BindView(R2.id.controller_view)
    public MediaControllerView controller_view;

    private Activity mActivity;
    private boolean isShowControllerView = false;

    /**
     * 阿里播放器
     */
    public VideoPlayer mVideoView;

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
        this.mActivity = (Activity) context;
        inflate(context,R.layout.layout_live_video_view, this);
        ButterKnife.bind(this);
        controller_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setHideControllerView();
            }
        });
    }

    /**
     * 初始化controller_view
     */
    private void initControllerView(){
        AllScreenTopView allScreenTopView = new AllScreenTopView(getContext()) ;
        allScreenTopView.setMediaController(this);
    }


    /**
     * 隐藏控制器的handler
     */
    private static int time = 0;
    private final HideControllerHandler mHideHandler = new HideControllerHandler(this);

    private static class HideControllerHandler extends Handler {
        private WeakReference<LiveVideoView> fragmentNewWeakReference;

        public HideControllerHandler(LiveVideoView fragmentNew) {
            fragmentNewWeakReference = new WeakReference<LiveVideoView>(fragmentNew);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                LiveVideoView fragmentNew = fragmentNewWeakReference.get();
                if (fragmentNew == null) {
                    return;
                }
                time++;
                if (time == 5) {
                    fragmentNew.setHideControllerView();
                } else {
                    sendEmptyMessageDelayed(0, 1000);
                }
            } catch (Exception e) {
                //正常逻辑处理，不需要上报
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    }

    private void setHideControllerView() {
        if (isShowControllerView) {
            controller_view.setVisibility(View.GONE);
            isShowControllerView = false;
            time = 0;
            mHideHandler.removeCallbacksAndMessages(null);
        } else {
            controller_view.setVisibility(View.VISIBLE);
            isShowControllerView = true;
            mHideHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onBack() {
        //返回
        if (mActivity != null) {
            mActivity.finish();
        }
    }

    @Override
    public void onSetting() {

    }

    @Override
    public void onShare() {

    }

    @Override
    public void onQuality() {

    }

    @Override
    public void onAttention() {

    }
}
