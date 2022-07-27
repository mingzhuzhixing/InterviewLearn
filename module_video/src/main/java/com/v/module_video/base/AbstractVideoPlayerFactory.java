package com.v.module_video.base;

public abstract class AbstractVideoPlayerFactory<M extends BasePlaySource,T> implements IVideoPlayerFactory, IVideoPlayer<M,T> {

    public IPlayerListener.onAutoPlayListener autoPlayListener = null;
    public IPlayerListener.onCompletionListener completionListener = null;
    public IPlayerListener.onCurrentPositionListener currentPositionListener = null;
    public IPlayerListener.onErrorListener errorListener = null;
    public IPlayerListener.onLoadingListener loadingListener = null;
    public IPlayerListener.onLoopPlayListener loopPlayListener = null;
    public IPlayerListener.onPreParedListener preParedListener = null;
    public IPlayerListener.onStartedListener onStartedListener = null;
    public IPlayerListener.onPausedListener onPausedListener = null;
    public IPlayerListener.onLoadingStatusListener loadingStatusListener = null;

    public int playerState = IVideoPlayer.idle;

    public M playSource = null;


    @Override
    public void setPlaySource(M playSource) {
        this.playSource = playSource;
    }


    @Override
    public M getPlaySource() {
        return playSource;
    }

    @Override
    public void setCompletionListener(IPlayerListener.onCompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    @Override
    public void setErrorListener(IPlayerListener.onErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void setPreParedListener(IPlayerListener.onPreParedListener preParedListener) {
        this.preParedListener = preParedListener;
    }

    @Override
    public void setLoopPlayListener(IPlayerListener.onLoopPlayListener loopPlayListener) {
        this.loopPlayListener = loopPlayListener;
    }

    @Override
    public void setLoadingListener(IPlayerListener.onLoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    @Override
    public void setCurrentPositionListener(IPlayerListener.onCurrentPositionListener currentPositionListener) {
        this.currentPositionListener = currentPositionListener;
    }

    @Override
    public void setAutoPlayListener(IPlayerListener.onAutoPlayListener autoPlayListener) {
        this.autoPlayListener = autoPlayListener;
    }


    public void setOnStartedListener(IPlayerListener.onStartedListener onStartedListener) {
        this.onStartedListener = onStartedListener;
    }

    public void setOnPausedListener(IPlayerListener.onPausedListener onPausedListener) {
        this.onPausedListener = onPausedListener;
    }

    public void setLoadingStatusListener(IPlayerListener.onLoadingStatusListener statusListener) {
        this.loadingStatusListener = statusListener;
    }

    public abstract void setQuality(Object quality);

    public abstract Object getQuality();

    public void changePath(String path){};
}
