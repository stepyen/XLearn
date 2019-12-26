package com.stepyen.xlearn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * date：2019-12-25
 * author：stepyen
 * description：
 */
public class XConstraintLayout extends ConstraintLayout {

    public XConstraintLayout(Context context) {
        this(context, null);
    }

    public XConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
