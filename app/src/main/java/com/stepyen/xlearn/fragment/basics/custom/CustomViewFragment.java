package com.stepyen.xlearn.fragment.basics.custom;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.stepyen.xlearn.fragment.basics.ConstraintLayoutFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/24
 * author：stepyen
 * description：自定义View 学习
 */
@Page(name = "自定义View",extra = R.drawable.ic_widget_imageview)
public class CustomViewFragment extends ComponentContainerFragment {

    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                CustomStyleFragment.class,
                CustomViewLearnFragment.class
        };
    }
}