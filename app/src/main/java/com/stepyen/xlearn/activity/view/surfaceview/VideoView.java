package com.stepyen.xlearn.activity.view.surfaceview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.stepyen.xlearn.App;

import java.io.File;
import java.io.FileDescriptor;

/**
 * Created by wjy on 2019-12-06
 * E-Mail Address: 673236072@qq.com
 **/
public class VideoView extends SurfaceView implements VideoViewImpl.Controller {
    private static final String TAG = "TestVideoViewTAG";

    // 默认播放下标
    public static final int DEFAULT_PLAY_POSITION = -1;

    private SurfaceHolder mSurfaceHolder;
    private SurfaceHolder.Callback mSurfaceCallback;

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener;
    private MediaPlayer.OnPreparedListener onPreparedListener;
    private MediaPlayer.OnErrorListener onErrorListener;
    private int mPlayPosition = DEFAULT_PLAY_POSITION; // 播放进度记录

    public VideoViewImpl.Callback mCallback;


    public VideoView(Context context) {
        this(context, null);
    }

    public VideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        initListener();
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(mSurfaceCallback);
    }

    /**
     * 初始化监听
     */
    public void initListener() {
        onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playComplete();
            }
        };

        onPreparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared: 资源准备完成");
                try {
                    mp.start();
                    if (mPlayPosition > 0) {
                        mp.seekTo(mPlayPosition);
                        mPlayPosition = DEFAULT_PLAY_POSITION;
                    }
                    // 设置显示界面
                    mp.setDisplay(mSurfaceHolder);
                } catch (Exception e) {
                    e.printStackTrace();
                    playError("播放异常:" + e.toString());
                }

                startPlay();
            }
        };

        onErrorListener = new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                playError("播放异常 what:" + what + " extra:" + extra);
                return false;
            }
        };

        mSurfaceCallback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated: 界面创建");
                if (mMediaPlayer != null) {
                    mMediaPlayer.setDisplay(holder);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(TAG, "surfaceChanged: ");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed: 界面被销毁");
                release();
            }
        };
    }


    /**
     * 初始化 MediaPlayer
     */
    private void initMediaPlayer() {

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }

        // 重置mediaPaly,建议在初始滑mediaplay立即调用。
        mMediaPlayer.setOnBufferingUpdateListener(null);
        mMediaPlayer.reset();
        // 设置视频播放常亮
        mMediaPlayer.setScreenOnWhilePlaying(true);
        // 设置声音效果
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置播放完成监听
        mMediaPlayer.setOnCompletionListener(onCompletionListener);
        // 设置媒体加载完成以后回调函数。
        mMediaPlayer.setOnPreparedListener(onPreparedListener);
        // 错误监听回调函数
        mMediaPlayer.setOnErrorListener(onErrorListener);
    }


    @Override
    public void playVideo(int type, String dataSource) {
        if (TextUtils.isEmpty(dataSource)) {
            playError("播放地址为空");
            return;
        }

        if (mSurfaceHolder == null) {
            playError("SurfaceHolder为空");
            return;
        }

        mPlayPosition = DEFAULT_PLAY_POSITION;
        startLoading();


        try {

            initMediaPlayer();

            switch (type) {
                case DataSourceType.TYPE_HTTP:
                case DataSourceType.TYPE_FILE_PATH:
                    mMediaPlayer.setDataSource(dataSource);
                    break;

                case DataSourceType.TYPE_ASSET:

                    AssetFileDescriptor afd = App.get().getAssets().openFd(dataSource);
                    FileDescriptor fd = afd.getFileDescriptor();
                    mMediaPlayer.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                    afd.close();

                    break;
            }


            mMediaPlayer.prepareAsync();

        } catch (Exception e) {

            e.printStackTrace();
            playError("初始化播放器异常:" + e.toString());
        }
    }

    @Override
    public void resumeVideo() {
        try {

            if( null != mMediaPlayer) {
                startLoading();
                mMediaPlayer.setOnPreparedListener(onPreparedListener);
                mMediaPlayer.prepareAsync();
            }

        } catch (Exception e) {
            e.printStackTrace();
            playError("恢复播放失败");
        }
    }

    @Override
    public void pauseVideo() {
        try {
            if (null != mMediaPlayer) {

                mMediaPlayer.setOnPreparedListener(null);
                mMediaPlayer.pause();
                mPlayPosition = getCurrentPosition();
                pausePlay();

            }

        } catch (Exception e) {
            e.printStackTrace();
            playError("暂停播放失败");
        }
    }

    @Override
    public void release() {
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    @Override
    public int getCurrentPosition() {
        try {
            if (mMediaPlayer != null) {
                return mMediaPlayer.getCurrentPosition();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public int getDuration() {
        try {
            if (mMediaPlayer != null) {
                return mMediaPlayer.getDuration();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        if (mCallback != null) {
            mCallback.startLoading();
        }
    }

    /**
     * 开始播放
     */
    private void startPlay() {

        if (mCallback != null) {
            mCallback.startPlay();
        }
    }

    /**
     * 暂停播放
     */
    private void pausePlay() {

        if (mCallback != null) {
            mCallback.pausePlay();
        }
    }

    /**
     * 播放完成
     */
    private void playComplete() {
        if (mCallback != null) {
            mCallback.onComplete();
        }
    }

    /**
     * 播放异常
     *
     * @param msg
     */
    private void playError(String msg) {
        mPlayPosition = getCurrentPosition();
        if (mCallback != null) {
            mCallback.onError(msg);
        }
    }
}
