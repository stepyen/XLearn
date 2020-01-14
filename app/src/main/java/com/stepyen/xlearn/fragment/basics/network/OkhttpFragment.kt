package com.stepyen.xlearn.fragment.basics.network

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Message
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.stepyen.xutil.tip.ToastUtils
import com.xuexiang.xpage.annotation.Page
import kotlinx.android.synthetic.main.fragment_okhttp.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * date：2020-01-13
 * author：stepyen
 * description：Okhttp 网络请求
 */
@Page(name = "Okhttp")
class OkhttpFragment : BaseFragment() {
    companion object {
        const val TAG = "OkhttpFragment"

        const val WHAT_DOWN_LOAD_IMAGE = 1

    }


    private val okHttpClient: OkHttpClient by lazy {

        OkHttpClient.Builder().apply {
            connectTimeout(20000L, TimeUnit.SECONDS)
            readTimeout(20000L, TimeUnit.SECONDS)
            writeTimeout(20000L, TimeUnit.SECONDS)
        }.build()


    }

    private val handle: Handler = Handler {

        when (it.what) {
            WHAT_DOWN_LOAD_IMAGE -> {
                val obj = it.obj as ByteArray
                val bitmap = BitmapFactory.decodeByteArray(obj, 0, obj.size)
                showIv.setImageBitmap(bitmap)
            }
            else -> {
            }

        }

        true
    }

    override fun initViews() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_okhttp
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
        postDownLoadFileBtn.setOnClickListener {

        }
        postDownLoadImageBtn.setOnClickListener {
            postDownLoadImage()
        }
        postUploadFileBtn.setOnClickListener {

        }

    }

    /**
     * 下载文件
     */
    private fun postDownLoadFile() {

    }

    /**
     * 下载图片
     */
    private fun postDownLoadImage() {
        val url = "http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg"

        val request = Request.Builder().apply { url(url) }.build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {

                Logger.e(e.toString())

            }

            override fun onResponse(call: Call?, response: Response) {
                Logger.e(response.toString())

                val bytes = response.body().bytes()
                val message = handle.obtainMessage().apply {
                    obj = bytes
                    what = WHAT_DOWN_LOAD_IMAGE
                }

                handle.sendMessage(message)

            }

        })


    }


}