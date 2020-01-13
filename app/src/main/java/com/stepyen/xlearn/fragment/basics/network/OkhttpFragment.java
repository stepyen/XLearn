package com.stepyen.xlearn.fragment.basics.network;

import android.util.Log;
import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;
import java.io.IOException;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */

@Page(name = "Okhttp")
public class OkhttpFragment extends BaseTestFragment {
    private static final String TAG = "OkhttpFragment";
    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_okhttp);
    }

    @Override
    protected void initViews() {

    }


    @OnClick({R.id.btn_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                postImage();

                break;
        }
    }


    public void postImage() {
        String url = "https://betachinese.kid58.com/AllConfig/allimage?appid=a3db11e896030050&md5=6e9b2c9f7866f32e0519a67f7ad31dd9";
        String clientInfo = "QauYefZ/GDHc5XZAt8M9qGBIiRa62GBXBxgpfYk4JU6hCcfoGmqQ1bGSlilmtDD7KIiIlN2XrEXZqdyP/QwsTX9q/+c44ATCKQqwBQJxK5OKsTHxHcw0e4my0igTFASTCi5l5SDwYUKHQ+Z9WqPAtJSYaz+hfNXrf6NNS3lmPeubrnmG4K89GU6AoOQizAHNonzlMcQjp6CT/dYyd62oTHPaUIov9rriR0lXQToVf6JJzYcxf/AeThYhGBQE4MBLeenpkXVcRpyHbdLH7iJ5qcb38WE08yIZT1U+QYiBOrSMZGbh2ULHccGKsInlviF3C+IKsxBNUm7Lzx1mT0i691a8gTJpB9Tb3MDY+IgP3epvTQqn19KOa85u/8yZGmsjZwey6voS9tnSK1BnpW8/hvpg5AIssqJp3FH0oTObD8o9gYPQtGhosGioZ8x29DyJqSF5JvjRPQB6RCgpGAKfWGjcM7vizJkpNe/fpKyYZvclpBYczKZ/+/6kDRxr1ydVBIgVlEHwL6VAVbdtnqHt9tm9hsf1Tp32qjMRFg==";

        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("client-info",clientInfo)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body().string());
            }
        });
    }

    public void post() {
        String url = "https://wanandroid.com/wxarticle/chapters/json";

        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();

        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body());
                Log.d(TAG, "onResponse: "+response.body().toString());
                Log.d(TAG, "onResponse: "+response.body().string());
            }
        });

//        OkHttpUtils.post()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.d(TAG, "onError: "+e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.d(TAG, "onResponse: "+response);
//                    }
//                });
//
//
//        OkHttpUtils.post()
//                .url(url)
//                .build()
//                .execute(new Callback() {
//                    @Override
//                    public Object parseNetworkResponse(Response response, int id) throws Exception {
//                        Log.d(TAG, "parseNetworkResponse: 1 "+response.body().toString());
//                        Log.d(TAG, "parseNetworkResponse: 2 "+response.body().string());
//
//                        return response.body().string();
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Object response, int id) {
//
//                    }
//                });

    }



}
