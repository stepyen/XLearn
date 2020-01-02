package com.stepyen.xlearn.fragment.basics.lyric;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

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

    private void setupDataWithIndex(int currentIndex) {
        LyricItem currentlyricItem = data.get(currentIndex);
        currentlyricItem.status = LyricItem.STATUS_CURRENT_READ;

        if (currentIndex == 0 || currentIndex ==1) {
            currentShowIndex = currentIndex;
        } else if (data.size()>=4 && currentIndex == data.size() -1) {
            currentShowIndex = 3;
        }else{
            currentShowIndex = 2;
        }

        if (currentIndex == 0) {
            return;
        }

        int frontIndex = currentIndex - 1;
        LyricItem frontlyricItem = data.get(frontIndex );
        frontlyricItem.status = LyricItem.STATUS_ALREADY_READ;

    }


    /**
     * 设置当前播放歌词下标
     *
     * @param index
     */
    public void setCurrentIndex(int index) {
        currentIndex = index;
        setupDataWithIndex(currentIndex);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data == null) {
            return;
        }
        int height = getHeight();
        int width = getWidth();

//        int i = 0;
//        int size = data.size();
//        if (size>4 && currentIndex>=3) {
//
//            int tempMaxI = size - 4 ;   // 临时最大的 i  6
//
//            int tempI = size - 1 - currentIndex;    // 临时 i 9
//
//            if (tempMaxI>=tempI) {
//                i = tempI;
//            }
//
//        }
//
//
//        for (int itemIndex = 0; i <size; i++, itemIndex++) {
//            LyricItem lyricItem = data.get(i);
//            if (lyricItem.status == LyricItem.STATUS_CURRENT_READ) {
//                Rect rect = new Rect(0, height / 4 * currentShowIndex, width, height / 4 * (currentShowIndex+1));
//                canvas.drawRect(rect, currentReadBgPaint);
//            }
//
//
//            drawText(canvas, getPaintWithStatus(lyricItem.status), width, height, itemIndex, lyricItem.text);
//        }


        for (int i = 0; i < data.size(); i++) {
            LyricItem lyricItem = data.get(i);
            Paint.FontMetrics fm = waitReadTextPaint.getFontMetrics();
            int x = (int) (width / 2 - waitReadTextPaint.measureText(lyricItem.text) / 2);
            int itemHeight = height / MAX_ROW;

            int y = (int) (i * itemHeight + itemHeight / 2 - (fm.descent + fm.ascent) / 2);

            canvas.drawText(lyricItem.text, x, y, waitReadTextPaint);
        }

//        setScrollY(getScrollY()+ height / MAX_ROW);
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
