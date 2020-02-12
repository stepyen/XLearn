package com.stepyen.xlearn.fragment.basics.custom.lyric;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.stepyen.xlearn.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * date：2020-01-03
 * author：stepyen
 * description：
 */
public class LyricViewBackgroundDrawable extends Drawable {

    private Paint paint;
    private Paint currentReadBgPaint;
    private Context mContext;
    private int width;
    private int height;
    public LyricViewBackgroundDrawable(Context context,int width,int height) {
        this.mContext = context;
        this.width = width;
        this.height = height;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getColor(R.color.lyric_bg));

        currentReadBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        currentReadBgPaint.setColor(getColor(R.color.lyric_current_read_bg));

    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        canvas.save();
        // 绘制圆角
        RectF receF = new RectF(0f, 0f, width, height);
        canvas.drawRoundRect(receF, 20f, 20f, paint);

        // 绘制当前阅读行的背景
        Rect rect = new Rect(0, height / 4 * 2, width, height / 4 * 3);
        canvas.drawRect(rect, currentReadBgPaint);

        canvas.restore();


    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    private int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }
}
