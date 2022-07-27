package com.v.module_video.base;

import android.view.SurfaceHolder;

public interface IVideoPlayer<M extends BasePlaySource,T> {

    int unknow = -1;
    int idle = 0;
    int initalized = 1;
    int prepared = 2;
    int started = 3;
    int paused = 4;
    int stopped = 5;
    int completion = 6;
    int error = 7;


    void start();

    void pause();

    void stop();

    void seekTo(long position);

    void reset();

    void release();

    /**
     * 获取媒体信息
     * @return
     */
    T getMediaInfo();

    /**
     * 循环播放
     * @param loop
     */
    void setLoop(boolean loop);

    /**
     * 自动播放
     * @param autoPlay
     */
    void setAutoPlay(boolean autoPlay);

    /**
     * 设置音量 0-1f
     */
    void setVolume(float volume);

    /**
     * 设置倍速 0.5-2f
     * @param speed
     */
    void setSpeed(float speed);


    float getSpeed();

    long getDuration();

    void setDisplay(SurfaceHolder surfaceHolder);

    void redraw();

    int getPlayState();

    void setPlaySource(M playSource);

    M getPlaySource();
}
