package com.stepyen.xlearn.fragment.basics;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.stepyen.xlearn.fragment.basics.animation.FrameAnimotionFragment;
import com.stepyen.xlearn.fragment.basics.animation.PropertyAnimotionFragment;
import com.stepyen.xlearn.fragment.basics.animation.TweenAnimotionFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/19
 * author：stepyen
 * description：
 */
@Page(name = "动画", extra = R.drawable.ic_widget_imageview)
public class AimationFragment extends ComponentContainerFragment {
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                FrameAnimotionFragment.class,
                TweenAnimotionFragment.class,
                PropertyAnimotionFragment.class
        };
    }
}
