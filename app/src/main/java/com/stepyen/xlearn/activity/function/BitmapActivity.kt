package com.stepyen.xlearn.activity.function

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import kotlinx.android.synthetic.main.activity_bitmap.*


/**
 * date：2019/7/5
 * author：stepyen
 * description：Bitmap
 */
class BitmapActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_bitmap)

        addButton("创建 Bitmap ->是同一个对象"){
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            val bitmap2 = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height)

            L.d("bitmap：$bitmap")
            L.d("bitmap：$bitmap2")

//            bitmap.recycle()

            iv.background = BitmapDrawable(bitmap)
            iv2.background = BitmapDrawable(bitmap2)

        }


        addButton("复制 Bitmap ->不是同一个对象"){
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
            val bitmap2 = bitmap.copy(Bitmap.Config.RGB_565,false)

            L.d("bitmap：$bitmap")
            L.d("bitmap：$bitmap2")

//            bitmap.recycle()

            iv.background = BitmapDrawable(bitmap)
            iv2.background = BitmapDrawable(bitmap2)

        }

        testMutable()

    }

    private fun testMutable() {
        val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val bitmap3 = Bitmap.createBitmap(bitmap)

        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.house)
        val bitmap4 = Bitmap.createBitmap(bitmap2)

        L.d("isMutable: ${bitmap.isMutable}")   // true
        L.d("isMutable3: ${bitmap3.isMutable}") // false
        L.d("hash: ${bitmap.toString()}")
        L.d("hash: ${bitmap3.toString()}")



        L.d("-------------")
        L.d("isMutable2: ${bitmap2.isMutable}")
        L.d("isMutable4: ${bitmap4.isMutable}")
        L.d("hash: ${bitmap2.toString()}")
        L.d("hash: ${bitmap4.toString()}")


        bitmap.height = 50

    }
}