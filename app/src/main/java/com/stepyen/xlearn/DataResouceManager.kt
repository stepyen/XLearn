package com.stepyen.xlearn

import com.stepyen.xlearn.utils.AssetsUtil

/**
 * date：2020-03-26
 * author：stepyen
 * description：
 *
 */
class DataResouceManager {



    fun copyMP3FromAssets(): String {
        var mp3FileName = "Poem_001.mp3"
        var mp3Path = "${App.get().externalCacheDir?.absolutePath}/$mp3FileName"
        AssetsUtil.copyFromAssets(App.get(), mp3FileName, mp3Path)
        return mp3Path

    }

    fun copyMP4FromAssets(): String {
        var mp4FileName = "babybus_start_zh.mp4"
        var mp4Path = "${App.get().externalCacheDir?.absolutePath}/$mp4FileName"
        AssetsUtil.copyFromAssets(App.get(), mp4FileName, mp4Path)
        return mp4Path
    }

    fun copyCatGifFromAssets(): String {
        var gifFileName = "cat.gif"
        var gifPath = "${App.get().externalCacheDir?.absolutePath}/$gifFileName"
        AssetsUtil.copyFromAssets(App.get(), gifFileName, gifPath)
        return gifPath
    }

    fun copyTigerJpgFromAssets(): String {
        var fileName = "tiger.jpg"
        var path = "${App.get().externalCacheDir?.absolutePath}/$fileName"
        AssetsUtil.copyFromAssets(App.get(), fileName, path)
        return path
    }
}