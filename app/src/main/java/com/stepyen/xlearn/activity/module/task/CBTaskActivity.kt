package com.stepyen.xlearn.activity.module.task

import android.content.Intent
import android.view.View
import com.stepyen.common.BaseLifePageActivity
import com.stepyen.xlearn.base.BasePageActivity

/**
 * date：2020-03-13
 * author：stepyen
 * description：
 *
 */
class CBTaskActivity : BaseLifePageActivity() {
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