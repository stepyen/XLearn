package com.stepyen.xlearn.fragment.utils;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.UMengActivity;
import com.stepyen.xlearn.fragment.utils.AgentWeb.AgentWebActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/11
 * author：stepyen
 * description：
 */
@Page(name = "AgentWeb",extra = R.drawable.ic_widget_imageview)
public class AgentWebFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        addView("打开网页",v->{

            String url = "https://blog.csdn.net/worst_hacker/article/details/80019040";
            Intent intent = new Intent(getContext(), AgentWebActivity.class);
            intent.putExtra(com.stepyen.xlearn.fragment.utils.AgentWeb.AgentWebFragment.KEY_URL, url);
            startActivity(intent);
        });

    }

    @Override
    public void initLayoutView() {

    }
}

