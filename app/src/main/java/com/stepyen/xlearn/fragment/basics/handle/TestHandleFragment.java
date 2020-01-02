package com.stepyen.xlearn.fragment.basics.handle;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.TestActivity;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;

/**
 * date：2019-11-25
 * author：stepyen
 * description：
 */

@Page(name = "Handle", extra = R.drawable.ic_widget_imageview)
public class TestHandleFragment extends BaseFragment {
    private static final String TAG = "TestHandleFragment";

    @BindView(R.id.btn_handle_thread_send)
    Button btn_handle_thread_send;
    private Handler mHandler;
    private int i = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_handle;
    }

    @Override
    protected void initViews() {
        HandlerThread handlerThread = new HandlerThread("test");
        handlerThread.start();


        mHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handleMessage: "+msg.obj.toString());
            }
        };

        btn_handle_thread_send.setOnClickListener(v->{
            Message message = mHandler.obtainMessage();
            i++;
            message.obj = i;
            mHandler.sendMessage(message);
        });





    }




}