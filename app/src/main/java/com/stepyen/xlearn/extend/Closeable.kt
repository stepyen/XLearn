package com.stepyen.xlearn.extend

import java.io.Closeable
import java.io.IOException

/**
 * date：2020-03-27
 * author：stepyen
 * description：
 *
 */

/**
 * 关闭IO流
 */
inline fun Closeable.closeIO():Boolean {

    try {
        close()
    } catch (e: IOException) {
        return false
    }

    return true
}










