package com.stepyen.xlearn.extend

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
 * 检查View的可见性，并设置为可见
 */
inline fun View.checkVisibility() {
    this?.run {
        if (visibility == View.GONE) {
            visibility = View.VISIBLE
        }
    }
}

/**
 *
 * 设置View的可见性
 */
inline fun View.setVisibility(visibility: Boolean) {
    this?.visibility = if (visibility) View.VISIBLE else View.GONE
}

/**
 * 检查值，当值为空，隐藏；当值不为空时，执行相应操作
 */
inline fun View.checkValueWhenEmptyGone(value: CharSequence? = null, isNoEmptyFunction:View.()->Unit) {
    var isEmpty = TextUtils.isEmpty(value)
    visibility = if (isEmpty) View.GONE else View.VISIBLE

    if (!isEmpty) {
        isNoEmptyFunction()
    }
}

inline fun View.checkValueWhenEmptyInvisible(value: CharSequence? = null, isNoEmptyFunction:()->Unit) {
    var isEmpty = TextUtils.isEmpty(value)
    visibility = if (isEmpty) View.INVISIBLE else View.VISIBLE

    if (!isEmpty) {
        isNoEmptyFunction()
    }
}

/**
 * 检查一个集合是否为空，为空时，View隐藏；不为空时，View显示，并执行相应操作
 */
inline fun View.checkListWhenEmptyGone(list: List<Any>? = null, isNoEmptyFunction:View.()->Unit) {
    if (list == null || list.isEmpty()) {
        visibility =  View.GONE
    }else{
        visibility =  View.VISIBLE
        isNoEmptyFunction()
    }
}

/**
 * 检查一个集合是否为空，为空时，View隐藏；不为空时，View显示，并执行相应操作
 */
inline fun View.checkListWhenEmptyInvisible(list: List<Any>? = null, isNoEmptyFunction:View.()->Unit) {
    if (list == null || list.isEmpty()) {
        visibility =  View.INVISIBLE
    }else{
        visibility =  View.VISIBLE
        isNoEmptyFunction()
    }
}






