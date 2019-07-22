package com.stepyen.xlearn.fragment.utils.arouter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.base.BaseTestActivity;
import com.stepyen.xlearn.constant.Constant;

/**
 *
 *
 * 测试登录界面
 */
@Route(path = RouterHub.ARouterTestLoginctivity)
public class ARouterTestLoginActivity extends BaseTestActivity {

    @Autowired
    String data;

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        addTextView("当前为登陆页面");

        addView("点击登录", v -> {
            Constant.isLogin = true;
            // 登录成功后，跳转到此前需要去的页面
            ARouter.getInstance().build(data).navigation();
            mActivity.finish();
        },100);

    }
}
