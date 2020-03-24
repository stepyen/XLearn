package com.stepyen.xlearn.activity.view.surfaceview;

/**
 * Created by wjy on 2019-12-06
 * E-Mail Address: 673236072@qq.com
 **/
public class VideoViewImpl {
    public interface Controller {
        /**
         * 播放视频
         * @param dataSource
         */
        void playVideo(String dataSource);

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
    /**
    控制器
        预加载视频
            预加载网络地址，或本地地址 视频唯一标识（用于记录播放位置）
        播放视频
            预加载完成并且未在展示广告，调用播放视频
        暂停视频
            如果视频正在播放则暂停视频
        恢复播放
            如果视频预加载完成并且未在播放则播放视频
        释放资源
            在销毁页面时，回收播放器资源
        是否正在播放
            播放中返回true，其他情况返回false
        获取当前播放位置
            用于显示播放时长
        视频总时长
            用于显示播放时长

    播放器回调
        开始加载
            显示loading页面，开头视频
        加载完成
            如果是loading页面则播放，播放广告中则等待。
        开始播放
            移除loading页面，5秒钟后全屏显示
        暂停播放
            显示弹窗广告
        播放完成
            获取列表中下一个视频，最后一个视频则关闭页面
        播放异常
            获取备用地址，或重新获取地址
           */
}
