package com.stepyen.xlearn.activity.audio

import android.media.MediaPlayer
import android.media.MediaRecorder
import com.orhanobut.logger.Logger
import com.stepyen.constant.DateFormatConstants
import com.stepyen.xutil.data.DateUtils
import java.text.SimpleDateFormat

/**
 * date：2020-02-15
 * author：stepyen
 * description：媒体管理
 *
 */
class MediaManager(private val audioPath: String) {

    var lastAudioPath = ""

    /**
     * 音频收集者
     */
    private val mediaRecorder: MediaRecorder by lazy {
        MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC);
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        }
    }

    /**
     * 音频播放者
     */
    private val mediaPlayer:MediaPlayer by lazy {
        MediaPlayer().apply {
            isLooping = false
            setOnCompletionListener {
                it.stop()
                it.reset()
            }
        }
    }

    /**
     * 开始录音
     */
    fun start() {
        mediaRecorder.apply {
            val fileName = DateUtils.millis2String(System.currentTimeMillis(), SimpleDateFormat(DateFormatConstants.yyyyMMddHHmmssNoSep)) + ".mp3"
            lastAudioPath = audioPath+fileName
            setOutputFile(lastAudioPath);
            prepare();
            start()
        }
    }

    /**
     * 停止录音
     */
    fun stop() {
        mediaRecorder.apply {
            stop()
            reset()
        }
    }

    /**
     * 回放上一个录音文件
     */
    fun playback() {
        mediaPlayer.apply {
            setDataSource(lastAudioPath)
            prepare()
            start()
        }
    }

    /**
     * 停止回放
     */
    fun stopPlayback() {
        mediaPlayer.apply {
            stop()
            reset()
        }

    }

    /**
     * 释放资源
     */
    fun destory() {
        mediaRecorder?.apply {
            release()
        }

        mediaPlayer?.apply {
            release()
        }
    }

}