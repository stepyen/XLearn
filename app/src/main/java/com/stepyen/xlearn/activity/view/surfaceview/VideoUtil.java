package com.stepyen.xlearn.activity.view.surfaceview;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * date：2020-03-31
 * author：stepyen
 * description：
 */
public class VideoUtil {

    /**
     * 获取视频第一帧图片
     * @param path
     * @return
     */
    public static Bitmap getFrameAtFirstTime(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime();

    }

    public static Bitmap getFrameAtTime(String path, long timeUs) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

    }
}
