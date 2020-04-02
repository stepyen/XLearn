package com.stepyen.xlearn.activity.function.countdown

import android.os.Handler
import com.stepyen.common.utils.L

/**
 *
 * 计时
 */
class CloseTimer(private val endTimeAction: ()->Unit) : Handler(), Runnable {

    companion object{
        private const val DEFAULT_TIME_COUNT = 0
        private const val MAX_TIME_COUNT = 5
    }

    private var timeCount = DEFAULT_TIME_COUNT
    private var isPause = false

    fun start() {
        if (isPause) {
            isPause = false
        } else {
            stop()
        }
        post(this)
    }

    fun pause() {
        isPause = true
        removeCallbacksAndMessages(null)
    }

    fun stop() {
        removeCallbacksAndMessages(null)
        timeCount = DEFAULT_TIME_COUNT
        isPause = false
    }

    override fun run() {
        L.d("时间：$timeCount")
        postDelayed(this, 1000)
        timeCount++

        if (timeCount >= MAX_TIME_COUNT) {
            stop()
            endTimeAction()
        }
    }

}