package com.stepyen.xlearn.activity.function

import android.view.View
import com.stepyen.common.utils.L
import com.stepyen.xlearn.App
import com.stepyen.xlearn.DataResouceManager
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.utils.AssetsUtil
import java.io.File
import java.io.FileOutputStream

/**
 * date：2020-03-25
 * author：stepyen
 * description：Assets 学习
 *
 *
 * Assets 的绝对路径：file:///android_asset/
 */
class AssetsActivity : BasePageActivity() {


    override fun initView() {
//        addView(R.layout.activity_assets)


        addButton("从 Assets 复制资源到sd卡下"){
            DataResouceManager().apply {
                copyMP3FromAssets()
                copyMP4FromAssets()
                copyCatGifFromAssets()
                copyTigerJpgFromAssets()
            }
        }


        addButton("从 asset 复制MP3文件到 sd下"){
            val mp3Path = DataResouceManager().copyMP3FromAssets()
            L.d("mp3文件路径：$mp3Path")

        }


    }



}