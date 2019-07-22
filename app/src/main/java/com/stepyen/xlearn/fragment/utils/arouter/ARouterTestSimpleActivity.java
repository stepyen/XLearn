package com.stepyen.xlearn.fragment.utils.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.stepyen.xlearn.base.BaseTestActivity;

/**
 * date：2019/4/27
 * author：stepyen
 * description：简单的界面
 */
@Route(path = RouterHub.ARouterTestSimpleActivity)
public class ARouterTestSimpleActivity extends BaseTestActivity {
    @Override
    public void initView() {
        addTextView("ARouterTestSimpleActivity页面");
    }
}
