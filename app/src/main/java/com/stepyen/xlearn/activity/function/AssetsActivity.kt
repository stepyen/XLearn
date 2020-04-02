package com.stepyen.xlearn.activity.function

import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.stepyen.common.utils.L
import com.stepyen.xlearn.App
import com.stepyen.xlearn.DataResouceManager
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.utils.AssetsUtil
import kotlinx.android.synthetic.main.activity_glide.*
import java.io.File
import java.io.FileOutputStream

/**
 * date：2020-03-25
 * author：stepyen
 * description：Assets 学习
 *
 *
 * Assets 的绝对路径：file:///android_asset/
 */
class AssetsActivity : BasePageActivity() {


    override fun initView() {
        addView(R.layout.activity_assets)


        addButton("从 Assets 复制资源到sd卡下"){

            DataResouceManager.copyALL()

        }



    }



}