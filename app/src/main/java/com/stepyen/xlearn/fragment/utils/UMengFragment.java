package com.stepyen.xlearn.fragment.utils;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.UMengActivity;
import com.stepyen.xlearn.fragment.utils.life_cycle.ActivityLifeActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/11
 * author：stepyen
 * description：
 */
@Page(name = "友盟",extra = R.drawable.ic_widget_imageview)
public class UMengFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        addView("友盟",v->{
            startActivity( new Intent(getContext(), UMengActivity.class));
        });

    }

    @Override
    public void initLayoutView() {

    }
}

