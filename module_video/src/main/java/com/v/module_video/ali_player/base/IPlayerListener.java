package com.v.module_video.ali_player.base;

public interface IPlayerListener {

    interface onCompletionListener {
        void onCompletion();
    }

    interface onErrorListener {
        void onError(Object error);
    }

    interface onPreParedListener {
        void onPrepared();
    }

    interface onLoopPlayListener {
        void onLoopPlay();
    }

    interface onLoadingListener {
        void onLoading(long position);
    }

    interface onCurrentPositionListener {
        void onCurrentPosition(long position);
    }

    interface onLoadingStatusListener {
        void onLoadingBegin();
        void onLoadingProgress(int i, float v);
        void onLoadingEnd();
    }

    interface onAutoPlayListener {
        void onAutoPlay();
    }

    interface onStartedListener {
        void onStartPlay();
    }

    interface onPausedListener {
        void onPausePlay();
    }
}
