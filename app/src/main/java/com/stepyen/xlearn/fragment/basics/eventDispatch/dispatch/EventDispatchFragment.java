package com.stepyen.xlearn.fragment.basics.eventDispatch.dispatch;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "事件分发", extra = R.drawable.ic_widget_imageview)
public class EventDispatchFragment extends BaseTestFragment {

    @Override
    public void initLayoutView() {


        addView("事件分发",v -> {
            startActivity(new Intent(getContext(), EventDispatchActivity.class));
        });


    }

    @Override
    protected void initViews() {

    }



}