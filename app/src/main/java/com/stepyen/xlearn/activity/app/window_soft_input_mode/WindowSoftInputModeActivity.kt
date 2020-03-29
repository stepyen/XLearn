package com.stepyen.xlearn.activity.app.window_soft_input_mode

import android.content.Intent
import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.MainActivity
import com.stepyen.xlearn.R

/**
 * date：2020-03-29
 * author：stepyen
 * description：activity 配置清单属性：android:windowSoftInputMode 测试
 *
 */
class WindowSoftInputModeActivity : BasePageActivity() {
    override fun initView() {

        addButton("adjustUnspecified", View.OnClickListener {
            startActivity(Intent(this, AdjustUnspecifiedActivity::class.java))

        })
        addButton("adjustResize", View.OnClickListener {
            startActivity(Intent(this, AdjustResizeActivity::class.java))

        })
        addButton("adjustPan", View.OnClickListener {
            startActivity(Intent(this, AdjustPanActivity::class.java))

        })
    }
}