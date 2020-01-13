package com.stepyen.xlearn.fragment.basics.lyric;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.stepyen.xlearn.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

/**
 * date：2020-01-02
 * author：stepyen
 * description：歌词滚动View
 */
public class LyricView extends View {
    private static final String TAG = "LyricView";
    // 最大行数
    private final static int MAX_ROW = 4;

    // 文字比例
    private final static float TEXT_SIZE_RATIO = 1.17f;

    // 文字原始大小
    private final static int TEXT_SIZE = 46;

    private Context mContext;

    // 文字
    private Paint paint;

    // 数据
    private List<String> data = new ArrayList<>();

    // 当前读取的行数索引
    private int currentIndex = -1;

    // 缩放 从大到小 的值
    private float scaleSmallValue;

    // 缩放 从小到大 的值
    private float scaleLargeValue;

    // 移动 的值
    private float translateValue;

    // 颜色 当前阅读到已经阅读 的值
    private int fromCurrentToAlreadColorValue;

    // 颜色 未阅读到当前阅读 的值
    private int fromWaitToCurrentColorValue;

    int textSize = TEXT_SIZE;

    int textColor;

    // 记录之前 y 的滚动位置
    private float oldScrollY = 0;

    // 宽
    private int width ;

    // 高
    private int height;

    // 子项高
    private int itemHeight;


    public LyricView(Context context) {
        this(context, null);
    }

    public LyricView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LyricView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 设置歌词数据
     *
     * @param data
     */
    public void setData(List<String> data) {
        this.data = data;
        currentIndex = -1;
        invalidate();
    }

    /**
     * 添加歌词数据
     * @param data
     */
    public void addData(List<String> data) {
        if (this.data == null) {
            data = new ArrayList<>();
        }

        this.data.addAll(data);
        invalidate();
    }



    /**
     * 设置当前播放歌词下标
     *
     * @param index
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setCurrentIndex(int index) {
        currentIndex = index;

        // 当读第一句时，不执行动画。
        if (currentIndex > 0) {
            oldScrollY = getScrollY();
            playAnimation();
        }else{
            invalidate();
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 设置背景
        LyricViewBackgroundDrawable drawable = new LyricViewBackgroundDrawable(mContext,getWidth(),getHeight());
        setBackground(drawable);

        width = w;
        height = h;
        itemHeight = height / MAX_ROW;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (data == null) {
            return;
        }


        for (int i = 0,size = data.size(); i < size; i++) {

            // 配置 字体颜色和字体大小
            configTextColorAndTextSize(currentIndex,i);

            paint.setTextSize(textSize);
            paint.setColor(textColor);


            // i + 2  -->  2 表示距离顶部两个 itemHeight 的距离开始绘制
            drawText(canvas,paint,i +2, data.get(i));
        }
    }


    /**
     * 配置下 文字颜色 和 文字大小
     * @param currentIndex
     * @param i
     */
    private void configTextColorAndTextSize(int currentIndex, int i) {

        // 重置数据和第0句
        if (currentIndex == -1 || currentIndex ==0) {
            if (i == 0) {

                textSize = (int) (TEXT_SIZE * TEXT_SIZE_RATIO);
                textColor = getColor(R.color.lyric_current_read_text);

            }else{

                textSize = TEXT_SIZE;
                textColor = getColor(R.color.lyric_wait_read_text);

            }
        } else if (currentIndex>0) {

            // 前面已经读过的
            if (currentIndex-1> i) {

                textSize = TEXT_SIZE;
                textColor = getColor(R.color.lyric_already_read_text);

                //  刚读的，要从大变小
            } else if (currentIndex -1 == i) {

                textSize = (int) (TEXT_SIZE * scaleSmallValue);
                textColor = fromCurrentToAlreadColorValue;

                // 当前准备读的，  要从小变大
            } else if (currentIndex == i) {

                textSize = (int) (TEXT_SIZE * scaleLargeValue);
                textColor = fromWaitToCurrentColorValue;

                // 未读的
            } else if (i>currentIndex) {

                textSize = TEXT_SIZE;
                textColor = getColor(R.color.lyric_wait_read_text);

            }
        }
    }


    /**
     * 绘制文本
     * @param canvas
     * @param paint
     * @param offsetIndex
     * @param text
     */
    private void drawText(Canvas canvas, Paint paint, int offsetIndex, String text) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        int x = (int) (width / 2 - paint.measureText(text) / 2);

        // 文本在 item 居中
        int y = (int) (offsetIndex * itemHeight + itemHeight / 2 - (fm.descent + fm.ascent) / 2);

        canvas.drawText(text, x, y, paint);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void playAnimation() {
        ValueAnimator scaleSmallAnimator = ValueAnimator.ofFloat(TEXT_SIZE_RATIO, 1f);
        scaleSmallAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleSmallValue = (Float) animation.getAnimatedValue();
            }
        });
        ValueAnimator scaleLargeAnimator = ValueAnimator.ofFloat(1, TEXT_SIZE_RATIO);
        scaleLargeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleLargeValue = (Float) animation.getAnimatedValue();
            }
        });


        ValueAnimator translateAnimator = ValueAnimator.ofFloat(0, itemHeight);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateValue = (Float) animation.getAnimatedValue();
                scrollTo(0, (int) (oldScrollY+translateValue));
            }
        });
        ValueAnimator fromCurrentToAlreadColorAnimator = ObjectAnimator.ofArgb(getColor(R.color.lyric_current_read_text), getColor(R.color.lyric_already_read_text));
        fromCurrentToAlreadColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fromCurrentToAlreadColorValue = (int) animation.getAnimatedValue();
            }
        });
        ValueAnimator fromWaitToCurrentReadColorAnimator = ObjectAnimator.ofArgb(getColor(R.color.lyric_wait_read_text), getColor(R.color.lyric_current_read_text));
        fromWaitToCurrentReadColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fromWaitToCurrentColorValue = (int) animation.getAnimatedValue();
            }
        });


        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(scaleSmallAnimator,
                scaleLargeAnimator,
                translateAnimator,
                fromCurrentToAlreadColorAnimator,
                fromWaitToCurrentReadColorAnimator);

        set.start();
    }

    private int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }
}
