package com.stepyen.xlearn.fragment.basics;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xutil.resource.ResUtils;
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

    @BindView(R.id.view_drawable_colorfilter)
    View mViewDrawableColorfilter;
    @BindView(R.id.view_drawable_tint)
    View mViewDrawableTint;
    @BindView(R.id.iv_drawable_1)
    ImageView mIvDrawable1;
    @BindView(R.id.iv_drawable_2)
    ImageView mIvDrawable2;


    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_drawable);
    }


    @Override
    protected void initViews() {
        tint();
        colorFilter();
        mutate();

    }


    /**
     * 着色的兼容api写法
     * @param drawable
     * @param colors
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


    private void tint() {
        mViewDrawableTint.setBackgroundColor(ResUtils.getColor(R.color.red));
        Drawable drawable = mViewDrawableTint.getBackground();
        DrawableCompat.setTint(drawable,ResUtils.getColor(R.color.blue));

//        ColorStateList colorStateList = ColorStateList.valueOf(ResUtils.getColor(R.color.blue));  //只有一个颜色时
//        DrawableCompat.setTintList(drawable, colorStateList);
    }

    private void colorFilter() {
        // 背景红色
        mViewDrawableColorfilter.setBackgroundColor(ResUtils.getColor(R.color.red));
        Drawable drawable = mViewDrawableColorfilter.getBackground();
        // 着色 蓝色
        int tint = Color.parseColor("blue");
        drawable.setColorFilter(tint, PorterDuff.Mode.SRC_IN);
    }

    /**
     * mutate，
     * <p>
     * 系统默认，系统资源共享同一个状态，当修改其中一个，其他的也会随之改变。
     * 要想单独修改一个，就要调用Drawable#mutate，该方法拷贝一份该资源的状态，进行单独的修改。
     */
    private void mutate() {
        Drawable aliPayDrawable = ResUtils.getDrawable(R.drawable.ic_alipay);
        mIvDrawable1.setImageDrawable(aliPayDrawable);
        Drawable mutateAliPayDrawable = ResUtils.getDrawable(R.drawable.ic_alipay).mutate();
        mutateAliPayDrawable.setColorFilter(ResUtils.getColor(R.color.pink), PorterDuff.Mode.SRC_ATOP);
        mIvDrawable2.setImageDrawable(mutateAliPayDrawable);
    }

    /**
     * 创建ColorStateList方式
     * 1、代码创建
     * 2、xml创建  color/xxx.xml
     * <p>
     * 有如下状态，具体的顺序是否按照下面的，未知。
     * <selector xmlns:android="http://schemas.android.com/apk/res/android" >
     * <item
     * android:color="hex_color"
     * android:state_pressed=["true" | "false"]
     * android:state_focused=["true" | "false"]
     * android:state_selected=["true" | "false"]
     * android:state_active=["true" | "false"]
     * android:state_checkable=["true" | "false"]
     * android:state_checked=["true" | "false"]
     * android:state_enabled=["true" | "false"]
     * android:state_window_focused=["true" | "false"] />
     * </selector>
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