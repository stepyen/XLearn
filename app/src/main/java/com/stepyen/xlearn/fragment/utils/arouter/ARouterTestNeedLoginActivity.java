package com.stepyen.xlearn.fragment.utils.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.stepyen.xlearn.base.BaseTestActivity;
import com.stepyen.xlearn.constant.Constant;

/**
 * date：2019/4/25
 * author：stepyen
 * description：ARouter 测试需要登录页面
 */

@Route(path = RouterHub.ARouterTestNeedLoginActivity, extras = PageExtras.login)
public class ARouterTestNeedLoginActivity extends BaseTestActivity {

    @Override
    public void initView() {
        addTextView("页面需要登录，当前显示为登录成功");


        addView("退出登录", v -> {
            Constant.isLogin = false;
            mActivity.finish();
        },100);
    }
}
