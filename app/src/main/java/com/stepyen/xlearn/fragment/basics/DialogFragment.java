package com.stepyen.xlearn.fragment.basics;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Dialog",extra = R.drawable.ic_widget_imageview)
public class DialogFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragmen_canvas;
    }

    @Override
    protected void initViews() {

    }
}