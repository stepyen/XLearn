package com.stepyen.xlearn.fragment.utils;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.utils.life_cycle.ActivityLifeActivity;
import com.stepyen.xutil.app.AppUtils;
import com.xuexiang.xpage.annotation.Page;

import androidx.fragment.app.Fragment;

/**
 * date：2019/7/11
 * author：stepyen
 * description：
 */
@Page(name = "Fragment生命周期",extra = R.drawable.ic_widget_imageview)
public class LifeCycleFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        addView("Activity生命周期",v->{
            startActivity( new Intent(getContext(), ActivityLifeActivity.class));
        });

        addView("Fragment生命周期",v->{

        });
    }

    @Override
    public void initLayoutView() {

    }
}

