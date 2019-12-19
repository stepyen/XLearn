package com.stepyen.xlearn.fragment.basics.process_communication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.webkit.WebView;

import com.stepyen.xutil.tip.ToastUtils;

import androidx.annotation.Nullable;

/**
 * date：2019-12-19
 * author：stepyen
 * description：Messenger 服务端 服务
 */
public class MessengerService extends Service {
    private static final String TAG = "MessengerTag";
    private final static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ToastUtils.toast("我是服务端，我接收到数据啦");
            Log.i("MessengerTag", "handleMessage: 我是服务端，我接收到数据啦");

            Messenger messenger = msg.replyTo;
            Message message = Message.obtain();
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private final static Messenger messenger = new Messenger(mHandler);


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}
