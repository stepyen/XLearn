package com.stepyen.xlearn.fragment.basics;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.fragment.basics.service.TestBindService;
import com.stepyen.xlearn.fragment.basics.service.TestStartService;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "服务", extra = R.drawable.ic_widget_imageview)
public class ServiceFragment extends BaseFragment {
    private static final String TAG = "ServiceFragment";

    private ServiceConnection mServiceConnection;
    private TestBindService mTestBindService;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btn_start_service) void btn_start_service() {
        Intent intent = new Intent(getContext(),TestStartService.class);
        getActivity().startService(intent);
    }
    @OnClick(R.id.btn_stop_service) void btn_stop_service() {
        Intent intent = new Intent(getContext(),TestStartService.class);
        getActivity().stopService(intent);
    }
    @OnClick(R.id.btn_bind_service) void btn_bind_service() {
        if (mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.d(TAG, "onServiceConnected:");
                    TestBindService.MyBind binder = (TestBindService.MyBind) iBinder;
                    mTestBindService = binder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    mTestBindService = null;
                    Log.d(TAG, "onServiceDisconnected:");
                }
            };
        }

        Intent intent = new Intent(getContext(), TestBindService.class);
        getActivity().bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);

    }
    @OnClick(R.id.btn_unbind_service) void btn_unbind_service() {
        getActivity().unbindService(mServiceConnection);
    }




}