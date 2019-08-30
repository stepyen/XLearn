package com.stepyen.xlearn.fragment.expands.SMSListen;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 * <p>
 * <p>
 * <p>
 * <p>
 * 监听短信权限
 * <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
 * <uses-permission android:name="android.permission.READ_SMS"></uses-permission>
 */
@Page(name = "短信监听", extra = R.drawable.ic_widget_imageview)
public class SMSListenFragment extends BaseFragment {

    public static final int WHAT_SMS_CHANGE = 100;
    private static final String TAG = "SMSListenFragment";
    @BindView(R.id.tv_sms_listen)
    Button mTvSmsListen;
    @BindView(R.id.tv_sms_content)
    TextView mTvSmsContent;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_SMS_CHANGE:
                    break;
            }
        }
    };
    private ContentResolver mContentResolver;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sms_listen;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.tv_sms_listen).setOnClickListener(v -> {
            getPermission();
        });
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECEIVE_SMS}, 2);
        }


        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            permissionGrant();
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, 2);
        }
    }



    private void permissionGrant() {
        registerContentObserver();
        querySms();
    }

    private void registerContentObserver() {
        mContentResolver = mActivity.getContentResolver();
        GetSmsMessage getSmsMessage = new GetSmsMessage(mHandler);
        mContentResolver.registerContentObserver(Uri.parse("content://sms/"), true, getSmsMessage);
    }

    private void querySms() {
        Cursor cursor = mContentResolver.query(
                Uri.parse("content://sms"),
                new String[]{"_id", "address", "date", "body", "type"},
                "read=?",
                new String[]{"0"},  // 0代表未读，1代表已读
                "_id desc");

        if (cursor == null || cursor.getColumnCount() <= 0) {
            if (cursor != null) {
                cursor.close();
            }
            return;
        }

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String address = cursor.getString(1);
                long date = cursor.getLong(2);
                String body = cursor.getString(3);
                String type = cursor.getString(4);

                String content =
                        "\n\n_id=" + id +
                        "\naddress=" + address +
                        "\ndate=" + date +
                        "\nbody=" + body +
                        "\ntype=" + type;

                mTvSmsContent.append(content);
                Log.d(TAG,content );
            }
        }

        if (cursor != null) {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}