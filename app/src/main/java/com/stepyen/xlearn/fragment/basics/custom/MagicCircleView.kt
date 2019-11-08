package com.stepyen.xlearn.fragment.basics.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * date：2019/11/7
 * author：stepyen
 * description：
 *      y
 *      \
 *      p1
 *  p4      p2      ---- x
 *      p3
 *
 *
 */
class MagicCircleView : View {
    companion object{
        const val blackMagic = 0.551915024494f  // 用来确定控制点的位置
    }

    var radius = 50f
    var maxDistance = 0f
    var c = 0f
    var offsetSize = 0f       // 圆偏移大小
    var p1= VPoint()
    var p2= HPoint()
    var p3= VPoint()
    var p4= HPoint()

    var interpolatedTime :Float = 0f


    val path:Path by lazy{
        Path()
    }

    val paint :Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.FILL
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
            context,
            attrs,
            defStyle
    ) {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        maxDistance = w - 2 * radius
        c = radius * blackMagic
        offsetSize = 0.45f * radius

        resetPoint()
    }

    private fun resetPoint() {

        p1.x = 0f
        p1.left.x = -c
        p1.right.x = c
        p1.setAllY(radius)

        p2.y = 0f
        p2.top.y = c
        p2.bottom.y = -c
        p2.setAllX(radius)

        p3.x = 0f
        p3.left.x = -c
        p3.right.x = c
        p3.setAllY(-radius)

        p4.y = 0f
        p4.top.y = c
        p4.bottom.y = -c
        p4.setAllX(-radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            save()

            translate(radius,radius)
            scale(1f, -1f)

            // 设置形状
            setShape()
            // 移动
            move()

            path.apply {
                moveTo(p1.x,p1.y)
                path.cubicTo(p1.right.x, p1.right.y, p2.top.x, p2.top.y,p2.x,p2.y)
                path.cubicTo(p2.bottom.x, p2.bottom.y, p3.right.x, p3.right.y,p3.x,p3.y)
                path.cubicTo(p3.left.x, p3.left.y, p4.bottom.x, p4.bottom.y,p4.x,p4.y)
                path.cubicTo(p4.top.x, p4.top.y, p1.left.x, p1.left.y,p1.x,p1.y)
            }

            drawPath(path,paint)

            restore()
        }
    }

    private fun move() {
        var distance = maxDistance* (interpolatedTime-0.2f)
        if (distance <0 ) {
            distance = 0f
        }
        p1.adjustX(distance)
        p2.adjustX(distance)
        p3.adjustX(distance)
        p4.adjustX(distance)
    }

    private fun setShape() {
        path.reset()
        resetPoint()
        when (interpolatedTime) {
            in 0.0f..0.2f-> {   // 最终状态：右边凸出 offsetSize
                p2.setAllX(radius+ offsetSize*interpolatedTime/0.2f)
            }

            in 0.2f..0.5f->{    // 最终状态：左边凸出 offsetSize/2;  右边凸出 offsetSize/2
                p2.setAllX(radius+ offsetSize)

                val distance:Float = (offsetSize * (interpolatedTime-0.2)/0.3f).toFloat()
                p2.adjustX(-distance/2)
                p4.adjustX(-distance/2)
            }
            in 0.5f..0.8f->{    // 最终状态：左边凸出 offsetSize
                p2.setAllX(radius+ offsetSize)

                p2.adjustX(-offsetSize/2)
                p4.adjustX(-offsetSize/2)

                val distance:Float = (offsetSize/2 * (interpolatedTime-0.5)/0.3f).toFloat()

                p2.adjustX(-distance)
                p4.adjustX(-distance)

            }
            in 0.8f..0.9f->{        // 最终状态：左边凸出 offsetSize/2
                p2.setAllX(radius+ offsetSize)

                p2.adjustX(-offsetSize/2)
                p4.adjustX(-offsetSize/2)

                val distance:Float = (offsetSize/2 ).toFloat()

                p2.adjustX(-distance)
                p4.adjustX(-distance)

                val distance2:Float = (offsetSize/2 * (interpolatedTime-0.8)/0.1f).toFloat()
                p4.adjustX(distance2)

            }
            in 0.9f..1.0f->{// 最终状态：圆

            }
        }
    }


    /**
     * 竖直方向的点，p1,p3
     */
    class VPoint {
        var x:Float = 0f
        var y:Float = 0f

        var left = PointF()
        var right = PointF()

        fun setAllY(y: Float) {
            this.y = y
            left.y = y
            right.y = y
        }

        fun adjustX(x :Float) {
            this.x += x
            left.x += x
            right.x += x
        }
    }

    /**
     * 水平方向的点，p2,p4
     */
    class HPoint {
        var x:Float = 0f
        var y:Float = 0f

        var top = PointF()
        var bottom = PointF()

        fun setAllX(x: Float) {
            this.x = x
            top.x = x
            bottom.x = x
        }
        fun adjustX(x :Float) {
            this.x += x
            top.x += x
            bottom.x += x
        }
    }



    inner class MoveAnimation : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            this@MagicCircleView.interpolatedTime = interpolatedTime
            invalidate()
        }
    }


    fun startAnimation() {
        val animation = MoveAnimation().apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }
        setAnimation(animation)
    }

}