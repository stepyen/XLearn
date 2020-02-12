package com.stepyen.xlearn.fragment.basics.custom;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * date：2018/12/7
 * author：stepyen
 * description：贝塞尔曲线
 */
public class BezierHeadView extends View {

    private Context context;
    private float mWidth =0f;
    private float mHeight=0f;
    private Path mPath;
    private Paint mPaint;
    private float offset = 0;

    public BezierHeadView(Context context) {
        this(context, null);
    }

    public BezierHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierHeadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAlpha(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        //设置弧形下部分需要填充的颜色
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //控制着幅度的高度(这个主要控制弧形的高度,如果想要幅度大一点，就把这个值修改大一些即可)
        Float h=(mHeight/8)*7;

        mPath.moveTo(0F,h);

        /***
         * 二阶贝塞尔曲线
         * 修改offset定制自己的需求
         */
//        mPath.quadTo(mWidth/2,mHeight,mWidth,h);
        mPath.quadTo(mWidth/2,mHeight,mWidth,h);

        mPath.lineTo(mWidth, mHeight+offset);
        mPath.lineTo(0.0F, mHeight+offset);

        canvas.drawPath(mPath,mPaint);


    }


}
