package com.stepyen.xlearn.extend

import androidx.annotation.StringRes
import com.stepyen.xui.utils.ResUtils

/**
 * date：2019-08-11
 * author：stepyen
 * description：
 *
 */
/**
 * 设置文本，预留文本
 *
 */
fun CharSequence.getStringFormat(@StringRes resId: Int, vararg args: Any) :CharSequence{
    return String.format(ResUtils.getString(resId), args)
}

fun CharSequence.getSpiltList(): List<String> {
    return split(",") ?: listOf()
}

