package com.stepyen.xlearn.fragment.basics.UI.banner;

import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2020-01-07
 * author：stepyen
 * description：
 */
@Page(name = "轮播图")
public class BannerFragment extends BaseTestFragment {

    ImageBanner mImageBanner;
    @Override
    public void initLayoutView() {
        View view = addView(R.layout.fragment_banner);
        mImageBanner = view.findViewById(R.id.imageBanner);
        List<Integer> data = Arrays.asList(R.drawable.ic_character_banner_1, R.drawable.ic_character_banner_2, R.drawable.ic_character_banner_3);
        mImageBanner.setData(data);
        mImageBanner.startScroll();
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