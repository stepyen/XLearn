package com.stepyen.xlearn.fragment.basics;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

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