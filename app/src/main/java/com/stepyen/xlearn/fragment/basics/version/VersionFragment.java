package com.stepyen.xlearn.fragment.basics.version;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019-12-18
 * author：stepyen
 * description：
 */

@Page(name = "Android 版本", extra = R.drawable.ic_widget_imageview)
public class VersionFragment extends BaseTestFragment {


    @Override
    public void initLayoutView() {
        addView("6.0权限", v->{
            startActivity(new Intent(getContext(), PermissionActivity.class));
        });
    }

    @Override
    protected void initViews() {

    }
}
