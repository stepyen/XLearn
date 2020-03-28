package com.stepyen.common

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.stepyen.common.utils.IntentLogUtil
import com.stepyen.common.utils.L
import com.stepyen.xutil.display.DensityUtils
import kotlinx.android.synthetic.main.activity_base_page.*

/**
 * date：2020-03-13
 * author：stepyen
 * description：
 *
 */
open class BasePageActivity :AppCompatActivity() {

    open var TAG = "BaseLifePageTAG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_page)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //竖屏

        L.d("$TAG   onCreate: ")
        L.d("$TAG   onCreate: ${IntentLogUtil.getIntentAllInfo(intent)}")
        L.d("$TAG   onCreate: taskId：${taskId}")

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

    protected fun addButton(title: String, clickAction: (View) -> Unit) {
        val clickListener = View.OnClickListener { v -> clickAction(v) }

        addButton(title, clickListener, 0)
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

    protected fun addTag(txt: CharSequence): TextView? {
        val tv = TextView(this@BasePageActivity).apply {
            text = txt
            setTextColor(ContextCompat.getColor(this@BasePageActivity,R.color.xui_config_main_theme))
            textSize = 15f
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                setMargins(DensityUtils.dp2px(this@BasePageActivity, 15f), DensityUtils.dp2px(this@BasePageActivity,15f), DensityUtils.dp2px(this@BasePageActivity,15f), DensityUtils.dp2px(this@BasePageActivity,15f))
            }
        }

        ll_parent.addView(tv)

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

        Toast.makeText(this,str, Toast.LENGTH_SHORT)

    }

    override fun onStart() {
        super.onStart()
        L.d("$TAG   onStart: ")
    }

    override fun onResume() {
        super.onResume()
        L.d("$TAG   onResume: ")
    }

    override fun onPause() {
        super.onPause()
        L.d("$TAG   onPause: ")
    }

    override fun onStop() {
        super.onStop()
        L.d("$TAG   onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        L.d("$TAG   onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        L.d("$TAG   onRestart: ")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        L.d("$TAG   onConfigurationChanged: ")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        L.d("$TAG   onRestoreInstanceState: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        L.d("$TAG   onSaveInstanceState: ")

    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        L.d("$TAG   onNewIntent: ")
        L.d("$TAG   onNewIntent: taskId：${taskId}")
        L.d("$TAG   onNewIntent: ${IntentLogUtil.getIntentAllInfo(intent)}")
    }
}