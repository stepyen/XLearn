package com.stepyen.xlearn.activity.app

import android.annotation.TargetApi
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.L
import com.stepyen.common.utils.ServiceUtil
import com.stepyen.xlearn.R

/**
 * date：2020-03-28
 * author：stepyen
 * description：手机参数
 *
 */
class PhoneParamActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_phone_param)


        val sb = StringBuilder()

        // 状态栏高度
        val statusBarRes = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = resources.getDimensionPixelSize(statusBarRes)
        sb.append("\n状态栏高度：$statusBarHeight\n")


        ServiceUtil.getWindowManager(this).defaultDisplay?.apply {
            sb.append("屏幕旋转角度：$rotation\n")
            sb.append("屏幕高度：$height\n")
            sb.append("屏幕宽度：$width\n")

        }


        L.d(sb.toString())


        addButton("获取旋转角度"){
            ServiceUtil.getWindowManager(this).defaultDisplay.rotation
        }
    }



}