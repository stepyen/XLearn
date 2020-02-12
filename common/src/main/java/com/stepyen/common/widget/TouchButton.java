package com.stepyen.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.stepyen.common.R;

import androidx.appcompat.widget.AppCompatButton;

/**
 * date：2019-12-03
 * author：stepyen
 * description：
 */
public class TouchButton extends AppCompatButton {

    public TouchButton(Context context) {
        this(context, null);
    }

    public TouchButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mOnEvaluationTouchListen != null) {
                    mOnEvaluationTouchListen.down(getId());
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mOnEvaluationTouchListen != null) {
                    mOnEvaluationTouchListen.up();
                }
                break;

        }

        return true;

    }

    public interface OnEvaluationTouchListen {
        void down(int id);

        void up();
    }

    private OnEvaluationTouchListen mOnEvaluationTouchListen;


    public void setOnEvaluationTouchListen(OnEvaluationTouchListen onEvaluationTouchListen) {
        this.mOnEvaluationTouchListen = onEvaluationTouchListen;
    }

}
