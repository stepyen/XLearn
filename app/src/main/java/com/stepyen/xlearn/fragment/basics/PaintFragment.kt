package com.stepyen.xlearn.fragment.basics

import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseTestFragment
import com.xuexiang.xpage.annotation.Page

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Paint", extra = R.drawable.ic_widget_imageview)
class PaintFragment : BaseTestFragment() {
    override fun initLayoutView() {
        addView(R.layout.fragmen_paint)
    }

    override fun initViews() {

    }


}