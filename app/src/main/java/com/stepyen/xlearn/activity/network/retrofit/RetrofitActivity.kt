package com.stepyen.xlearn.activity.network.retrofit

import android.graphics.BitmapFactory
import android.util.Log
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_retrofit.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.stepyen.xutil.file.FileIOUtils
import okhttp3.MediaType
import java.io.File


class RetrofitActivity : BasePageActivity() {

    companion object{
        const val TAG = "RetrofitTAG"
    }

    var filePath = ""


    override fun initView() {
        addView(R.layout.activity_retrofit)

        filePath = "${ externalCacheDir.absolutePath}/a.png"


        getBtn.setOnClickListener {
            get()
        }
        postBtn.setOnClickListener {

        }
        postFormBtn.setOnClickListener {

        }
        postJsonBtn.setOnClickListener {

        }
        getDownLoadBtn.setOnClickListener {

        }
        getDownLoadBtn.setOnClickListener {
            postDownLoadImage()
        }
        postUploadFileBtn.setOnClickListener {

        }
    }



    private fun get() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Gson解析
                .build()
        val service = retrofit.create(Api::class.java)
        val call = service.getVersion()

        call.enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                Log.i(TAG, "onResponse: " + response.body())
                Log.i(TAG, "onResponse: " + response.body().toString())
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Log.i(TAG, t.message)
            }
        })

    }

    private fun postDownLoadImage() {

        val url = "http://beta.admin.kid58.com/uploads/images/img/b5931961f98c985ed7bc4fc78c612a8b.png"
        val retrofit = Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Gson解析
                .build()

        val service = retrofit.create(Api::class.java).also {
            it.downImage(url).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, e: Throwable?) {
                    Log.i(TAG, "onResponse: " + e.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                    Log.i(TAG, "onResponse: ${response?.body()?.string()}")

                   response?.body()?.bytes()?.apply {
                        runOnUiThread {
                            showIv.setImageBitmap(BitmapFactory.decodeByteArray(this,0,this.size))
                        }

                        // 写文件到本地
                        FileIOUtils.writeFileFromBytesByStream(filePath, this)
                    }
                }
            })
        }
    }

    /**
     * 上传文件
     */
    private fun postUploadFile() {
        val baseUrl = "http://139.9.231.20:7777/"
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Gson解析
                .build()

        val file = File(filePath)
        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
        val body = MultipartBody.Part.createFormData("upload", file.getName(), requestFile)
        retrofit.create(Api::class.java).uploadFile(body).enqueue(object :Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.i(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.string())
            }
        })
    }




}
