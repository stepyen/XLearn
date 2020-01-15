package com.stepyen.xlearn.fragment.basics.network

import android.graphics.BitmapFactory
import android.os.Handler
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.App
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.stepyen.xutil.tip.ToastUtils
import com.xuexiang.xpage.annotation.Page
import kotlinx.android.synthetic.main.fragment_okhttp.*
import okhttp3.*
import java.io.File
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


        const val IMAGE_URL_1 = "http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg"

        const val IMAGE_URL_2 = "http://beta.admin.kid58.com/uploads/images/img/b5931961f98c985ed7bc4fc78c612a8b.png"

    }

    private var imageUrl = IMAGE_URL_1



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


        changeImagePath.setOnClickListener {
            imageUrl = if (imageUrl == IMAGE_URL_1) IMAGE_URL_2 else IMAGE_URL_1
        }

        getBtn.setOnClickListener {

        }
        postBtn.setOnClickListener {

        }
        postFormBtn.setOnClickListener {

        }
        postJsonBtn.setOnClickListener {

        }
        postDownLoadImageBtn.setOnClickListener {
            postDownLoadImage()
        }
        postDownLoadImageAndShowBtn.setOnClickListener {
            postDownLoadImageAndShow()
        }
        postUploadFileBtn.setOnClickListener {

        }

    }

    /**
     * 下载文件
     */


    /**
     * 下载图片并存在存储中
     */
    private fun postDownLoadImage() {

        val request = Request.Builder().apply { url(imageUrl) }.build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {

                Logger.e(e.toString())

            }

            override fun onResponse(call: Call?, response: Response) {
                Logger.e(response.toString())

                val bytes = response.body()?.bytes()

                val imagePath = "test_image.jgp"

                FileUtil.writeToFile(App.get().externalCacheDir.absolutePath,imagePath,bytes)

                FileUtil.refreshSystemImageDataBase(context, File("${App.get().externalCacheDir}/${imagePath}"))

                Logger.e("保存成功：图片路径：${App.get().externalCacheDir}/${imagePath}")

                ToastUtils.toast("保存成功")
            }

        })
    }

    /**
     * 下载图片并显示
     */
    private fun postDownLoadImageAndShow() {


        val request = Request.Builder().apply { url(imageUrl) }.build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {

                Logger.e(e.toString())

            }

            override fun onResponse(call: Call?, response: Response) {
                Logger.e(response.toString())

                val bytes = response.body()?.bytes()
                val message = handle.obtainMessage().apply {
                    obj = bytes
                    what = WHAT_DOWN_LOAD_IMAGE
                }

                handle.sendMessage(message)

            }

        })


    }


}