package com.v.module_video;

import android.view.SurfaceHolder;

import com.v.module_video.base.AbstractVideoPlayerFactory;
import com.v.module_video.base.BasePlaySource;
import com.v.module_video.base.IPlayerListener;
import com.v.module_video.base.IVideoPlayer;
import com.v.module_video.myaliyunplayer.AliyunVideoPlayer;


public class VideoPlayer implements IVideoPlayer {

    AbstractVideoPlayerFactory videoPlayerFactory = null;

    public VideoPlayer() {
        videoPlayerFactory = new AliyunVideoPlayer();
    }

    @Override
    public void start() {
        if (videoPlayerFactory != null) videoPlayerFactory.start();
    }

    @Override
    public void pause() {
        if (videoPlayerFactory != null) videoPlayerFactory.pause();
    }

    @Override
    public void stop() {
        if (videoPlayerFactory != null) videoPlayerFactory.stop();
    }

    @Override
    public void seekTo(long position) {
        if (videoPlayerFactory != null) videoPlayerFactory.seekTo(position);
    }

    @Override
    public void reset() {
        if (videoPlayerFactory != null) videoPlayerFactory.reset();
    }

    @Override
    public void release() {
        if (videoPlayerFactory != null) videoPlayerFactory.release();
    }

    @Override
    public Object getMediaInfo() {
        if (videoPlayerFactory == null) return null;
        return videoPlayerFactory.getMediaInfo();
    }

    @Override
    public void setLoop(boolean loop) {
        if (videoPlayerFactory != null) videoPlayerFactory.setLoop(loop);
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        if (videoPlayerFactory != null) videoPlayerFactory.setAutoPlay(autoPlay);
    }

    @Override
    public void setVolume(float volume) {
        if (videoPlayerFactory != null) videoPlayerFactory.setVolume(volume);
    }

    @Override
    public void setSpeed(float speed) {
        if (videoPlayerFactory != null) videoPlayerFactory.setSpeed(speed);
    }

    @Override
    public float getSpeed() {
        if (videoPlayerFactory != null) {
            return videoPlayerFactory.getSpeed();
        }
        return 1f;
    }

    @Override
    public long getDuration() {
        if (videoPlayerFactory != null) {
            return videoPlayerFactory.getDuration();
        }
        return 0;
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        if (videoPlayerFactory != null) videoPlayerFactory.setDisplay(surfaceHolder);
    }

    @Override
    public void redraw() {
        if (videoPlayerFactory != null) videoPlayerFactory.redraw();
    }

    @Override
    public int getPlayState() {
        if (videoPlayerFactory != null) {
            return videoPlayerFactory.getPlayState();
        }
        return IVideoPlayer.idle;
    }

    @Override
    public void setPlaySource(BasePlaySource playSource) {
        if (videoPlayerFactory != null) videoPlayerFactory.setPlaySource(playSource);
    }

    @Override
    public BasePlaySource getPlaySource() {
        if (videoPlayerFactory != null) {
            return videoPlayerFactory.getPlaySource();
        }
        return null;
    }

    public void setCompletionListener(IPlayerListener.onCompletionListener completionListener) {
        if (videoPlayerFactory != null)
            videoPlayerFactory.setCompletionListener(completionListener);
    }

    public void setErrorListener(IPlayerListener.onErrorListener errorListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setErrorListener(errorListener);
    }

    public void setPreParedListener(IPlayerListener.onPreParedListener preParedListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setPreParedListener(preParedListener);
    }

    public void setLoopPlayListener(IPlayerListener.onLoopPlayListener loopPlayListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setLoopPlayListener(loopPlayListener);
    }

    public void setLoadingListener(IPlayerListener.onLoadingListener loadingListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setLoadingListener(loadingListener);
    }

    public void setCurrentPositionListener(IPlayerListener.onCurrentPositionListener currentPositionListener) {
        if (videoPlayerFactory != null)
            videoPlayerFactory.setCurrentPositionListener(currentPositionListener);
    }

    public void setAutoPlayListener(IPlayerListener.onAutoPlayListener autoPlayListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setAutoPlayListener(autoPlayListener);
    }

    public void setOnStartedListener(IPlayerListener.onStartedListener onStartedListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setOnStartedListener(onStartedListener);
    }

    public void setOnPausedListener(IPlayerListener.onPausedListener onPausedListener) {
        if (videoPlayerFactory != null) videoPlayerFactory.setOnPausedListener(onPausedListener);
    }

    public void setOnLoadingStatusListener(IPlayerListener.onLoadingStatusListener onLoadingStatusListener) {
        if (videoPlayerFactory != null)
            videoPlayerFactory.setLoadingStatusListener(onLoadingStatusListener);
    }

    public void setQuality(Object quality) {
        if (videoPlayerFactory != null) videoPlayerFactory.setQuality(quality);
    }

    public Object getQuality() {
        if (videoPlayerFactory != null) {
            return videoPlayerFactory.getQuality();
        }
        return null;
    }

    public void changePath(String path) {
        if (videoPlayerFactory != null) videoPlayerFactory.changePath(path);
    }

}
