package com.stepyen.xlearn.fragment.basics.process_communication;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.stepyen.testdemo.aidl.RemoteInterface;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：进程间通信 学习
 * <p>
 * 方式：
 */
@Page(name = "进程间通信", extra = R.drawable.ic_widget_imageview)
public class ProcessCommunicationFragment extends BaseFragment {
    private static final String TAG = "ProcessCommunicationFragment";
    private RemoteInterface aidlService;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_process_comminication;
    }

    @Override
    protected void initViews() {

        findViewById(R.id.btn_aidl_bind_service).setOnClickListener(v -> {

            Intent intent = new Intent("com.stepyen.testdemo.aidl.action"); // 服务端 service 的 action
            intent.setPackage("com.stepyen.testdemo");  // 服务端包名
            getActivity().bindService(intent, new AIDLConnection(), Service.BIND_AUTO_CREATE);

        });
        findViewById(R.id.btn_Messenger_bind_service).setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), MessengerService.class); // 服务端 service 的 action
            getActivity().bindService(intent, new MessengerConnection(), Service.BIND_AUTO_CREATE);

        });
    }


    private class AIDLConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            aidlService = RemoteInterface.Stub.asInterface(service);// 获得服务端的 RemoteInterface 的对象。
            try {
                String str = aidlService.getString();// 调用服务端暴露的方法
                ToastUtils.toast(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


    private final static Handler mReplyHandler = new Handler() {
        @SuppressLint("LongLogTag")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ToastUtils.toast("我是客户端，我接收到数据啦");
            Log.i("MessengerTag", "handleMessage: 我是客户端，我接收到数据啦");

        }

        ;
    };

    private final static Messenger mReplyMessenger = new Messenger(mReplyHandler);


    private class MessengerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain();
            message.replyTo = mReplyMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}