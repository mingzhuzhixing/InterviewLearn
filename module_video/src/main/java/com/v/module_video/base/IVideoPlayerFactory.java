package com.v.module_video.base;

import android.content.Context;

public interface IVideoPlayerFactory {

    /**
     * 初始化，可以设置监听
     */
    void init(Context context);

    void setCompletionListener(IPlayerListener.onCompletionListener completionListener);

    void setErrorListener(IPlayerListener.onErrorListener errorListener);

    void setPreParedListener(IPlayerListener.onPreParedListener preParedListener);

    /**
     * 循环播放开始监听
     */
    void setLoopPlayListener(IPlayerListener.onLoopPlayListener loopPlayListener);

    /**
     * 缓冲位置监听
     */
    void setLoadingListener(IPlayerListener.onLoadingListener loadingListener);

    /**
     * 当前播放位置监听
     */
    void setCurrentPositionListener(IPlayerListener.onCurrentPositionListener currentPositionListener);

    /**
     * 自动播放开始监听
     */
    void setAutoPlayListener(IPlayerListener.onAutoPlayListener autoPlayListener);





}
