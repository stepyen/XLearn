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
        JSONObject pushJo = new JSONObject();
        JSONObject pageJo = new JSONObject();
        try {

            // 透传信息
            jo.put("title", "打开游戏内页面");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");

            pushJo.put("id","123");
            pushJo.put("type","getui");

            JSONObject gameJo = new JSONObject();
            gameJo.put("name", "xiaoming");
            gameJo.put("age", "17");

            pageJo.put("url", gameJo.toString());
            pushJo.put("uri","babybus://push/openGame?"+pageJo.toString());

            jo.put("extra", "babybus://push/openPush?"+pushJo.toString());

            L.d(" 游戏内页面 "+jo.toString());
            L.d(" 游戏内页面 extra "+jo.getString("extra"));

            jo = new JSONObject();
            pushJo = new JSONObject();
            pageJo = new JSONObject();




            jo.put("title", "打开外部浏览器");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");

            pushJo.put("id","123");
            pushJo.put("type","getui");


            pageJo.put("url",   "https://www.baidu.com/");
            pushJo.put("uri","babybus://push/openBrowser?"+pageJo.toString());

            jo.put("extra", "babybus://push/openPush?"+pushJo.toString());

            L.d("\n\n 打开外部浏览器 "+jo.toString());
            L.d(" 打开外部浏览器 extra "+jo.getString("extra"));



            jo = new JSONObject();
            pushJo = new JSONObject();
            pageJo = new JSONObject();


            jo.put("title", "打开首页");
            jo.put("content", "通知内容");
            jo.put("isVibrate", "1");
            jo.put("isSound", "1");

            pushJo.put("id","123");
            pushJo.put("type","getui");


            pushJo.put("uri","babybus://push/openHome");

            jo.put("extra", "babybus://push/openPush?"+pushJo.toString());

            L.d("\n\n 打开首页 "+jo.toString());
            L.d(" 打开首页 extra "+jo.getString("extra"));




            // url 直接就是路径
            // intent  intent:#Intent;action=android.intent.action.view;package=com.kid58.tiyong.characters;component=com.kid58.tiyong.characters/com.sinyee.babybus.SplashAct;S.data=dataValue;end


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
