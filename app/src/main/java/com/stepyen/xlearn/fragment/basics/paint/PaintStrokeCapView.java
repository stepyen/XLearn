package com.stepyen.xlearn.fragment.basics.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * date：2019/10/25
 * author：stepyen
 * description：
 */
public class PaintStrokeCapView extends View {
    public PaintStrokeCapView(Context context) {
        this(context, null);
    }

    public PaintStrokeCapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintStrokeCapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(60);
    }

    private Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(0, 30, 300, 30,mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(0, 130, 300, 130,mPaint);

        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(0, 230, 300, 230,mPaint);
    }
}
