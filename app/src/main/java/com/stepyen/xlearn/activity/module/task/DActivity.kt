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
class DActivity : BasePageActivity() {
    override var TAG =  "D_TAG"

    override fun initView() {
        addTextView("当前是 D")

        // ABCDB
        addButton("B \n FLAG_ACTIVITY_NEW_TASK", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        })

        // ABCDB
        addButton("B \nFLAG_ACTIVITY_CLEAR_TASK", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        })
        // AB       C死，B死，B创建，D死
        addButton("B \nFLAG_ACTIVITY_CLEAR_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        })

        // ABCDB
        addButton("B \nFLAG_ACTIVITY_SINGLE_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags =  Intent.FLAG_ACTIVITY_SINGLE_TOP
            })
        })

        // B         整个任务栈除了D，按打开顺序，全部死掉，B创建，D死掉
        addButton("B \nFLAG_ACTIVITY_NEW_TASK\nFLAG_ACTIVITY_CLEAR_TASK", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        })

        // AB       C死，B死，B创建，D死
        addButton("B \nFLAG_ACTIVITY_NEW_TASK\nFLAG_ACTIVITY_CLEAR_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        })

        // AB       C死，B onNewIntent,D死
        addButton("B \nFLAG_ACTIVITY_SINGLE_TOP\nFLAG_ACTIVITY_CLEAR_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BActivity::class.java).apply {
                flags =  Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        })

        // ABCDB    B创建
        addButton("B SingleTop", View.OnClickListener {
            startActivity(Intent(this@DActivity,BSingleTopActivity::class.java).apply {

            })
        })

        // AB         C死，B onNewIntent,D死
        addButton("B SingleTask", View.OnClickListener {
            startActivity(Intent(this@DActivity,BSingleTaskActivity::class.java).apply {

            })
        })
        // 按照 ABC 顺序打开，点击返回是 A, A onRestart ，C死，B还在
        // 按照 ABCDB 顺序打开，B onNewIntent。点击返回，依次出现的顺序是DCA
        addButton("B SingleInstance", View.OnClickListener {
            startActivity(Intent(this@DActivity,BSingleInstanceActivity::class.java).apply {

            })
        })


        addTextView("测试打开其他任务栈的 Activity")
        // 依次打开 A BBTask CBTask D ，此时回退栈是 AD    按下任务键，可以看到两个任务栈


        // A BBTask CBTask D BBTask         在 ATask 创建 BBTask
        addButton("B BTask", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskActivity::class.java).apply {

            })
        })

        // A D BBTaskSingleTask    CBTask死，BBTaskSingleTask onNewIntent，
        // SingleTask 自带 FLAG_ACTIVITY_NEW_TASK
        addButton("B BTask SingleTask", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskSingleTaskActivity::class.java).apply {

            })
        })

        // A D BBTaskSingleTask    CBTask死，BBTaskSingleTask onNewIntent，
        addButton("B BTask SingleTask\nFLAG_ACTIVITY_NEW_TASK", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskSingleTaskActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        })

        // 在 D 打开 BTask,显示的是 B 任务栈 当前顶部的 CTask ,此时的回退栈是 A D BBTask CBTask
        addButton("B BTask \nFLAG_ACTIVITY_NEW_TASK", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        })

        //  A D BBTask ， CBTask死 BBTask死 BBTask创建
        addButton("B BTask \nFLAG_ACTIVITY_NEW_TASK\nFLAG_ACTIVITY_CLEAR_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        })

        // A D BBTask，CBTask死， BBTask onNewIntent，
        addButton("B BTask \nFLAG_ACTIVITY_NEW_TASK\nFLAG_ACTIVITY_CLEAR_TOP\nFLAG_ACTIVITY_SINGLE_TOP", View.OnClickListener {
            startActivity(Intent(this@DActivity,BBTaskActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            })
        })

    }

}