package com.stepyen.xlearn.activity.thrid.glide

import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.stepyen.common.utils.L
import com.stepyen.xlearn.App
import com.stepyen.xlearn.DataResouceManager
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_glide.*
import kotlinx.android.synthetic.main.activity_msa.*

/**
 * Glide
 *
 *
 *
 */
class GlideActivity : BasePageActivity() {


    companion object {
        const val TAG = "GlideTAG"
    }

    override fun initView() {
        addView(R.layout.activity_glide)

        val dataResouceManager = DataResouceManager()
        val catGif = dataResouceManager.copyCatGifFromAssets()


        showGifBtn.setOnClickListener {
            Glide.with(this).load(catGif).into(iv)
        }

        addButton("显示 assets 文件下的图片"){
            val imagePath = "file:///android_asset/test/smalltiger.jpg"
            Glide.with(this).load(imagePath).into(iv)
        }

        addButton("显示 assets 文件下的图片 uri"){
            val imagePath = "file:///android_asset/test/smalltiger.jpg"
            val uri = Uri.parse(imagePath)
            L.d("$uri")
            Glide.with(this).load(uri).into(iv)
        }

    }


}
