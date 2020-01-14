package com.stepyen.xlearn.fragment.basics.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */
interface Api {


    @POST("wxarticle/chapters/json")
    fun getVersion() : Call<JSONObject>

    @GET
    fun downImage(@Url url: String): Call<ResponseBody>

}


