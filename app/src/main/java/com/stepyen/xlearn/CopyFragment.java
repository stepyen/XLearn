package com.stepyen.xlearn;

import android.net.Uri;
import android.widget.TextView;

import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Copy", extra = R.drawable.ic_widget_imageview)
public class CopyFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_copy;
    }

    @Override
    protected void initViews() {

    }

}