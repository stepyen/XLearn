package com.stepyen.xlearn.fragment.utils.arouter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.base.BaseTestActivity;

/**
 * date：2019/4/27
 * author：stepyen
 * description：测试服务
 */
@Route(path = RouterHub.ARouterTestServiceActivity)
public class ARouterTestServiceActivity extends BaseTestActivity {
    @Autowired(name = RouterHub.ARouterHelloServiceImpl)
    ARouterHelloService mARouterHelloService1;
    @Autowired(name = RouterHub.ARouterHelloServiceImpl2)
    ARouterHelloService mARouterHelloService2;

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        addTextView("服务的使用");
        addTextView( mARouterHelloService1.sayHello());
        addTextView( mARouterHelloService2.sayHello());

    }
}
