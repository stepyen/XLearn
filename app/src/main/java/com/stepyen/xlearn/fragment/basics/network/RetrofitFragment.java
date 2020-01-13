package com.stepyen.xlearn.fragment.basics.network;

import android.util.Log;
import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import org.json.JSONObject;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */
@Page(name = "Retrofit")
public class RetrofitFragment extends BaseTestFragment {

    private static final String TAG = "RetrofitFragment";
    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_retrofit);
    }

    @Override
    protected void initViews() {

    }


    @OnClick({R.id.btn_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                post();
                break;
        }
    }

    public void post() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())//Gson解析
                .build();

        Api service = retrofit.create(Api.class);
        Call<JSONObject> call = service.getVersion();
        //调用服务请求时的修改
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                Log.d(TAG, "onResponse: "+response.body());
                Log.d(TAG, "onResponse: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e(TAG, t.getMessage());

            }
        });
    }
}
