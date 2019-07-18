package com.stepyen.xlearn.fragment.basics;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.StrikethroughSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */

@Page(name = "TextView",extra = R.drawable.ic_widget_imageview)
public class TextViewFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_textview;
    }

    @Override
    protected void initViews() {

    }
}
