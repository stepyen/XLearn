package com.stepyen.xlearn.activity.kotlin

import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity

class KotlinActivity : BasePageActivity() {

    override fun initView() {
        addView(R.layout.activity_kotlin)

        addButton("", View.OnClickListener {

        })


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
