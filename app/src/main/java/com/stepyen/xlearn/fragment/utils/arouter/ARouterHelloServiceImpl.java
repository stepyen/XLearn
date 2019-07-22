package com.stepyen.xlearn.fragment.utils.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * date：2019/4/27
 * author：stepyen
 * description：
 */
@Route(path = RouterHub.ARouterHelloServiceImpl)
public class ARouterHelloServiceImpl implements ARouterHelloService {


    @Override
    public String sayHello() {
        return "sayHello: ARouterHelloServiceImpl";
    }

    @Override
    public void init(Context context) {

    }
}
