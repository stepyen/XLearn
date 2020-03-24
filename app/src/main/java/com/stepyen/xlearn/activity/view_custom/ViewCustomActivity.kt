package com.stepyen.xlearn.activity.view_custom

import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_view_custom.*

/**
 *
 * View 自定义
 *
 */
class ViewCustomActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_view_custom)
        testView.setName("小明")
    }
}