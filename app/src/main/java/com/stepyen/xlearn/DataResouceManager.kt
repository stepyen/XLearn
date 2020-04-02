package com.stepyen.xlearn

import com.stepyen.xlearn.utils.AssetsUtil
import kotlinx.coroutines.channels.consumesAll

/**
 * date：2020-03-26
 * author：stepyen
 * description：资源从 Assets 拷贝到 SD 卡下
 *
 */
object DataResouceManager {

    const val POEM_001_MP3 = "Poem_001.mp3"

    const val BB_SNOW_MP4 = "bb_snow.mp4"
    const val CONOR_MP4 = "conor.mp4"

    const val CAT_GIF = "cat.gif"
    const val HOUSE_PNG = "house.png"
    const val TIGER_JPG = "tiger.jpg"


    fun copyALL() {
        copyFromAssets(POEM_001_MP3)
        copyFromAssets(BB_SNOW_MP4)
        copyFromAssets(CONOR_MP4)
        copyFromAssets(CAT_GIF)
        copyFromAssets(HOUSE_PNG)
        copyFromAssets(TIGER_JPG)
    }


    fun getFilePath(name: String): String = "${Constant.BasePath}/$name"


    private fun copyFromAssets(name: String) {
        AssetsUtil.copyFromAssets(App.get(), name, getFilePath(name))
    }


}