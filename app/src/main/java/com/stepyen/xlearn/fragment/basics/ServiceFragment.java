package com.stepyen.xlearn.fragment.basics;

import android.net.Uri;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "服务", extra = R.drawable.ic_widget_imageview)
public class ServiceFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initViews() {

    }



}