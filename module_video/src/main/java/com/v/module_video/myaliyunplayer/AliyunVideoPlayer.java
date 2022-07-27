package com.v.module_video.myaliyunplayer;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceHolder;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.IPlayer;
import com.aliyun.player.bean.ErrorInfo;
import com.aliyun.player.bean.InfoBean;
import com.aliyun.player.bean.InfoCode;
import com.aliyun.player.nativeclass.MediaInfo;
import com.aliyun.player.nativeclass.PlayerConfig;
import com.aliyun.player.nativeclass.TrackInfo;
import com.aliyun.player.source.UrlSource;
import com.aliyun.player.source.VidSts;
import com.v.module_utils.Utilities;
import com.v.module_video.HTTPStringHelper;
import com.v.module_video.base.AbstractVideoPlayerFactory;
import com.v.module_video.base.IVideoPlayer;


public class AliyunVideoPlayer extends AbstractVideoPlayerFactory<AliyunPlaySource, MediaInfo> implements IPlayer.OnCompletionListener, IPlayer.OnErrorListener, IPlayer.OnPreparedListener, IPlayer.OnVideoSizeChangedListener, IPlayer.OnRenderingStartListener, IPlayer.OnInfoListener, IPlayer.OnLoadingStatusListener, IPlayer.OnSeekCompleteListener, IPlayer.OnTrackChangedListener, IPlayer.OnStateChangedListener {

    private AliPlayer aliyunVodPlayer;
    private boolean isAutoPlay = true;

    public AliyunVideoPlayer() {
        init(Utilities.getApplicationContext());
    }

    @Override
    public void init(Context context) {
        if (aliyunVodPlayer == null) {
            aliyunVodPlayer = AliPlayerFactory.createAliPlayer(context);

            aliyunVodPlayer.setOnCompletionListener(this);
            aliyunVodPlayer.setOnErrorListener(this);
            aliyunVodPlayer.setOnPreparedListener(this);
            aliyunVodPlayer.setOnVideoSizeChangedListener(this);
            aliyunVodPlayer.setOnRenderingStartListener(this);
            aliyunVodPlayer.setOnInfoListener(this);
            aliyunVodPlayer.setOnLoadingStatusListener(this);
            aliyunVodPlayer.setOnSeekCompleteListener(this);
            aliyunVodPlayer.setOnTrackChangedListener(this);
            aliyunVodPlayer.setOnStateChangedListener(this);
            PlayerConfig config = aliyunVodPlayer.getConfig();
            config.mReferrer = "https://app.youshu.cc/youshuapp/android_" + Utilities.getVersionName();
            config.mUserAgent = HTTPStringHelper.getUserAgent(Utilities.getApplicationContext());
            aliyunVodPlayer.setConfig(config);

            //如果播放的视频是通过播放器SDK安全下载后的本地文件，那么还需要设置一个加密校验信息（建议在Application中配置一次即可）;
//        PrivateService.initService(Utilities.getApplicationContext(), "encryptedApp.dat的本地路径");
        }
    }

    @Override
    public void start() {

        if (aliyunVodPlayer == null) return;
        if (playerState == IVideoPlayer.paused || playerState == IVideoPlayer.prepared || playerState == IVideoPlayer.completion) {
            aliyunVodPlayer.start();
            playerState = IVideoPlayer.started;
            if (onStartedListener != null) onStartedListener.onStartPlay();
        } else if (playerState == IVideoPlayer.started) {
            aliyunVodPlayer.pause();
            playerState = IVideoPlayer.paused;
            if (onPausedListener != null) onPausedListener.onPausePlay();
        } else if (playerState == IVideoPlayer.idle) {
            if (!TextUtils.isEmpty(getPlaySource().getUrl())) {
                UrlSource urlSource = new UrlSource();
                urlSource.setUri(getPlaySource().getUrl());
                aliyunVodPlayer.setDataSource(urlSource);
                aliyunVodPlayer.prepare();
            } else {
                VidSts aliyunVidSts = new VidSts();
                aliyunVidSts.setVid(getPlaySource().getVid());
                aliyunVidSts.setAccessKeyId(getPlaySource().getAkId());
                aliyunVidSts.setAccessKeySecret(getPlaySource().getAkSecret());
                aliyunVidSts.setSecurityToken(getPlaySource().getToken());
                aliyunVodPlayer.setDataSource(aliyunVidSts);
                aliyunVodPlayer.prepare();
            }
            playerState = IVideoPlayer.initalized;
        } else if (playerState == IVideoPlayer.error) {
            //临时解决断网情况
            aliyunVodPlayer.prepare();
            aliyunVodPlayer.start();
            playerState = IVideoPlayer.started;
            if (onStartedListener != null) onStartedListener.onStartPlay();
        }

    }

    @Override
    public void pause() {

        if (aliyunVodPlayer == null) return;
        if (playerState == IVideoPlayer.started) {
            aliyunVodPlayer.pause();
            playerState = IVideoPlayer.paused;
            if (onPausedListener != null) onPausedListener.onPausePlay();
        }
    }

    @Override
    public void stop() {

        if (aliyunVodPlayer == null) return;
//        if (playerState == IVideoPlayer.started || playerState == IVideoPlayer.prepared){
        aliyunVodPlayer.stop();
        playerState = IVideoPlayer.stopped;
//        }
    }

    @Override
    public void seekTo(long position) {
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.seekTo(position, IPlayer.SeekMode.Accurate);
    }

