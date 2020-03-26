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

    // 当前处于暂停状态
    private var isPause = false


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
     * 开始播放 网络资源
     */
    fun startHttpDataSource(dataSource: String) {
        start(DataSourceType.HTTP, dataSource)
    }
    /**
     * 开始播放 Asset 资源
     */
    fun startAssetDataSource(dataSource: String) {
        start(DataSourceType.ASSET, dataSource)
    }
    /**
     * 开始播放 文件 资源
     */
    fun startFileDataSource(dataSource: String) {
        start(DataSourceType.FILE, dataSource)
    }

    /**
     * 开始播放
     * @param dataSourceType 资源类型
     *
     */
    private fun start(dataSourceType: DataSourceType, dataSource: String) {

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
                    DataSourceType.FILE->{
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
                isPause = true
            }
        }
    }

    /**
     * 恢复播放
     */
    fun resume() {
        mediaPlayer.apply {
            if (isPause) {
                mediaPlayer.start()
                isPause = false
            }
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
        FILE

    }
}