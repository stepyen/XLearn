package com.stepyen.xlearn.activity.view.surfaceview

import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_surfaceview.*

/**
 * date：2020-03-24
 * author：stepyen
 * description：
 *
 */
class SurfaceViewActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_surfaceview)


        playVideoBtn.setOnClickListener {
            videoview.playVideo("babybus_start_zh.mp4")
        }

        pauseVideoBtn.setOnClickListener {
            videoview.pauseVideo()
        }

        resumeVideoBtn.setOnClickListener {
            videoview.resumeVideo()
        }
    }
}