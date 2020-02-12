package com.stepyen.xlearn.fragment.basics.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * 创建时间：2018/6/4
 * 作者：yfj
 * 描述：
 */

public class LearnView extends FrameLayout {
    private static final String TAG = "LearnView";

    public LearnView(Context context) {
        this(context, null);
    }

    public LearnView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LearnView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "getlocation: getLeft " + getLeft());
        Log.d(TAG, "getlocation: getTranslationX " + getTranslationX());
        Log.d(TAG, "getlocation: getX " + getX());
    }


    public void getlocation() {
        Log.d(TAG, "getlocation: getLeft " + getLeft());
        Log.d(TAG, "getlocation: getTranslationX " + getTranslationX());
        Log.d(TAG, "getlocation: getX " + getX());
    }

    public void setTranslationX() {
        setTranslationX(100);

        Log.d(TAG, "getlocation: getLeft " + getLeft());
        Log.d(TAG, "getlocation: getTranslationX " + getTranslationX());
        Log.d(TAG, "getlocation: getX " + getX());
    }

    public void scrollto() {
        scrollTo(20,30);

        Log.d(TAG, "getlocation: getLeft " + getLeft());
        Log.d(TAG, "getlocation: getTranslationX " + getTranslationX());
        Log.d(TAG, "getlocation: getX " + getX());
    }



}
