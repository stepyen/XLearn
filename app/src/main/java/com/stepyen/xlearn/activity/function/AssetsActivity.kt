package com.stepyen.xlearn.activity.function

import android.view.View
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
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


    var mp3Path = ""


    override fun initView() {
//        addView(R.layout.activity_assets)

        addButton("从 asset 复制MP3文件到 sd下", View.OnClickListener {
            copyFromAssetToSDPath()
            L.d("mp3文件路径：$mp3Path")
        })


    }


    /**
     * 获取 assets 下的文件
     */
    private fun getAssetsList() {
        assets.list("")
    }

    /**
     * 从 assets 复制文件到 SD 卡下
     */
    private fun copyFromAssetToSDPath() {
        mp3Path = "${externalCacheDir?.absolutePath}/Poem_001.mp3"
        val inputStream = assets.open("Poem_001.mp3")
        val file = File(mp3Path)
        val fileOutputStream = FileOutputStream(file)
        var buffer = ByteArray(1024)

        var byteCount = inputStream.read(buffer)

        while(byteCount!=-1) {
            fileOutputStream.write(buffer,0,byteCount)
            byteCount=inputStream.read(buffer)
        }

        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
    }


}