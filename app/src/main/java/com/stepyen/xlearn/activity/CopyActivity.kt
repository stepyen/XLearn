package com.stepyen.xlearn.activity

import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
class CopyActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_test)

        addButton("", View.OnClickListener {

        })
    }
}