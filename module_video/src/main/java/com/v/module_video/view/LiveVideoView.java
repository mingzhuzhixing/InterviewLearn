package com.v.module_video.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.v.module_video.R;

import java.lang.ref.WeakReference;

/**
 * ClassName: LiveVideoView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.url_module
 * @date 2022/2/14 2:06 下午
 */
public class LiveVideoView extends RelativeLayout implements com.v.url_module.MediaControllerInterface {
    private Activity mActivity;
    private MediaControllerView controller_view;
    private boolean isShowControllerView = false;

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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_live_video_view, this, true);
        controller_view = view.findViewById(R.id.controller_view);
        view.findViewById(R.id.fl_video_container).setOnClickListener(new OnClickListener() {
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
