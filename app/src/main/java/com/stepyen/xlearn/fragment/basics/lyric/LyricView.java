package com.stepyen.xlearn.fragment.basics.lyric;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.stepyen.xlearn.R;
import com.stepyen.xutil.shape.ShapeBuilder;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * date：2020-01-02
 * author：stepyen
 * description：
 */
public class LyricView extends View {

    // 最大行数
    private final static int MAX_ROW = 4;
    private Scroller mScroller;

    private Context mContext;
    private Paint waitReadTextPaint;
    private Paint currentReadTextPaint;
    private Paint alreadyReadTextPaint;
    private Paint currentReadBgPaint;

    private List<LyricItem> data = new ArrayList<>();
    private int currentIndex = -1;
    private int currentShowIndex = 0;


    public LyricView(Context context) {
        this(context, null);
    }

    public LyricView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LyricView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        setBg();
        mScroller = new Scroller(context, new DecelerateInterpolator());

        init();
    }

    private void init() {

        waitReadTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        waitReadTextPaint.setColor(getColor(R.color.lyric_wait_read_text));
        waitReadTextPaint.setTextSize(46);

        currentReadTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        currentReadTextPaint.setColor(getColor(R.color.lyric_current_read_text));
        currentReadTextPaint.setTextSize(54);

        alreadyReadTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        alreadyReadTextPaint.setColor(getColor(R.color.lyric_already_read_text));
        alreadyReadTextPaint.setTextSize(46);

        currentReadBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        currentReadBgPaint.setColor(getColor(R.color.lyric_current_read_bg));

    }

    /**
     * 设置歌词数据
     *
     * @param data
     */
    public void setData(List<String> data) {
        initData(data);
        currentIndex = 0;
        currentShowIndex = 0;
        invalidate();
    }

    private void initData(List<String> stringList) {
        if (data == null) {
            data = new ArrayList<>();
        }

        data.clear();

        for (int i = 0, size = stringList.size(); i < size; i++) {
            String text = stringList.get(i);
            LyricItem lyricItem = new LyricItem();
            lyricItem.status = LyricItem.STATUS_WAIT_READ;
            lyricItem.text = text;
            data.add(lyricItem);
        }
    }

    /**
     * 设置当前播放歌词下标
     *
     * @param index
     */
    public void setCurrentIndex(int index) {
        currentIndex = index;
        mScroller.startScroll(0,getScrollY(),0,getHeight()/4,1000);
        invalidate();
        playAlreadyReadAnimation();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data == null) {
            return;
        }
        int height = getHeight();
        int width = getWidth();
        Rect rect = new Rect(0, height / 4 * (2+currentIndex), width, height / 4 * (3+currentIndex));
        canvas.drawRect(rect, currentReadBgPaint);


        for (int i = 0; i < data.size(); i++) {
            int size = 46;
            if (i == currentIndex && i== 0) {
                size = (int) (46 *1.25f);
            }

            if (i == currentIndex) {
                size = (int) (46 *value);
            }

            waitReadTextPaint.setTextSize(size);

            LyricItem lyricItem = data.get(i);
            Paint.FontMetrics fm = waitReadTextPaint.getFontMetrics();
            int x = (int) (width / 2 - waitReadTextPaint.measureText(lyricItem.text) / 2);
            int itemHeight = height / MAX_ROW;

            int y = (int) ((i+2) * itemHeight + itemHeight / 2 - (fm.descent + fm.ascent) / 2);



            canvas.drawText(lyricItem.text, x, y, waitReadTextPaint);
        }



    }

    float value;

    private void playAlreadyReadAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(1.25f,1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 value = (Float) animation.getAnimatedValue();
            }
        });
        animator.start();
    }

    private Paint getPaintWithStatus(int status) {
        Paint result = null;
        switch (status) {
            case LyricItem.STATUS_ALREADY_READ:
                result = alreadyReadTextPaint;
                break;
            case LyricItem.STATUS_CURRENT_READ:
                result = currentReadTextPaint;
                break;
            case LyricItem.STATUS_WAIT_READ:
                result = waitReadTextPaint;
                break;
        }

        return result;
    }

    private void drawText(Canvas canvas, Paint paint, int width, int height, int index, String text) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        int x = (int) (width / 2 - paint.measureText(text) / 2);
        int itemHeight = height / MAX_ROW;

        int y = (int) (index * itemHeight + itemHeight / 2 - (fm.descent + fm.ascent) / 2);

        canvas.drawText(text, x, y, paint);
    }


    /**
     * 通过invalidate操纵,此方法通过draw方法调用
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY()); //会重复调用invalidate
        }


    }


    /**
     * 设置背景
     */
    private void setBg() {
        int radius = 20;
        float[] radii = {
                radius, radius,
                radius, radius,
                radius, radius,
                radius, radius};
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(getColor(R.color.lyric_bg));
        drawable.setCornerRadii(radii);
        setBackground(drawable);
    }

    private int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }
}
