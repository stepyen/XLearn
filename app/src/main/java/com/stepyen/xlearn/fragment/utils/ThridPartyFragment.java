package com.stepyen.xlearn.fragment.utils;

import android.content.Intent;

import com.stepyen.chivox.ChivoxActivity;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.activity.UMengActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/11
 * author：stepyen
 * description：
 */
@Page(name = "第三方",extra = R.drawable.ic_widget_imageview)
public class ThridPartyFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        addView("友盟",v->{
            startActivity( new Intent(getContext(), UMengActivity.class));
        });

        addView("驰声",v->{
            startActivity( new Intent(getContext(), ChivoxActivity.class));
        });

    }

    @Override
    public void initLayoutView() {

    }
}

