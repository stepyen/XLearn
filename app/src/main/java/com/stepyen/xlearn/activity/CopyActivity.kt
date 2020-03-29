package com.stepyen.xlearn.activity

import android.content.Intent
import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.MainActivity

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
class CopyActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_test)

        addButton("", View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}