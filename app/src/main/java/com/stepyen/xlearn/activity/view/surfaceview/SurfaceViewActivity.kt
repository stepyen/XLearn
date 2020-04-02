package com.stepyen.xlearn.activity.view.surfaceview

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.view.View
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import com.stepyen.common.listen.VideoViewImpl
import com.stepyen.common.utils.VideoUtil
import com.stepyen.xlearn.DataResouceManager
import kotlinx.android.synthetic.main.activity_surfaceview.*
import java.io.File
import java.io.FileOutputStream
import java.sql.DatabaseMetaData

/**
 * date：2020-03-24
 * author：stepyen
 * description：
 *
 */
class SurfaceViewActivity : BasePageActivity() {

    var mp4Path = DataResouceManager.getFilePath(DataResouceManager.BB_SNOW_MP4)
    init {
        L.d("mp4 地址：${mp4Path}")
    }


    override fun initView() {
        addView(R.layout.activity_surfaceview)

        playAssetVideoBtn.setOnClickListener {
           videoview.playAssetsVideo(DataResouceManager.BB_SNOW_MP4)
        }

        playFilePathVideoBtn.setOnClickListener {

            videoview.playVideo(mp4Path)

        }

        pauseVideoBtn.setOnClickListener {
            videoview.pauseVideo()
        }

        resumeVideoBtn.setOnClickListener {
            videoview.resumeVideo()
        }


        getFrame()

        initCallback()

    }


    private fun getFrame() {

        getThumbBtn.setOnClickListener {
            val bitmap = VideoUtil.getVideoThumb(mp4Path)
            iv.setImageBitmap(bitmap)
        }

        getFirstFrameBtn.setOnClickListener {
            val bitmap = VideoUtil.getFrameAtFirstTime(mp4Path)
            iv.setImageBitmap(bitmap)
        }

        getFrameBtn.setOnClickListener {
            val bitmap = VideoUtil.getFrameAtTime(mp4Path,0L)
            iv.setImageBitmap(bitmap)
        }

    }


    private fun initCallback() {
        videoview.callback = object : VideoViewImpl.Callback{
            override fun startLoading() {
                L.d("startLoading")
            }

            override fun onLoaded() {
                L.d("onLoaded")
            }

            override fun startPlay() {
                L.d("startPlay")
            }

            override fun pausePlay() {
                L.d("pausePlay")
            }

            override fun onComplete() {
                L.d("onComplete")
            }

            override fun onError(mp: MediaPlayer?, what: Int, extra: Int) {
                L.d("onError")
            }

        }

    }


    override fun onResume() {
        super.onResume()
        videoview.playVideo(mp4Path)
    }

    override fun onPause() {
        super.onPause()
        videoview.pauseVideo()
    }





}