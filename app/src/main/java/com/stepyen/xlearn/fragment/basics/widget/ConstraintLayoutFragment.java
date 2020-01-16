package com.stepyen.xlearn.fragment.basics.widget;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/17
 * author：stepyen
 * description：
 */
@Page(name = "ConstraintLayout")
public class ConstraintLayoutFragment extends BaseTestFragment {

    @Override
    protected void initViews() {

    }

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_constraintlayout);
    }
}