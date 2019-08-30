
package com.stepyen.xlearn.fragment.basics.eventDispatch.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class View1 extends View {
    private static final String TAG = Static.TAG4;

    public View1(Context context) {
        super(context);
    }

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.dispatchTouchEvent+"做淘宝???");
            Log.i(TAG, Static.dispatchTouchEvent+"加一道光.");
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onTouchEvent+"这个真心做不了啊");
            Log.i(TAG, Static.onTouchEvent+"做好了.");
        }
        return true;
    }
}
