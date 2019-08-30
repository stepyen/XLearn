package com.stepyen.xlearn.fragment.basics.eventDispatch.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class RootView extends RelativeLayout {
    private static final String TAG = Static.TAG2;

    public RootView(Context context) {
        super(context);
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // Log.i(TAG, Static.dispatchTouchEvent + "呼叫技术部,老板要做淘宝,下周上线.");
            //Log.i(TAG, Static.dispatchTouchEvent + "技术部,老板说按钮不好看,要加一道光.");
            Log.i(TAG, Static.dispatchTouchEvent + "技术部,你们的app快做完了么?");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onInterceptTouchEvent + "(老板可能疯了,但又不是我做.)");
            Log.i(TAG, Static.onInterceptTouchEvent );
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onTouchEvent+"报告老板, 技术部说做不了");
            Log.i(TAG, Static.onTouchEvent );
        }
        return super.onTouchEvent(event);
    }
}
