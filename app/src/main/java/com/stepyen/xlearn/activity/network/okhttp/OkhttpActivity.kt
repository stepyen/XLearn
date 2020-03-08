package com.stepyen.xlearn.activity.network.okhttp

import android.graphics.BitmapFactory
import android.util.Log
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.*
import java.io.IOException

/**
 *
 * Okhttp
 *
 *
 */
class OkhttpActivity : BasePageActivity() {

    companion object{
        const val TAG = "OkhttpActivityTAG"
    }


    override fun initView() {
        addView(R.layout.activity_okhttp)

        btn_down_image.setOnClickListener {
            downImageView()
        }


    }

    /**
     * 下载图片
     */
    private fun downImageView() {

        val url = "http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg"

        val okHttpClient = OkHttpClient()
        val request: Request = Request.Builder().url(url).get().build() //默认为get请求
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG, "onFailure = $e")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                response?.body()?.bytes()?.let {

                    val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)

                    runOnUiThread {
                        iv_okhttp.setImageBitmap(bitmap)
                    }

                }
            }
        })
    }
}
