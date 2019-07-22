package com.stepyen.xlearn.fragment.utils.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.base.BaseTestActivity;

/**
 * date：2019/4/27
 * author：stepyen
 * description：ARouter 协议解析页面
 */
@Route(path = RouterHub.ARouterSchameFilterActivity)
public class ARouterSchameFilterActivity extends BaseTestActivity {

//    @Autowired
//    String data;

    @Override
    public void initView() {
//        ARouter.getInstance().inject(this);

        String uriStr = getIntent().getStringExtra(ARouter.RAW_URI);
        addTextView(uriStr);

//        ARouter.getInstance().build(uriStr).navigation();

//        finish();
    }
}
