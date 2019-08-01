package com.stepyen.xlearn.fragment.basics;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.CompoundButton;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Canvas",extra = R.drawable.ic_widget_imageview)
public class CanvasFragment extends BaseTestFragment {
    @Override
    public void initLayoutView() {
        addView(R.layout.fragmen_canvas);
    }

    @Override
    protected void initViews() {

    }


}