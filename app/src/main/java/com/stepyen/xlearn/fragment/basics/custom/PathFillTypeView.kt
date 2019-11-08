package com.stepyen.xlearn.fragment.basics.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * date：2019/11/8
 * author：stepyen
 * description：
 *
 */
class PathFillTypeView : View{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {

    }

    val paint:Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
            strokeWidth  = 10f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            save()
            translate(width/2f, height/2f)

            val path1 = Path().apply {
                addCircle(0f, 0f, 200f, Path.Direction.CW)
            }
            val path2 = Path().apply {
                addRect(0f, -200f, 200f, 200f, Path.Direction.CW)
            }
            val path3 = Path().apply {
                addCircle(0f, -100f, 100f, Path.Direction.CW)
            }
            val path4 = Path().apply {
                addCircle(0f, 100f, 100f, Path.Direction.CCW)
            }

            path1.op(path2, Path.Op.DIFFERENCE)     // 大圆取一半
            path1.op(path3, Path.Op.UNION)          // 大圆拼接小圆
            path1.op(path4, Path.Op.DIFFERENCE)     // 大圆抠除一个小圆


            path1.reset()
            drawPath(path1, paint)
            restore()
        }
    }
}