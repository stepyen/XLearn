package com.stepyen.xlearn.activity.exception

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_exception.*

/**
 *
 * 功能点

 *
 */
class ExceptionActivity : BasePageActivity(),Thread.UncaughtExceptionHandler {

    companion object{
        const val TAG = "ExceptionTAG"
    }
    override fun initView() {
//        addView(R.layout.activity_exception)


        addButton("注册捕获异常",View.OnClickListener {
            Thread.setDefaultUncaughtExceptionHandler(this)
        })

        addButton("除0异常",View.OnClickListener {
            var i = 12 / 0
        })



    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        Log.d(TAG,e?.toString())
    }


}
