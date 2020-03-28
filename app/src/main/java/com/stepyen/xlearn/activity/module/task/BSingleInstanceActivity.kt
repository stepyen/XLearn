package com.stepyen.xlearn.activity.module.task

import android.content.Intent
import android.view.View
import com.stepyen.common.BasePageActivity

/**
 * date：2020-03-13
 * author：stepyen
 * description：
 *
 */
class BSingleInstanceActivity : BasePageActivity() {
    override var TAG =  "BSingleInstance_TAG"

    override fun initView() {
        addTextView("当前是 B SingleInstance")
        addButton("打开 C",View.OnClickListener {
            startActivity(Intent(this@BSingleInstanceActivity,CActivity::class.java).apply {

            })
        })
    }

}