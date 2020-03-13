package com.stepyen.xlearn.activity.module.task

import android.content.Intent
import android.os.RemoteException
import android.view.View
import com.stepyen.common.BaseLifePageActivity

/**
 * date：2020-03-13
 * author：stepyen
 * description：测试 任务栈
 *
 */
class TaskActivity : BaseLifePageActivity() {
    override var TAG =  "Task_TAG"


    override fun initView() {
        addTextView("当前是 Task")
        addButton("打开 A", View.OnClickListener {
            startActivity(Intent(this@TaskActivity, AActivity::class.java).apply {

            })
        })



    }

}