package com.stepyen.xlearn.activity.view.notification

import com.stepyen.common.BasePageActivity

/**
 * date：2020-03-08
 * author：stepyen
 * description：
 *
 */
class NotificationTestParentActivity : BasePageActivity() {

    override fun initView() {
        addTextView("父页面是 MainActivity，从通知栏跳转到这个页面，点击返回会回到 MainActivity")

    }
}