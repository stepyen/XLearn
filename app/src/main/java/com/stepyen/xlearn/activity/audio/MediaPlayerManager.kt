package com.stepyen.xlearn.activity.audio

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.text.TextUtils
import com.stepyen.xlearn.App
import java.lang.Exception

/**
 * date：2020-03-25
 * author：stepyen
 * description：音频播放
 *
 */
class MediaPlayerManager {

    /**
     * 音频播放者
     */
    private val mediaPlayer: MediaPlayer by lazy {
        MediaPlayer().apply {
            isLooping = false
            setOnCompletionListener {
                it.stop()
                it.reset()
            }
        }
    }


    /**
     * 开始播放
     * @param dataSourceType 资源类型
     *
     */
    fun start(dataSourceType: DataSourceType, dataSource: String) {

        if (TextUtils.isEmpty(dataSource)) {
            return
        }

        try {

            mediaPlayer.apply {

                if (isPlaying) {
                    stop()
                }

                reset()

                when (dataSourceType) {
                    DataSourceType.HTTP->{
                        setDataSource(dataSource)
                    }
                    DataSourceType.ASSET->{
                        val afd: AssetFileDescriptor = App.get().assets.openFd(dataSource)
                        val fd = afd.fileDescriptor
                        setDataSource(fd, afd.startOffset, afd.length)
                        afd.close()
                    }
                    DataSourceType.FILE_PATH->{
                        setDataSource(dataSource)
                    }
                }

                prepare()
                start()
            }

        } catch (e: Exception) {
            e.printStackTrace()

        }


    }

    /**
     * 暂停播放
     */
    fun pause() {
        mediaPlayer.apply {
            if (isPlaying) {
                pause()
            }
        }
    }

    /**
     * 恢复播放
     */
    fun resume() {
        mediaPlayer.apply {
            mediaPlayer.start()
        }
    }


    /**
     * 释放资源
     */
    fun release() {
        mediaPlayer?.apply {
            stop()
            release()
        }
    }


    /**
     * 设置是否可以重复播放
     */
    fun isLooping(isLooping: Boolean) {
        mediaPlayer.isLooping = isLooping
    }

    /**
     * 是否在播放
     */
    fun isPlayer(): Boolean {
        return mediaPlayer.isPlaying
    }


    enum class DataSourceType {
        /**
         * 网络地址
         */
        HTTP,

        /**
         * asset 名称
         */
        ASSET,

        /**
         * 文件地址
         */
        FILE_PATH

    }
}