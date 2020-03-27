package com.stepyen.xlearn.extend
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
inline fun TextView.trim():String {
    return this.text.toString().trim()
}
