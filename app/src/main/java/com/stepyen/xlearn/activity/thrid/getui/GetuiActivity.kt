package com.stepyen.xlearn.activity.thrid.getui

import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.common.BasePageActivity

/**
 * date：2020-03-12
 * author：stepyen
 * description：个推
 *
 */
class GetuiActivity : BasePageActivity() {

    override fun initView() {

        bbGetui()


    }


    /**
     * bb 个推
     */
    private fun bbGetui() {
        Logger.d("--------------通知栏Json------------------")
        BBGetui.home()
        BBGetui.outBrower()
        BBGetui.innerBrower()
        BBGetui.game()
        Logger.d("-----------------Intent uri---------------")
        BBGetui.homeIntentUri()
        BBGetui.gameIntentUri()
        BBGetui.outBrowerIntentUri()
        BBGetui.innerBrowerIntentUri()

    }

}