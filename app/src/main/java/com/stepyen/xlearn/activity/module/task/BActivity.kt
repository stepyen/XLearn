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
class BActivity : BasePageActivity() {
    override var TAG =  "B_TAG"

    override fun initView() {
        addTextView("当前是 B")
        addButton("打开 C",View.OnClickListener {
            startActivity(Intent(this@BActivity,CActivity::class.java).apply {

            })
        })
    }

}