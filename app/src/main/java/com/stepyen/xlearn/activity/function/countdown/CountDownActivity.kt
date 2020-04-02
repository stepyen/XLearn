package com.stepyen.xlearn.activity.function.countdown

import android.content.Intent
import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.L
import com.stepyen.xlearn.MainActivity
import com.stepyen.xlearn.R
import com.stepyen.xlearn.activity.function.countdown.CloseTimer

/**
 * date：2020-04-02
 * author：stepyen
 * description：计时
 *
 */

class CountDownActivity : BasePageActivity() {

    private val timer:CloseTimer by lazy{
        CloseTimer(){
            L.d("倒计时结束")
        }
    }
    override fun initView() {

        addButton("开始", View.OnClickListener {
            timer.start()
        })

        addButton("暂停", View.OnClickListener {
            timer.pause()
        })

        addButton("停止", View.OnClickListener {
            timer.stop()
        })


    }


}