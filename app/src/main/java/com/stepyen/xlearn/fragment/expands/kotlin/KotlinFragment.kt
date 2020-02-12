package com.stepyen.xlearn.fragment.expands.kotlin

import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.xuexiang.xpage.annotation.Page

/**
 * date：2020-01-16
 * author：stepyen
 * description：
 *
 */
@Page(name = "Kotlin", extra = R.drawable.ic_widget_imageview)
class KotlinFragment : BaseFragment() {
    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int = R.layout.fragment_kotlin


    override fun kotlinInitViews() {

    }


    /**
     * anko中DSL写法
     */
    fun view() {
//        frameLayout {
//            padding =1
//            backgroundColor = resources.getColor(R.color.abc_btn_colored_borderless_text_material)
//            button("点击我"){
//                onClick {
//                    toast("按钮 点击我")
//                }
//
//                layoutParams = FrameLayout.LayoutParams(wrapContent, wrapContent)
//            }
//
//            onClick {
//                toast("frameLayout 点击我")
//            }
//        }
    }


}