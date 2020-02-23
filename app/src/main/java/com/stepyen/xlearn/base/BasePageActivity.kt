package com.stepyen.xlearn.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepyen.xlearn.R
import com.stepyen.xutil.display.DensityUtils
import kotlinx.android.synthetic.main.activity_base_page.*
import kotlinx.android.synthetic.main.activity_test.*

/**
 * date：2020-02-12
 * author：stepyen
 * description：基础页面
 */
open class BasePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_page)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //竖屏

        initData()

        initView()


    }

    open fun initView(){}

    open fun initData(){}


    protected fun addView(layoutId: Int): View {
        val view = LayoutInflater.from(this).inflate(layoutId, null)
        ll_parent.addView(view)
        return view
    }

    protected fun addView(view: View): View {
        ll_parent.addView(view)
        return view
    }



    protected fun addPageButton(title: String, cls: Class<*>, marginTop: Int = 0) {

        addButton(title, View.OnClickListener {

            val intent = Intent(this@BasePageActivity, cls)
            startActivity(intent)

        }, marginTop)
    }


    protected fun addButton(title: String, clickListener: View.OnClickListener) {
        addButton(title, clickListener, 0)
    }

    protected fun addButton(title: String, clickListener: View.OnClickListener, marginTop: Int) {
        val button = Button(this).apply {
            text = title
            isAllCaps = false
            setOnClickListener(clickListener)
        }

        ll_parent.addView(button)
        setLpMarginTop(button, marginTop)
    }

    protected fun addTextView(txt: CharSequence, marginTop: Int=10): TextView? {
        val tv = TextView(this@BasePageActivity).apply {
            text = txt
        }

        ll_parent.addView(tv)
        setLpMarginTop(tv, marginTop)

        return tv
    }

    private fun setLpMarginTop(view: View, marginTop: Int) {

        view?.run {
            val lp = layoutParams as LinearLayout.LayoutParams
            if (lp != null) {
                lp.setMargins(0, DensityUtils.dp2px(this@BasePageActivity, marginTop.toFloat()), 0, 0)
                view.layoutParams = lp
            }
        }
    }


    fun toast(str:String) {

        Toast.makeText(this,str,Toast.LENGTH_SHORT)

    }


}