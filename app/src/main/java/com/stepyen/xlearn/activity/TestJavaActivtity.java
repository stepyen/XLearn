package com.stepyen.xlearn.activity;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.stepyen.xlearn.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * date：2020-03-09
 * author：stepyen
 * description：
 */
public class TestJavaActivtity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_java);

        String url = "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openGame?{\\\"url\\\":\\\"{\\\\\\\"type\\\\\\\":\\\\\\\"https:\\\\\\\\\\\\\\/\\\\\\\\\\\\\\/beta-wx.kid58.com\\\\\\\\\\\\\\/double12\\\\\\\\\\\\\\/index\\\\\\\",\\\\\\\"data\\\\\\\":\\\\\\\"\\\\\\\"}\\\"}\"}";

        String game = "babybus://push/openGame";

        String tempUrl = url.replaceAll("\\\\", "");
        if (tempUrl.contains(game)) {
            Logger.d("包含");
        }else{
            Logger.d("不包含");
        }
    }
}
