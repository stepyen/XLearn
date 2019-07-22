package com.stepyen.xlearn.fragment.utils.arouter;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.base.BaseTestActivity;
import com.stepyen.xlearn.bean.UserBean;
import com.stepyen.xlearn.constant.ExtraKey;

/**
 * date：2019/4/25
 * author：stepyen
 * description：测试接收参数
 */

@Route(path = RouterHub.ARouterTestParaActivity)
public class ARouterTestParaActivity extends BaseTestActivity {

    @Autowired
    String data;

    @Autowired(name = ExtraKey.name)
    String mName;

    @Autowired(name = ExtraKey.obj)
    UserBean mUserBean;

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        addTextView("data："+data+"    "+"name；"+mName);
        addTextView("name："+mUserBean.getName()+"    "+"age；"+mUserBean.getAge());


        addView("设置返回值",v->{
            Intent intent = new Intent();
            intent.putExtra(ExtraKey.data, "data");
            setResult(ExtraKey.RESULT_DATA_CODE,intent );
            mActivity.finish();
        },100);
    }
}
