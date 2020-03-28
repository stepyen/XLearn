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
class CBTaskActivity : BasePageActivity() {
    override var TAG =  "CBTask_TAG"

    override fun initView() {
        addTextView("当前是 CBTask  在 B 任务栈下")
        addButton("打开 D",View.OnClickListener {
            startActivity(Intent(this@CBTaskActivity,DActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        })
    }

}