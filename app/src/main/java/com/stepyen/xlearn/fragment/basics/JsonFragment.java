package com.stepyen.xlearn.fragment.basics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：Json 学习
 *
 * 测试以下情况：
 * 1、put value 为空
 * 2、解析的json中，没有所需字段
 * 3、
 *
 */
@Page(name = "json", extra = R.drawable.ic_widget_imageview)
public class JsonFragment extends BaseFragment {
    private static final String TAG = "CountDownFragment";
    @BindView(R.id.tv_value_is_empty)
    TextView tv_value_is_empty;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_json;
    }

    @Override
    protected void initViews() {
        testValueIsEmpty();
        testParseJson();
    }

    /**
     * 测试 put 值为空的情况
     */
    private void testValueIsEmpty() {


        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("uid", "");  // 可以
            jsonObject.put("name", "小明");

            String age = null;
            jsonObject.put("age", age);     // age 为空，不会被 put 进 Json 中

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "testValueIsEmpty: Json: "+ jsonObject.toString());
        tv_value_is_empty.setText("Json : "+ jsonObject.toString());

    }

    /**
     * 解析 json 没有所需字段情况
     */
    public void testParseJson() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("name", "小明");
            jo.put("age", 17);


            JSONObject jsonObject = new JSONObject(jo.toString());
//            String address = jsonObject.getString("address");   // 会报异常 org.json.JSONException: No value for address

            String address = jsonObject.optString("address");

            Log.i(TAG, "testParseJson: address: "+ address);

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }



    public static class User{
        public String name;
        public int age;
    }
}