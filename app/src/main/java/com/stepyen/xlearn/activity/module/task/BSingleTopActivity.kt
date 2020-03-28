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
class BSingleTopActivity : BasePageActivity() {
    override var TAG =  "BSingleTop_TAG"

    override fun initView() {
        addTextView("当前是 B SingleTop")
        addButton("打开 C",View.OnClickListener {
            startActivity(Intent(this@BSingleTopActivity,CActivity::class.java).apply {

            })
        })
    }

}