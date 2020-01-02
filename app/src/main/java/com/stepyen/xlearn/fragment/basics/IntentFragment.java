package com.stepyen.xlearn.fragment.basics;

import android.content.Intent;
import android.widget.Button;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.TestActivity;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Intent", extra = R.drawable.ic_widget_imageview)
public class IntentFragment extends BaseFragment {


    @BindView(R.id.btn_test)
    Button mBtnTest;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intent;
    }

    @Override
    protected void initViews() {
        mBtnTest.setOnClickListener(v->{
            startActivity(new Intent(getContext(), TestActivity.class));
        });
    }


}