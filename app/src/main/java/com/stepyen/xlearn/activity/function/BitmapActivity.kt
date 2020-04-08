package com.stepyen.xlearn.activity.function

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
            copy()
        }


        addButton("测试可变性 isMutable"){
            testMutable()
        }


        addButton("修改 Bitmap 尺寸大小"){
            modifyBitmapSize()
        }

    }

    /**
     * 测试可变性
     */
    private fun testMutable() {

        fun logMutable(name:String,bitmap1:Bitmap,bitmap2: Bitmap) {
            L.d("------以下展示 $name -------")
            L.d("Bitmap1 isMutable: ${bitmap1.isMutable}")
            L.d("Bitmap2 isMutable: ${bitmap2.isMutable}")
            L.d("Bitmap1 hashcode : $bitmap1")
            L.d("Bitmap2 hashcode : $bitmap2")

        }
        fun test1() {

            val bitmap1 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
            val bitmap2 = Bitmap.createBitmap(bitmap1)

            logMutable("test1", bitmap1,bitmap2)
//            Bitmap1 isMutable: true
//            Bitmap2 isMutable: true
//            Bitmap1 hashcode : android.graphics.Bitmap@931c5fd
//            Bitmap2 hashcode : android.graphics.Bitmap@33292f2
        }

        fun test2() {

            val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.house)
            val bitmap2 = Bitmap.createBitmap(bitmap1)

            logMutable("test2", bitmap1,bitmap2)
//            Bitmap1 isMutable: false
//            Bitmap2 isMutable: false
//            Bitmap1 hashcode : android.graphics.Bitmap@dd9c4f9
//            Bitmap2 hashcode : android.graphics.Bitmap@dd9c4f9
        }


        fun test3() {

            val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.house)
            val bitmap2 = Bitmap.createBitmap(bitmap1,0,0,100,100)

            logMutable("test3", bitmap1,bitmap2)

//            Bitmap1 isMutable: false
//            Bitmap2 isMutable: true
//            Bitmap1 hashcode : android.graphics.Bitmap@a3daa9f
//            Bitmap2 hashcode : android.graphics.Bitmap@c3847ec
        }


        test1()
        test2()
        test3()


    }

    /**
     * 修改 Bitmap 尺寸
     *
     * 修改前注意确定 Bitmap 是否可修改，也就是 isMutable = true
     *
     *
     */
    private fun modifyBitmapSize() {
        fun test1() {
//            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)  // false
//            val bitmap2 = Bitmap.createBitmap(bitmap)                               // false
//            bitmap2.width = 100   // isMutable2 = false  抛出异常
        }

        fun test2() {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)      // false
            val bitmap2 = Bitmap.createBitmap(bitmap,100,100,100,100)  // true

            iv.setImageBitmap(bitmap2)
        }

        test1()
        test2()
    }

    /**
     * 复制 Bitmap
     */
    private fun copy() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.house)
        val bitmap2 = bitmap.copy(Bitmap.Config.RGB_565,false)

        L.d("bitmap：$bitmap")
        L.d("bitmap：$bitmap2")

        iv.background = BitmapDrawable(bitmap)
        iv2.background = BitmapDrawable(bitmap2)
    }

}