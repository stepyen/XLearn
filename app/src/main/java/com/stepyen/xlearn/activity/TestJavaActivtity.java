package com.stepyen.xlearn.activity;

import android.os.Bundle;
import android.util.Log;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

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

        JSONObject jo = new JSONObject();
        try {

            // 透传信息

            String openApp = "babybus://push/openApp?{}";
            jo.put("title", "通知标题");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");
            jo.put("extra", "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\""+openApp+"\"}");

            L.d(" openApp "+jo.toString());

            jo = new JSONObject();
            String openH5 = "babybus://push/openH5?{\"uri\":\"https://www.baidu.com/\"}";
            jo.put("title", "通知标题");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");
            jo.put("extra", "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\""+openH5+"\"}");


            L.d(" openH5 "+jo.toString());

            jo = new JSONObject();
            String openPage = "babybus://push/openPage?{\"uri\":\"com.kid58.tiyong.characters://{\"type\":\"gogamepage\",\"data\":\"{\"age\":17}\"}\"}";
            jo.put("title", "通知标题");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");
            jo.put("extra", "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\""+openPage+"\"}");

            L.d(" openPage "+jo.toString());



            // url 直接就是路径
            // intent  intent:#Intent;action=android.intent.action.view;package=com.kid58.tiyong.characters;component=com.kid58.tiyong.characters/com.sinyee.babybus.SplashAct;S.data=dataValue;end


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
