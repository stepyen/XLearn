package com.stepyen.common.widget

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.MediaPlayer
import android.text.TextUtils
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.stepyen.common.listen.VideoViewImpl
import com.stepyen.common.utils.L

/**
 * date：2020-04-02
 * author：stepyen
 * description：视频
 *
 */
class BBVideoView : SurfaceView, VideoViewImpl.Controller {

    companion object {
        const val DEFAULT_PLAY_POSITION = -1

        /**
         * 网络地址
         */
        const val TYPE_HTTP = 1

        /**
         * asset 名称
         */
        const val TYPE_ASSET = 2

        /**
         * 文件地址
         */
        const val TYPE_FILE_PATH = 3


    }


    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var surfaceHolder: SurfaceHolder? = null

    var callback: VideoViewImpl.Callback? = null


    private val onCompletionListener: MediaPlayer.OnCompletionListener by lazy {
        MediaPlayer.OnCompletionListener {
            callback?.onComplete()
        }
    }

    private val onPreparedListener: MediaPlayer.OnPreparedListener by lazy {
        MediaPlayer.OnPreparedListener { mp ->
            if (playPosition > 0) {
                mp?.apply {
                    seekTo(playPosition)
                    playPosition = DEFAULT_PLAY_POSITION
                }
            }

            mp?.apply {
                setDisplay(surfaceHolder)
                start()
                callback?.startPlay()
            }

        }
    }

    private val onErrorListener: MediaPlayer.OnErrorListener by lazy {
        MediaPlayer.OnErrorListener { mp, what, extra ->
            callback?.onError(mp, what, extra)

            true
        }
    }

    private val onInfoListener: MediaPlayer.OnInfoListener by lazy {
        MediaPlayer.OnInfoListener { mp, what, extra ->
            when (what) {
                MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START -> {
                    callback?.onLoaded()
                }
            }

            true
        }
    }

    private val surfaceHolderCallback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
            L.d("surfaceChanged")
        }

        override fun surfaceCreated(holder: SurfaceHolder) {
            L.d("surfaceCreated")
            surfaceHolder = holder
            playVideo(playPath,playType)
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            L.d("surfaceDestroyed")
            release()
        }
    }

    private var playPosition = DEFAULT_PLAY_POSITION

    private lateinit var mContext: Context

    private var playPath:String=""
    private var playType:Int = TYPE_FILE_PATH

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {
        mContext = context
        init()
    }


    private fun init() {
        holder?.apply {
            addCallback(surfaceHolderCallback)
        }

    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()?.apply {
            setScreenOnWhilePlaying(true)
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setOnCompletionListener(onCompletionListener)
            setOnErrorListener(onErrorListener)
            setOnPreparedListener(onPreparedListener)
            setOnInfoListener(onInfoListener)
        }

    }


    fun playAssetsVideo(path: String) {
        playVideo(path, TYPE_ASSET)
    }


    override fun playVideo(path: String) {
        playVideo(path, TYPE_FILE_PATH)
    }


    private fun playVideo(path: String, type:Int) {
        playPath = path
        playType = type
        if (TextUtils.isEmpty(path)) return

        if (surfaceHolder == null) return

        release()
        try {

            initMediaPlayer()
            mediaPlayer?.apply {

                when(type) {
                    TYPE_HTTP,TYPE_FILE_PATH->{
                        setDataSource(path)
                    }
                    TYPE_ASSET->{
                        val afd: AssetFileDescriptor = mContext.assets.openFd(path)
                        val fd = afd.fileDescriptor
                        setDataSource(fd, afd.startOffset, afd.length)
                        afd.close()
                    }
                }

                prepareAsync()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            callback?.onError(mediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0)
        }


    }


    override fun resumeVideo() {

        mediaPlayer?.apply {
            mediaPlayer.start()
        }
    }

    override fun pauseVideo() {

        mediaPlayer?.apply {
            pause()
            playPosition = currentPosition
            callback?.pausePlay()
        }

    }

    override fun release() {
        try {
            mediaPlayer?.apply {
                reset()
                release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override val isPlaying: Boolean
        get() = mediaPlayer.isPlaying

    override val currentPosition: Int
        get() = mediaPlayer.currentPosition;

    override val duration: Int
        get() = mediaPlayer.duration


}