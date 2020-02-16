package com.stepyen.commonsdk.extend

import android.text.TextUtils
import android.view.View
import android.widget.TextView

/**
 * date：2019-08-11
 * author：stepyen
 * description：
 *
 */
/**
 * 获取文本
 */
fun TextView.trim():String {
    return this.text.toString().trim()
}
