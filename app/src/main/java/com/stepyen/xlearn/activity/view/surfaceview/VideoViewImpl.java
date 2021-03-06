package com.stepyen.xlearn.activity.view.surfaceview;

/**
 * Created by wjy on 2019-12-06
 * E-Mail Address: 673236072@qq.com
 **/
public class VideoViewImpl {


    public interface Controller {
        /**
         * 播放视频
         * @param type {@link DataSourceType}
         * @param dataSource
         */
        void playVideo(int type, String dataSource);

        /**
         * 恢复视频
         */
        void resumeVideo();

        /**
         * 暂停视频
         */
        void pauseVideo();

        /**
         * 释放资源
         */
        void release();

        /**
         * 是否正在播放中
         */
        boolean isPlaying();

        /**
         * 获取当前播放位置
         * @return
         */
        int getCurrentPosition();

        /**
         * 获取总时长
         * @return
         */
        int getDuration();
    }

    public interface Callback {
        /**
         * 开始加载
         */
        void startLoading();

        /**
         * 加载完成
         */
        void onLoaded();
        /**
         * 开始播放
         */
        void startPlay();

        /**
         * 暂停播放
         */
        void pausePlay();

        /**
         * 播放完成
         */
        void onComplete();

        /**
         * 播放异常
         */
        void onError(String msg);

    }
}
