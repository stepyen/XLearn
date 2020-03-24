package com.stepyen.xlearn.activity.view_custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.stepyen.xlearn.R

/**
 * date：2020-03-24
 * author：stepyen
 * description：
 *
 */
class TestView(context: Context,set:AttributeSet) :RelativeLayout(context,set) {

    private var mRootView: View = View.inflate(context, R.layout.view_test, this)


    private val tv: TextView by lazy {
        findView<TextView>(R.id.tv_test)
    }


    fun setName(name:String) {
        tv?.apply {
            text = name
        }
    }


    private fun <T : View> findView(id: Int): T = mRootView.findViewById(id)

}