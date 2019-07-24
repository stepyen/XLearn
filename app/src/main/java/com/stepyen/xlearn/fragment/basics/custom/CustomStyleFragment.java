package com.stepyen.xlearn.fragment.basics.custom;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/24
 * author：stepyen
 * description：自定义主题样式
 */
@Page(name = "自定义主题样式")
public class CustomStyleFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        CustomStyleView tv = new CustomStyleView(getContext(), null, R.attr.CustomStyleViewStyle);
        addView(tv);
    }

    @Override
    public void initLayoutView() {

    }
}