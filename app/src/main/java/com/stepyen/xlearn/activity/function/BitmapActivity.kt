package com.stepyen.xlearn.activity.function

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.L
import com.stepyen.xlearn.MainActivity
import kotlinx.android.synthetic.main.activity_bitmap.*

/**
 * date：2019/7/5
 * author：stepyen
 * description：Bitmap
 */
class BitmapActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_bitmap)

        show1BitmapBtn.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            iv.setImageBitmap(bitmap)
        }

        show2BitmapBtn.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            iv.setImageBitmap(bitmap)
            iv2.setImageBitmap(bitmap)
        }
        show3BitmapBtn.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            iv.background = BitmapDrawable(bitmap)
            iv2.background = BitmapDrawable(bitmap)
        }
        show4BitmapBtn.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            val bitmap2 = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height)

            bitmap.recycle()

            iv.background = BitmapDrawable(bitmap2)
            iv2.background = BitmapDrawable(bitmap2)

        }
    }
}