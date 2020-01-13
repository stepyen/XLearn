package com.stepyen.xlearn.fragment.basics.UI;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.stepyen.xlearn.fragment.basics.UI.banner.BannerFragment;
import com.stepyen.xlearn.fragment.basics.animation.PropertyAnimotionFragment;
import com.stepyen.xlearn.fragment.basics.animation.ShowAnimotionFragment;
import com.stepyen.xlearn.fragment.basics.animation.TweenAnimotionFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/19
 * author：stepyen
 * description：
 */
@Page(name = "UI", extra = R.drawable.ic_widget_imageview)
public class UIFragment extends ComponentContainerFragment {
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                BannerFragment.class
        };
    }
}
