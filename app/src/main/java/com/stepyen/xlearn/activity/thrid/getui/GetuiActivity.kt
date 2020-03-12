package com.stepyen.xlearn.activity.thrid.getui

import com.stepyen.xlearn.base.BasePageActivity

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

        BBGetui.home()
        BBGetui.outBrower()
        BBGetui.innerBrower()
        BBGetui.game()


    }

}