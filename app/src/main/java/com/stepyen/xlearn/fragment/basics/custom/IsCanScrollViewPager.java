package com.stepyen.xlearn.fragment.basics.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 创建时间：2018/5/11
 * 作者：yfj
 * 描述：控制viewpager能不能滑动
 */

public class IsCanScrollViewPager extends ViewPager {

    private boolean isCanScroll;

    public IsCanScrollViewPager(Context context) {
        super(context);
    }

    public IsCanScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }

    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

}
