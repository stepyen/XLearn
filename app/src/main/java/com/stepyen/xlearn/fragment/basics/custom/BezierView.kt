package com.stepyen.xlearn.fragment.basics.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * date：2019/11/6
 * author：stepyen
 * description：贝塞尔曲线
 *
 *  展示控制点随手指移动，曲线变化
 *
 */
class BezierView : View{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {

    }
    lateinit var startPoint: PointF
    lateinit var endPoint: PointF
    lateinit var controlPoint: PointF

    private val paint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val centerX = w / 2f
        val centerY = h / 2f

        startPoint = PointF(centerX - 200f, centerY)
        endPoint = PointF(centerX + 200f, centerY)
        controlPoint = PointF(centerX, centerY)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.run {
            drawPoint(startPoint.x, startPoint.y, paint)
            drawPoint(endPoint.x, endPoint.y, paint)
            drawPoint(controlPoint.x, controlPoint.y, paint)

            drawLine(startPoint.x, startPoint.y,controlPoint.x,controlPoint.y,paint)
            drawLine(endPoint.x, endPoint.y,controlPoint.x,controlPoint.y,paint)

            val path = Path().apply {
                moveTo(startPoint.x, startPoint.y)
                quadTo(controlPoint.x, controlPoint.y,endPoint.x,endPoint.y)
            }

            drawPath(path,paint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.let {
            controlPoint.x = it.x
            controlPoint.y = it.y
        }
        invalidate()

        return true
    }

}