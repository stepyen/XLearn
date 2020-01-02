package com.stepyen.xlearn.fragment.basics.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * date：2019-11-25
 * author：stepyen
 * description：
 */
public class TestBindService extends Service {

    private static final String TAG = "TestBindService";

    private Binder mBinder = new MyBind();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    public class MyBind extends Binder{

        public TestBindService getService() {
            return TestBindService.this;
        }
    }
}
