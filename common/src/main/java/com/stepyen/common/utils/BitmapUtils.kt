package com.stepyen.common.utils

import android.graphics.Bitmap
import android.widget.ImageView

/**
 * date：2020-04-08
 * author：stepyen
 * description：Bitmap 工具类
 *
 */
class BitmapUtils {
    companion object{


        /**
         * 从ImageView中获取Bitmap对象
         * @param imageView
         * @return
         */
        fun getBitmapFromImageView(imageView: ImageView?): Bitmap? {

            imageView?:return null

            return imageView.run {
                isDrawingCacheEnabled = true
                val bitmap: Bitmap = drawingCache
                isDrawingCacheEnabled = false
                bitmap
            }

        }



    }
}