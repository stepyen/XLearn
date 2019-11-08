package com.stepyen.xlearn.fragment.basics.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import com.stepyen.xlearn.R
import com.stepyen.xui.utils.ResUtils
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.view.View
import androidx.appcompat.widget.DrawableUtils


/**
 * date：2019/10/28
 * author：stepyen
 * description：
 *
 */
class TestCustomView : View{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {

    }

    private val paint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            strokeWidth = 10f
            style = Paint.Style.STROKE
            color = Color.BLACK

        }
    }

    override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.apply {
        save()
        translate(width/2f,height/2f)
        paint.color = Color.RED
        drawLine(-width/2f, 0f, width/2f, 0f,paint)
        drawLine(0f,-height/2f,0f,height/2f,paint)
        val rectF = RectF(-200f,-100f,200f,100f)
        paint.color = Color.BLACK
        drawOval(rectF,paint)

        restore()

    }
}
}