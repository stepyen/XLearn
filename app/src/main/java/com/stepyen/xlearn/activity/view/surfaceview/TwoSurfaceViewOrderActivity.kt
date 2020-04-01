package com.stepyen.xlearn.activity.view.surfaceview

import android.media.MediaMetadataRetriever
import android.view.View
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_tow_surfaceview_order.*


/**
 * date：2020-03-24
 * author：stepyen
 * description：两个 SurfaceView 层级顺序研究
 *
 *
 *

视频切换

SurfaceView fromView = (SurfaceView) mSPreviewContainer.getChildAt(0);
SurfaceView toView = (SurfaceView) mLPreviewContainer.getChildAt(0);

mLPreviewContainer.removeAllViews();
mSPreviewContainer.removeAllViews();
fromView.setZOrderOnTop(false);
fromView.setZOrderMediaOverlay(false);
mLPreviewContainer.addView(fromView);
toView.setZOrderOnTop(true);
toView.setZOrderMediaOverlay(true);
mSPreviewContainer.addView(toView);

 *
 */
class TwoSurfaceViewOrderActivity : BasePageActivity() {

    var isChange: Boolean = true

    override fun initView() {
        addView(R.layout.activity_tow_surfaceview_order)

        addButton("view1\nsetZOrderOnTop") {

            play() {
                videoview1.setZOrderOnTop(isChange)
            }
        }

        addButton("view2\nsetZOrderOnTop") {

            play() {
                videoview2.setZOrderOnTop(isChange)
            }
        }

        addButton("view1、view2\nsetZOrderOnTop") {

            play() {
                videoview1.setZOrderOnTop(isChange)
                videoview2.setZOrderOnTop(!isChange)
            }
        }
        addButton("view1、view2\nsetZOrderMediaOverlay") {

            play() {
                videoview1.setZOrderMediaOverlay(isChange)
                videoview2.setZOrderMediaOverlay(!isChange)
            }
        }
        addButton("view1、view2\nsetZOrderOnTop\nsetZOrderMediaOverlay") {

            play() {
                videoview1.setZOrderOnTop(isChange)
                videoview1.setZOrderMediaOverlay(isChange)
                videoview2.setZOrderOnTop(!isChange)
                videoview2.setZOrderMediaOverlay(!isChange)
            }
        }
        addButton("view1\nsetZOrderMediaOverlay\nremoveView") {

            play() {
                fl_parent.removeView(videoview1)
                fl_parent.removeView(videoview2)
                videoview1.setZOrderMediaOverlay(isChange)
                videoview2.setZOrderMediaOverlay(!isChange)
                fl_parent.addView(videoview1, videoview1.layoutParams)
                fl_parent.addView(videoview2, videoview2.layoutParams)
            }
        }

        addButton("view1、view2\nsetZOrderOnTop\nsetZOrderMediaOverlay\nremoveView") {

            play() {
                fl_parent.removeView(videoview1)
                fl_parent.removeView(videoview2)
                videoview1.setZOrderOnTop(isChange)
                videoview1.setZOrderMediaOverlay(isChange)
                videoview2.setZOrderOnTop(!isChange)
                videoview2.setZOrderMediaOverlay(!isChange)
                fl_parent.addView(videoview1, videoview1.layoutParams)
                fl_parent.addView(videoview2, videoview2.layoutParams)

            }
        }

    }

    private fun play(action: () -> Unit) {
        isChange = !isChange
        action()
        videoview1.setAssetsPath("bb_splash.mp4")
        videoview2.setAssetsPath("conor.mp4")
        videoview1.start()
        videoview2.start()
    }



}