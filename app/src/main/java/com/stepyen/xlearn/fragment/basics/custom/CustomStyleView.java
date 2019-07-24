package com.stepyen.xlearn.fragment.basics.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xlearn.R;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * date：2019/7/24
 * author：stepyen
 * description：
 *
 * https://www.jianshu.com/p/61b79e7f88fc 深入理解Android 自定义attr Style styleable以及其应用
 */
public class CustomStyleView extends AppCompatTextView {
    private static final String TAG = "CustomStyleView";

    /**
     * 代码中动态添加，调用此方法
     * @param context
     */
    public CustomStyleView(Context context) {
         this(context, null);
         Log.i(TAG, "CustomStyleView: (Context context)");
    }

    /**
     * 布局中添加，调用此方法
     * @param context
     * @param attrs
     */
    public CustomStyleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        Log.i(TAG, "CustomStyleView: (Context context, AttributeSet attrs)");
    }

    public CustomStyleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i(TAG, "CustomStyleView: (Context context, AttributeSet attrs, int defStyle)");

        /**
         *   AttributeSet set,              xml中设置的属性
         *   @StyleableRes int[] attrs,     自定义的属性集
         *   @AttrRes int defStyleAttr,     style中设置的属性，自定义一个参数，并指定对应的style
         *   @StyleRes int defStyleRes      默认设置的属性集
         *
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomStyleView, defStyle, 0);
        String title = ta.getString(R.styleable.CustomStyleView_csv_title);
        ta.recycle();
        setText(title);
    }
}