    @Override
    public void reset() {

        playerState = IVideoPlayer.idle;
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.reset();
    }

    @Override
    public void release() {
        playerState = IVideoPlayer.idle;
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.release();
    }

    @Override
    public MediaInfo getMediaInfo() {
        return aliyunVodPlayer.getMediaInfo();
    }

    @Override
    public void setLoop(boolean loop) {

        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.setLoop(loop);
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {


        if (aliyunVodPlayer == null) return;
        isAutoPlay = autoPlay;
        aliyunVodPlayer.setAutoPlay(autoPlay);
    }

    @Override
    public void setVolume(float volume) {
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.setVolume(volume);
    }

    @Override
    public void setSpeed(float speed) {
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.setSpeed(speed);
    }

    @Override
    public float getSpeed() {
        if (aliyunVodPlayer != null) {
            return aliyunVodPlayer.getSpeed();
        }
        return 1f;
    }

    @Override
    public long getDuration() {
        if (aliyunVodPlayer != null) {
            return aliyunVodPlayer.getDuration();
        }
        return 0;
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.setDisplay(surfaceHolder);
    }

    @Override
    public void redraw() {
        if (aliyunVodPlayer == null) return;
        aliyunVodPlayer.redraw();
    }

    @Override
    public int getPlayState() {
        return playerState;
    }

    @Override
    public void setQuality(Object quality) {
        if (aliyunVodPlayer == null) return;
        try {
            aliyunVodPlayer.selectTrack((Integer) quality);
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
        }
    }

    @Override
    public Object getQuality() {
        if (aliyunVodPlayer != null) {
            return aliyunVodPlayer.currentTrack(TrackInfo.Type.TYPE_VOD);
        }
        return null;
    }

    //配置网络重试时间和次数
    public void setConfig(PlayerConfig config) {

    }

    //------------------------以下为阿里云播放器监听回调-------------------------------

    @Override
    public void onCompletion() {

        //播放完成事件
        playerState = IVideoPlayer.completion;
        if (completionListener != null) completionListener.onCompletion();
    }

    @Override
    public void onError(ErrorInfo errorInfo) {
        //出错事件
        playerState = IVideoPlayer.error;
        if (errorListener != null) errorListener.onError(errorInfo);
    }

    @Override
    public void onPrepared() {

        //准备成功事件
        playerState = IVideoPlayer.prepared;
        if (isAutoPlay) start();
        if (preParedListener != null) preParedListener.onPrepared();
    }

    @Override
    public void onVideoSizeChanged(int i, int i1) {
        //视频分辨率变化回调
    }

    @Override
    public void onRenderingStart() {
        //首帧渲染显示事件
    }

    @Override
    public void onInfo(InfoBean infoBean) {
        //其他信息的事件，type包括了：循环播放开始，缓冲位置，当前播放位置，自动播放开始等
        if (infoBean.getCode() == InfoCode.AutoPlayStart) {
            //自动播放开始,需要设置播放状态
            if (autoPlayListener != null) autoPlayListener.onAutoPlay();
        } else if (infoBean.getCode() == InfoCode.BufferedPosition) {
            //更新bufferedPosition
            if (loadingListener != null) loadingListener.onLoading(infoBean.getExtraValue());
        } else if (infoBean.getCode() == InfoCode.CurrentPosition) {
            //更新currentPosition
            if (currentPositionListener != null)
                currentPositionListener.onCurrentPosition(infoBean.getExtraValue());
        } else if (infoBean.getCode() == InfoCode.AutoPlayStart) {
            //自动播放开始,需要设置播放状态
            if (autoPlayListener != null) autoPlayListener.onAutoPlay();
        } else {
        }

//        try {
//            if (infoBean.getCode() == InfoCode.CurrentPosition) {
//                //更新currentPosition
//                if (currentPositionListener != null) currentPositionListener.onCurrentPosition(infoBean.getExtraValue());
//
//            }
//        } catch (Exception e) {
//            //正常容错逻辑，不需要上报
//            e.printStackTrace();
//        }
    }


    @Override
    public void onLoadingBegin() {
        //开始缓冲
        if (loadingStatusListener != null) {
            loadingStatusListener.onLoadingBegin();
        }
    }

    @Override
    public void onLoadingProgress(int i, float v) {
        //缓冲进度
        if (loadingStatusListener != null) {
            loadingStatusListener.onLoadingProgress(i, v);
        }
    }

    @Override
    public void onLoadingEnd() {
        //缓冲结束
        if (loadingStatusListener != null) {
            loadingStatusListener.onLoadingEnd();
        }
    }


    @Override
    public void onSeekComplete() {
        //拖动结束
    }

    @Override
    public void onChangedSuccess(TrackInfo trackInfo) {
        //切换音视频流或者清晰度成功
        //清晰度切换监听
        if (trackInfo.getType() == TrackInfo.Type.TYPE_VOD) {
            //切换成功后就开始播放
        }
    }

    @Override
    public void onChangedFail(TrackInfo trackInfo, ErrorInfo errorInfo) {
        //切换音视频流或者清晰度失败
    }

    @Override
    public void onStateChanged(int i) {
        //播放器状态改变事件
    }

    public void changePath(String path){
        if(!TextUtils.isEmpty(path)) {
            getPlaySource().setUrl(path);
        }
        if (aliyunVodPlayer == null) return;
//        aliyunVodPlayer.stop();
        playerState=IVideoPlayer.idle;
        start();

//        mVideoView.setPlaySource(new AliyunPlaySource().setUrl(mVideoPath));

    }
}

