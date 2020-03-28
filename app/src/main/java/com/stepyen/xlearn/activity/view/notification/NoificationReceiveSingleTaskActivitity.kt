package com.stepyen.xlearn.activity.view.notification

import com.orhanobut.logger.Logger
import com.stepyen.common.BasePageActivity

import com.stepyen.common.utils.IntentLogUtil

/**
 * date：2020-03-13
 * author：stepyen
 * description：通知栏接收页面，启动模式是 SingleTask
 *
 */
class NoificationReceiveSingleTaskActivitity : BasePageActivity() {

    override fun initView() {
        addTextView("通知栏接收页面，启动模式是 SingleTask")

    }
}