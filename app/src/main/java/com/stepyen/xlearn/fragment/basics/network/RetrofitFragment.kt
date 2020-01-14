package com.stepyen.xlearn.fragment.basics.network

import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.xuexiang.xpage.annotation.Page
import kotlinx.android.synthetic.main.fragment_okhttp.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * date：2020-01-13
 * author：stepyen
 * description：
 */
@Page(name = "Retrofit")
class RetrofitFragment : BaseFragment() {

    companion object {

        private const val TAG = "RetrofitFragment"


    }



    private val handle: Handler = Handler {

        when (it.what) {
            OkhttpFragment.WHAT_DOWN_LOAD_IMAGE -> {
                val obj = it.obj as ByteArray
                val bitmap = BitmapFactory.decodeByteArray(obj, 0, obj.size)
                showIv.setImageBitmap(bitmap)
            }
            else -> {
            }

        }

        true
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_retrofit
    }

    override fun initViews() {

    }

    override fun kotlinInitViews() {

        getBtn.setOnClickListener {

        }
        postBtn.setOnClickListener {

        }
        postFormBtn.setOnClickListener {

        }
        postJsonBtn.setOnClickListener {

        }
        postDownLoadImageBtn.setOnClickListener {

        }
        postDownLoadImageAndShowBtn.setOnClickListener {
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
                Log.d(TAG, "onResponse: " + response.body())
                Log.d(TAG, "onResponse: " + response.body().toString())
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Log.e(TAG, t.message)
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
                    Logger.e(e.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                    Logger.e(response.toString())

                    val bytes = response.body().bytes()
                    val message = handle.obtainMessage().apply {
                        obj = bytes
                        what = OkhttpFragment.WHAT_DOWN_LOAD_IMAGE
                    }

                    handle.sendMessage(message)

                }

            })
        }

    }


}