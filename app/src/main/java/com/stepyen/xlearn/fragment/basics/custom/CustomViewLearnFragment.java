package com.stepyen.xlearn.fragment.basics.custom;

import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name ="自定义View学习")
public class CustomViewLearnFragment extends BaseTestFragment {
    @Override
    public void initLayoutView() {
        View view = addView(R.layout.fragmen_custom_view_learn);
//        MagicCircleView magicCircle = view.findViewById(R.id.circle_view);
//        view.findViewById(R.id.btn_click).setOnClickListener(v->{
//            magicCircle.startAnimation();
//        });
    }

    @Override
    protected void initViews() {

    }


}