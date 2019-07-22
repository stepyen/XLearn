package com.stepyen.xlearn.fragment.utils.launch;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.utils.life_cycle.ActivityLifeActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/11
 * author：stepyen
 * description：
 */
@Page(name = "页面启动",extra = R.drawable.ic_widget_imageview)
public class ActivityLaunchFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        addView("打开A",v->{
            startActivity( new Intent(getContext(), AActivity.class));
        });

    }

    @Override
    public void initLayoutView() {

    }
}

