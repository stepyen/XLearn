package com.stepyen.xlearn.activity.module.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * date：2019-11-25
 * author：stepyen
 * description：
 */
public class TestIntentService extends IntentService {

    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
