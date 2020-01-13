package com.stepyen.xlearn.fragment.basics.network;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */
@Page(name = "网络请求", extra = R.drawable.ic_widget_imageview)
public class NetWorkFragment extends ComponentContainerFragment {
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                OkhttpFragment.class,
                RetrofitFragment.class
        };
    }
}
