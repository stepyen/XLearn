package com.stepyen.xlearn.fragment.basics.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
/**
 * date：2019/10/28
 * author：stepyen
 * description：
 *
 */
class RoundRectImageView : ImageView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
    }

    private val paint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            //            // 保存图层
//            val saveCount = saveLayer(0f,0f,width.toFloat(),height.toFloat(),paint)
//            // 绘制圆角
//            val receF = RectF(0f, 0f, width.toFloat(), height.toFloat())
//            drawRoundRect(receF, 15f, 15f, paint)
//            // 设置图层混合模式
//            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//            // 绘制图片
//            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.icon_dog)
//            drawBitmap(bitmap,0f,0f,paint)
//            // 恢复图层
//            restoreToCount(saveCount)

            val widthFloat = width.toFloat()
            val heightFloat = height.toFloat()

            val path = Path()
            path.moveTo(12f, 0f)
            path.lineTo(widthFloat - 12, 0f)
            path.quadTo(widthFloat, 0f, widthFloat, 12f)
            path.lineTo(widthFloat, heightFloat - 12f)
            path.quadTo(widthFloat, heightFloat, widthFloat - 12, heightFloat)
            path.lineTo(12f, heightFloat)
            path.quadTo(0f, heightFloat, 0f, heightFloat - 12)
            path.lineTo(0f, 12f)
            path.quadTo(0f, 0f, 12f, 0f)
            clipPath(path)
        }
        super.onDraw(canvas)
    }

}