package com.stepyen.xlearn.fragment.utils.life_cycle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * date：2019/6/20
 * author：stepyen
 * description：view的生命周期
 */
public class LifecycleView extends View {

    private static final String TAG = "LifecycleViewTAG";

    private int size = 0;

    public LifecycleView(Context context) {
        this(context, null);
    }

    public LifecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LifecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i(TAG, "LifecycleView: ");
        Log.i(TAG, "LifecycleView-getMeasuredWidth: "+getMeasuredWidth());
        Log.i(TAG, "LifecycleView-getWidth: "+getWidth());

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "onFinishInflate: ");
    }

    public void setSize(int size) {
        this.size = size;
        Log.i(TAG, "setSize: ");
        Log.i(TAG, "setSize-getMeasuredWidth: "+getMeasuredWidth());
        Log.i(TAG, "setSize-getWidth: "+getWidth());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure: ");
        Log.i(TAG, "onMeasure-getMeasuredWidth: "+getMeasuredWidth());
        Log.i(TAG, "onMeasure-getWidth: "+getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: ");
        Log.i(TAG, "onSizeChanged-getMeasuredWidth: "+getMeasuredWidth());
        Log.i(TAG, "onSizeChanged-getWidth: "+getWidth());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");
    }


}
