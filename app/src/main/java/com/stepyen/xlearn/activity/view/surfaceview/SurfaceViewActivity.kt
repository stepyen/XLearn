package com.stepyen.xlearn.activity.view.surfaceview

import android.view.View
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_surfaceview.*
import java.io.File
import java.io.FileOutputStream

/**
 * date：2020-03-24
 * author：stepyen
 * description：
 *
 */
class SurfaceViewActivity : BasePageActivity() {
    var mp4Path = ""
    override fun initView() {
        addView(R.layout.activity_surfaceview)


        playAssetVideoBtn.setOnClickListener {
            videoview.playVideo(DataSourceType.TYPE_ASSET, "babybus_start_zh.mp4")
        }

        playFilePathVideoBtn.setOnClickListener {

            videoview.playVideo(DataSourceType.TYPE_FILE_PATH, mp4Path)
        }

        pauseVideoBtn.setOnClickListener {
            videoview.pauseVideo()
        }

        resumeVideoBtn.setOnClickListener {
            videoview.resumeVideo()
        }

        copyFromAssetToSDPath()
        L.d("mp4文件路径：$mp4Path")
    }

    /**
     * 从 Asset 复制mp4到sd卡下
     */
    private fun copyFromAssetToSDPath() {
        mp4Path = "${externalCacheDir?.absolutePath}/babybus_start_zh.mp4"
        val file = File(mp4Path)
        if (file.exists()) {
            return
        }

        val inputStream = assets.open("babybus_start_zh.mp4")
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


    override fun onResume() {
        super.onResume()
        videoview.resumeVideo()
    }

    override fun onPause() {
        super.onPause()
        videoview.pauseVideo()
    }




}