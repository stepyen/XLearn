package com.stepyen.xlearn.activity.network.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

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


    /**
     * 上传文件
     */
    @POST("upload")
    @Multipart
    fun uploadFile(@Part imageFile: MultipartBody.Part): Call<ResponseBody>

    /**
     * 下载文件
     */

    @GET("load")
    fun downloadFile(@Query("filename") filename:String )





}


