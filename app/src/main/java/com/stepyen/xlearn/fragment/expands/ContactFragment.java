package com.stepyen.xlearn.fragment.expands;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "联系人", extra = R.drawable.ic_widget_imageview)
public class ContactFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initViews() {

    }

}