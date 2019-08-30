package com.stepyen.xlearn.fragment.expands.NotificationListen;

import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "通知栏监听", extra = R.drawable.ic_widget_imageview)
public class NotificationListenFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification_listen;
    }

    @Override
    protected void initViews() {

    }
    @OnClick(R.id.tv_send_notify)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_notify:

                break;
        }

    }

    private void tv_send_notify() {

    }

}