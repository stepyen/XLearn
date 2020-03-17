package com.stepyen.xlearn.activity.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;

/**
 * date：2019-11-25
 * author：stepyen
 * description：测试开启服务
 */
public class TestStartService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.d("onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
    }
}
