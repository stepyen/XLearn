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
class BActivity : BaseLifePageActivity() {
    override var TAG =  "B_TAG"

    override fun initView() {
        addTextView("当前是 B")
        addButton("打开 C",View.OnClickListener {
            startActivity(Intent(this@BActivity,CActivity::class.java).apply {

            })
        })
    }

}