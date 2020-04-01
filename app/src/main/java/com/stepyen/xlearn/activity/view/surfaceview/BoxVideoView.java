package com.stepyen.xlearn.activity.view.surfaceview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.stepyen.xlearn.App;

import java.io.FileDescriptor;

/**
 * <pre>
 *
 *      author LYB
 *      time   18/4/23 下午3:40
 *      des
 *
 * </pre>
 */

public class BoxVideoView extends SurfaceView {

    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;

    private Context mContext;
    private SurfaceHolder mSurfaceHolder = null;
    private MediaPlayer mMediaPlayer = null;

    private MediaPlayer.OnErrorListener mOnErrorListener;

    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    private RemoveSplashViewListener mRemoveSplashViewListener;
    private Uri mUri;
    private String mAssetsPath;

    private int mCurrentState = STATE_IDLE;
    private MediaPlayer.OnPreparedListener mPreparedListener =
            new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    mCurrentState = STATE_PREPARED;
                    mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
                                if (mRemoveSplashViewListener != null) {
                                    mRemoveSplashViewListener.remove();
                                }
                            }

                            return true;
                        }
                    });


                    start();
                }
            };
    private MediaPlayer.OnErrorListener mErrorListener =
            new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {

                    mCurrentState = STATE_ERROR;

                    if (mOnErrorListener != null) {
                        mOnErrorListener.onError(mp, what, extra);
                    }

                    return true;
                }
            };
    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {

                    mCurrentState = STATE_PLAYBACK_COMPLETED;

                    if (mOnCompletionListener != null) {
                        mOnCompletionListener.onCompletion(mMediaPlayer);
                    }
                }
            };
    private SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback() {
        public void surfaceChanged(SurfaceHolder holder, int format,
                                   int w, int h) {

        }

        public void surfaceCreated(SurfaceHolder holder) {
            mSurfaceHolder = holder;
            openVideo();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            mSurfaceHolder = null;
            release();
        }
    };

    public BoxVideoView(Context context) {
        super(context);
        init(context);
    }

    public BoxVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoxVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setVideoPath(String path) {
        mUri = Uri.parse(path);
        mAssetsPath = null;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void setAssetsPath(String path) {
        mAssetsPath = path;
        mUri = null;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener l) {
        mOnErrorListener = l;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener l) {
        mOnCompletionListener = l;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        mPreparedListener = l;
    }

    public boolean isPlaying() {
        return mCurrentState == STATE_PLAYING;
    }

    private void init(Context context) {
        mContext = context;
        getHolder().addCallback(mSHCallback);
    }

    private void openVideo() {

        if (mUri == null && TextUtils.isEmpty(mAssetsPath)) return;

        if (mSurfaceHolder == null) return;

        release();

        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnPreparedListener(mPreparedListener);

            if (mUri != null) {
                mMediaPlayer.setDataSource(mContext, mUri);
            } else {
                AssetFileDescriptor afd = App.get().getAssets().openFd(mAssetsPath);
                FileDescriptor fd = afd.getFileDescriptor();
                mMediaPlayer.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                afd.close();
            }
            mCurrentState = STATE_PREPARING;
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {

            mErrorListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        }

    }

    public void start() {
        if (canPlay()) {
            mMediaPlayer.start();
            mCurrentState = STATE_PLAYING;
        }
    }

    private boolean canPlay() {
        return mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING;
    }

    private void release() {

        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mCurrentState = STATE_IDLE;
        }
    }

    public void setReomveSplashViewListener(RemoveSplashViewListener l) {
        mRemoveSplashViewListener = l;
    }
}
