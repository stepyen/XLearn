package com.stepyen.common.listen

import android.media.MediaPlayer

/**
 * Created by wjy on 2019-12-06
 * E-Mail Address: 673236072@qq.com
 */
class VideoViewImpl {
    interface Controller {
        /**
         * 播放视频
         * @param dataSource
         */
        fun playVideo(path: String)

        /**
         * 恢复视频
         */
        fun resumeVideo()

        /**
         * 暂停视频
         */
        fun pauseVideo()

        /**
         * 释放资源
         */
        fun release()

        /**
         * 是否正在播放中
         */
        val isPlaying: Boolean

        /**
         * 获取当前播放位置
         * @return
         */
        val currentPosition: Int

        /**
         * 获取总时长
         * @return
         */
        val duration: Int
    }

    interface Callback {
        /**
         * 开始加载
         */
        fun startLoading()

        /**
         * 加载完成
         */
        fun onLoaded()

        /**
         * 开始播放
         */
        fun startPlay()

        /**
         * 暂停播放
         */
        fun pausePlay()

        /**
         * 播放完成
         */
        fun onComplete()

        /**
         * 播放异常
         */
        fun onError(mp: MediaPlayer?, what: Int, extra: Int)

    }
}