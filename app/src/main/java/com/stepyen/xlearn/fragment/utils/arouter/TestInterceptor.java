package com.stepyen.xlearn.fragment.utils.arouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.stepyen.xlearn.constant.Constant;
import com.stepyen.xlearn.constant.ExtraKey;


/**
 * 测试拦截
 */
@Interceptor(priority = 5, name = "测试拦截器")
public class TestInterceptor implements IInterceptor {
    private static final String TAG = "TestInterceptor";
    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.d(TAG, "process: ");
        switch (postcard.getExtra()) {
            case PageExtras.login:
                if (Constant.isLogin) {
                    callback.onContinue(postcard);
                } else {

                    ARouter.getInstance()
                            .build(RouterHub.ARouterTestLoginctivity)
                            .withString(ExtraKey.data, postcard.getPath())
                            .navigation();
                    callback.onInterrupt(null);

                    // 这样是不行的，不能跳转    不知道为什么
//                    postcard.withString(ExtraKey.data, postcard.getPath());
//                    postcard.setPath(RouterHub.ARouterTestLoginctivity);
//                    postcard.navigation();
//                    callback.onInterrupt(null);

                    // 没有拦截，直接就到目标页面了
//                    postcard.withString(ExtraKey.data, postcard.getPath());
//                    postcard.setPath(RouterHub.ARouterTestLoginctivity);
//                    callback.onContinue(postcard);

//                    直接连跳转都没有
//                    postcard.withString(ExtraKey.data, postcard.getPath());
//                    postcard.setPath(RouterHub.ARouterTestLoginctivity);
//                    postcard.navigation();
//                    callback.onInterrupt(null);

                }
                break;
            default:
                callback.onContinue(postcard);
                break;
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
