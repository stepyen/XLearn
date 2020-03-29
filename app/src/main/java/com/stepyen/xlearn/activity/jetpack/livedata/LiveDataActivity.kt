package com.stepyen.xlearn.activity.jetpack.livedata

import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.R

/**
 * date：2020-03-29
 * author：stepyen
 * description：
 *
 */
class LiveDataActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_test)

        addButton("", View.OnClickListener {

        })
    }
}