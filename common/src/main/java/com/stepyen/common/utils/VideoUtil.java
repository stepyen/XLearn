package com.stepyen.common.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * date：2020-03-31
 * author：stepyen
 * description：视频工具类
 */
public class VideoUtil {

    /**
     * 获取视频的缩略图
     * @param path
     * @return
     */
    public static Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime();

    }

    /**
     * 获取视频第一帧
     * @param path
     * @return
     */
    public static Bitmap getFrameAtFirstTime(String path) {

        return getFrameAtTime(path, 0);

    }

    /**
     * 获取视频指定位置帧
     * @param path
     * @param timeUs
     * @return
     */
    public static Bitmap getFrameAtTime(String path, long timeUs) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

    }



}
