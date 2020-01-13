package com.stepyen.xlearn.fragment.basics.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */
public interface Api {


    @POST("wxarticle/chapters/json")
    Call<JSONObject> getVersion();

}
