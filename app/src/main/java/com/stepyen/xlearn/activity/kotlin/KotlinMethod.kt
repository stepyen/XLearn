package com.stepyen.xlearn.activity.kotlin

import android.text.Spannable

/**
 * date：2020-01-16
 * author：stepyen
 * description：
 *
 */
/**
 *
 * 允许对象直接使用操作符进行操作，如下面的 +=
 *   var spannable: Spannable = SpannableStringBuilder()
spannable  += UnderlineSpan()
 *
 */
inline operator fun Spannable.plusAssign(span: Any) =
        setSpan(span, 0, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)


