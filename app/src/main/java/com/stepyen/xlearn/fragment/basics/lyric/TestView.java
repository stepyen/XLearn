package com.stepyen.xlearn.fragment.basics.lyric;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.stepyen.xlearn.R;

import androidx.core.content.ContextCompat;

/**
 * date：2020-01-02
 * author：stepyen
 * description：
 */
public class TestView extends View {
    private static final String TAG = "TestView";
    private Scroller mScroller;
    private Paint mPaint;
    private Context mContext;
    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        mScroller = new Scroller(context, new DecelerateInterpolator());

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getColor(R.color.lyric_wait_read_text));
        mPaint.setTextSize(46);

    }



    public void startScrollBy(int dx,int dy) {

        mScroller.forceFinished(true);
        int startX = getScrollX();
        int startY = getScrollY();
        Log.d(TAG, "startScrollBy: "+startX+"   "+startY);
        mScroller.startScroll(startX,startY,dx,dy,1000);
        invalidate();
        playAlreadyReadAnimation();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int size = (int) (46 * value);
        mPaint.setTextSize(size);
        Log.d(TAG, "onDraw: "+value);
        canvas.drawText("哈哈哈",100,100,mPaint);
    }

    float value;

    private void playAlreadyReadAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f,1.25f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
            }
        });
        animator.start();
    }

    private void playAnimation() {

    }


    private int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }

}
