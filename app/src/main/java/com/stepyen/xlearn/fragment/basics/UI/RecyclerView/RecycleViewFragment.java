package com.stepyen.xlearn.fragment.basics.UI.RecyclerView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/24
 * author：stepyen
 * description：
 */
@Page(name = "RecycleView",extra = R.drawable.ic_widget_imageview)
public class RecycleViewFragment extends ComponentContainerFragment {

    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                RecycleViewOptionFragment.class
        };
    }
}