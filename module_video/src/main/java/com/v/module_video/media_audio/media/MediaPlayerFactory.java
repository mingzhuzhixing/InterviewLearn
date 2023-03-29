package com.v.module_video.media_audio.media;

import android.media.MediaPlayer;

/**
 * ClassName: BaseMediaPlayer
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_video.media_audio.media
 * @date 2023/3/29 18:36
 */
public class MediaPlayerFactory implements android.media.MediaPlayer.OnPreparedListener, android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnErrorListener, android.media.MediaPlayer.OnInfoListener {

    public MediaPlayerFactory getInstance(){
        return MediaFactory.INSTANCE;
    }

   public static class MediaFactory{
       static MediaPlayerFactory INSTANCE = new MediaPlayerFactory();
    }

    public MediaPlayer getMediaPlayer(){
        return new MediaPlayer();
    }

    @Override
    public void onPrepared(android.media.MediaPlayer mp) {

    }

    @Override
    public void onCompletion(android.media.MediaPlayer mp) {

    }

    @Override
    public boolean onError(android.media.MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(android.media.MediaPlayer mp, int what, int extra) {
        return false;
    }
}
