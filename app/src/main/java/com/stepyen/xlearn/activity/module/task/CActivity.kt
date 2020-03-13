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
class CActivity : BaseLifePageActivity() {
    override var TAG =  "C_TAG"

    override fun initView() {
        addTextView("当前是 C")
        addButton("打开 D",View.OnClickListener {
            startActivity(Intent(this@CActivity,DActivity::class.java).apply {

            })
        })
    }

}