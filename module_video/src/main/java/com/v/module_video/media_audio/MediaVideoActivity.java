package com.v.module_video.media_audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_video.R;

import java.io.IOException;

/**
 * 视频播放器
 * https://www.runoob.com/w3cnote/android-tutorial-mediaplayer.html
 * 音频链接：http://feed.youshu.cc/readwith/media/27518/1045924_1_480733540573511680.mp3
 */
public class MediaVideoActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {
    private Button btn_play;
    private Button btn_pause;
    private Button btn_stop;
    private boolean isRelease = true;   //判断是否MediaPlayer是否释放的标志
    private final MediaPlayer mPlayer = new MediaPlayer();
    private SurfaceView sfv_show;
    private SurfaceHolder surfaceHolder;
    private progressThread progressThread;
    private SeekBar seek_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_audio);
        bindViews();

        //I am thinking about use a thread to listen to the progress of the music
        //开启进度条跟踪线程
        progressThread = new progressThread();


        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("zm1234", "onPrepared: ");
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("zm1234", "onCompletion: ");
            }
        });
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("zm1234", "onError: ");
                return false;
            }
        });

        mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("zm1234", "onBufferingUpdate: ");
            }
        });

        mPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.i("zm1234", "onInfo: ");
                return false;
            }
        });

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());
                //松开之后音乐跳转到相应位置
            }
        });
    }

    private void bindViews() {
        sfv_show = (SurfaceView) findViewById(R.id.sfv_show);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        //初始化SurfaceHolder类，SurfaceView的控制器
        surfaceHolder = sfv_show.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(320, 220);   //显示的分辨率,不设置为视频默认
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_play) {
            if (isRelease) {
                try {
                    //①raw下的资源：
                    //MediaPlayer.create(this, R.raw.test);

                    //②本地文件路径：
                    //mPlayer.setDataSource("/sdcard/test.mp3");
                    Log.i("zm1234", "btn_play: ");
                    //③网络URL文件：
                    String url = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4"; // your URL here
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.setDataSource(url);
                    mPlayer.prepare(); // might take long! (for buffering, etc)
                    mPlayer.start(); //开始播放
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //根据音乐的时长设置进度条的最大进度
                seek_bar.setMax(mPlayer.getDuration());
                progressThread.start();

                isRelease = false;
            }
            btn_play.setEnabled(false);
            btn_pause.setEnabled(true);
            btn_stop.setEnabled(true);
        } else if (id == R.id.btn_pause) {
            mPlayer.pause();     //停止播放
            btn_play.setEnabled(true);
            btn_pause.setEnabled(false);
            btn_stop.setEnabled(false);
        } else if (id == R.id.btn_stop) {
            mPlayer.reset();     //重置MediaPlayer
            mPlayer.release();   //释放MediaPlayer
            isRelease = true;
            btn_play.setEnabled(true);
            btn_pause.setEnabled(false);
            btn_stop.setEnabled(false);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class progressThread extends Thread{
        boolean flag=true;

        @Override
        public void run() {
            super.run();
            while(flag){
                if(mPlayer.isPlaying()) {
                    seek_bar.setProgress(mPlayer.getCurrentPosition()); //实时获取播放音乐的位置并且设置进度条的位置
                }
            }
        }
        //下面的函数是外部调用种植线程的，因为现在是不提倡直接带哦用stop方法的
        public void stopThread(){
            this.flag=false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mPlayer.release();
    }
}