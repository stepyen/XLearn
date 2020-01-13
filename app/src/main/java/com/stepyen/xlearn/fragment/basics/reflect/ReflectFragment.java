package com.stepyen.xlearn.fragment.basics.reflect;

import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.OnClick;

/**
 * date：2020-01-08
 * author：stepyen
 * description：
 */

@Page(name = "反射", extra = R.drawable.ic_widget_imageview)
public class ReflectFragment extends BaseTestFragment {

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_frame_animotion);
    }

    @Override
    protected void initViews() {

    }


//    @OnClick({R.id.btn_switch})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_switch:
//
//                break;
//        }
//    }
}
