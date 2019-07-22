package com.stepyen.xlearn.fragment.utils.arouter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.bean.UserBean;
import com.stepyen.xlearn.constant.ExtraKey;
import com.stepyen.xlearn.fragment.utils.life_cycle.ActivityLifeActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/11
 * author：stepyen
 * description： 路由框架学习
 */
@Page(name = "ARouter",extra = R.drawable.ic_widget_imageview)
public class ArouterFragment extends BaseTestFragment {

    public static final String TAG = "ArouterFragment";

    @Override
    protected void initViews() {
        addView("简单跳转", V->{
            ARouter.getInstance().build(RouterHub.ARouterTestSimpleActivity).navigation(getContext(), new NavCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    Log.e(TAG, "找到了");
                }

                @Override
                public void onLost(Postcard postcard) {
                    Log.e(TAG, "找不到了");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    Log.e(TAG, "跳转完了");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    Log.e(TAG, "被拦截了");
                }
            });
        });

        addView("携带参数,返回结果", V->{
            ARouter.getInstance()
                    .build(RouterHub.ARouterTestParaActivity)
                    .withString(ExtraKey.data, "数据123")
                    .withString(ExtraKey.name, "我的名字")
                    .withObject(ExtraKey.obj, new UserBean("xiaoming", 15))
                    .navigation(mActivity, ExtraKey.REQUEST_DATA_CODE);
        });
        addView("依赖注入解耦服务", V->{
            ARouter.getInstance().build(RouterHub.ARouterTestServiceActivity).navigation();
        });

        addView("解析URL并且跳转", V->{
            String data = "arouter://m.aliyun.com/main/ARouterSchameFilterActivity";
            ARouter.getInstance().build(Uri.parse(data)).navigation();
        });

        addView("页面需要登录", V->{
            ARouter.getInstance().build(RouterHub.ARouterTestNeedLoginActivity).navigation();
        });



    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ExtraKey.REQUEST_DATA_CODE:
                if (resultCode == ExtraKey.RESULT_DATA_CODE) {
                    if (data != null) {
                        Toast.makeText(mActivity, data.getStringExtra(ExtraKey.data), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}

