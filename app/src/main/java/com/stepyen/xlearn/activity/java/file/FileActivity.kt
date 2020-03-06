package com.stepyen.xlearn.activity.java.reflect

import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import java.io.File

/**
 * date：2020-03-06
 * author：stepyen
 * description：文件
 *
 */
class FileActivity : BasePageActivity() {
    override fun initView() {
        addButton("生成文件的uri", View.OnClickListener {
            val uri = File(externalCacheDir.absolutePath).toURI()

            Logger.d(uri)   // file:/storage/emulated/0/Android/data/com.stepyen.xlearn/cache/
        })

    }
}