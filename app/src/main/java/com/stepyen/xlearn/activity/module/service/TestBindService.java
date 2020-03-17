package com.stepyen.xlearn.activity.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;

/**
 * date：2019-11-25
 * author：stepyen
 * description：测试绑定服务
 */
public class TestBindService extends Service {

    private Binder mBinder = new MyBind();

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.d("onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.d("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
    }


    public class MyBind extends Binder{

        public TestBindService getService() {
            return TestBindService.this;
        }
    }
}
