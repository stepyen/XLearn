package com.stepyen.common

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.stepyen.common.utils.IntentLogUtil
import com.stepyen.common.utils.L
import com.stepyen.xlearn.base.BasePageActivity

/**
 * date：2020-03-13
 * author：stepyen
 * description：
 *
 */
open class BaseLifePageActivity :BasePageActivity() {

    open var TAG = "BaseLifePageTAG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.d("$TAG   onCreate: ")
        L.d("$TAG   onCreate: ${IntentLogUtil.getIntentAllInfo(intent)}")
        L.d("$TAG   onCreate: taskId：${taskId}")

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