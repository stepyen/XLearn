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
class BBTaskActivity : BasePageActivity() {
    override var TAG =  "BBTask_TAG"

    override fun initView() {
        addTextView("当前是 BBTask  在 B 任务栈下")
        addButton("打开 CBTask",View.OnClickListener {
            startActivity(Intent(this@BBTaskActivity,CBTaskActivity::class.java).apply {

            })
        })
    }

}