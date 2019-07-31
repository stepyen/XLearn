package com.stepyen.xlearn.fragment.basics;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.basics.activity.AliasActivity;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Activity", extra = R.drawable.ic_widget_imageview)
public class ActivityFragment extends BaseTestFragment {

    @Override
    public void initLayoutView() {

        /**
         * 使用场景
         * 1、app更换入口页面时，升级引发奔溃，因为手机的入口信息还是之前那个。
         * 2、桌面添加快捷入口
         * 3、微信登陆封装实现逻辑，在配置清单中，指定别名。
         */
        addView("activity别名使用",v -> {
            startActivity(new Intent(getContext(), AliasActivity.class));
        });


    }

    @Override
    protected void initViews() {

    }



}