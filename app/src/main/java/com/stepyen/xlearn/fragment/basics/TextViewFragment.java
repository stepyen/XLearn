package com.stepyen.xlearn.fragment.basics;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import androidx.core.content.ContextCompat;
import butterknife.BindView;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */

@Page(name = "TextView", extra = R.drawable.ic_widget_imageview)
public class TextViewFragment extends BaseFragment {

    @BindView(R.id.tv_spannablestring_click)
    TextView mTvSpannablestringClick;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_textview;
    }

    @Override
    protected void initViews() {
        testClickableSpan();
    }

    private void testClickableSpan() {
        SpannableString ss = new SpannableString("请阅读协议");
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                ToastUtils.toast("点击协议");
            }
            @Override
            public void updateDrawState( TextPaint ds) {
                ds.setColor(ContextCompat.getColor(mActivity, R.color.red));    // 设置字体颜色
                ds.setUnderlineText(false);                                      // 设置下划线
            }
        }, 3, 5, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);

        // 设置字体颜色 这里设置的
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.blue)), 3,5,SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        // 设置可点击
        mTvSpannablestringClick.setMovementMethod(LinkMovementMethod.getInstance());
        // 点击后的高亮颜色
        mTvSpannablestringClick.setHighlightColor(ContextCompat.getColor(getContext(), R.color.transparent));

        mTvSpannablestringClick.setText(ss);
    }
}
