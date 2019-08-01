package com.stepyen.xlearn.fragment.basics;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import androidx.core.graphics.drawable.DrawableCompat;
import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：Drawable 学习
 */
@Page(name = "Drawable", extra = R.drawable.ic_widget_imageview)
public class DrawableFragment extends BaseTestFragment {

    @BindView(R.id.tv_drawable_hint)
    TextView mTvDrawableHint;

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_drawable);
    }


    @Override
    protected void initViews() {
        int tint = Color.parseColor("blue");
        Drawable background = mTvDrawableHint.getBackground();

    }


    /**
     * 创建ColorStateList方式
     * 1、代码创建
     * 2、xml创建  color/xxx.xml
     *
     * 有如下状态，具体的顺序是否按照下面的，未知。
     *   <selector xmlns:android="http://schemas.android.com/apk/res/android" >
     *        <item
     *            android:color="hex_color"
     *            android:state_pressed=["true" | "false"]
     *            android:state_focused=["true" | "false"]
     *            android:state_selected=["true" | "false"]
     *            android:state_active=["true" | "false"]
     *            android:state_checkable=["true" | "false"]
     *            android:state_checked=["true" | "false"]
     *            android:state_enabled=["true" | "false"]
     *            android:state_window_focused=["true" | "false"] />
     *    </selector>
     *
     *
     *
     *
     */
    private void createColorStateList() {
        //        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[] colors = new int[]{
                android.R.color.background_dark,
                android.R.color.background_dark
                , android.R.color.background_dark,
                android.R.color.background_dark,
                android.R.color.background_dark
                , android.R.color.background_dark};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        ColorStateList colorStateList = new ColorStateList(states, colors);
    }

}