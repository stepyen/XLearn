package com.stepyen.xlearn.activity.view.imageview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.MainActivity
import com.stepyen.xlearn.R
import kotlinx.android.synthetic.main.activity_imageview.*

/**
 * date：2020-03-31
 * author：stepyen
 * description：图片
 *
 */
class ImageViewActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_imageview)


        showBlurBtn.setOnClickListener {
            var bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            bitmap = ImageBlurUtil.blurBitmap( bitmap, iv.width, iv.height)
            iv.setImageBitmap(bitmap)
        }
    }
}