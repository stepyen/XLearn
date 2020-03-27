package com.stepyen.xlearn.extend

import android.database.Cursor
import java.io.IOException

/**
 * date：2020-03-27
 * author：stepyen
 * description：
 *
 */

/**
 * 关闭游标
 */
inline fun Cursor.closeCursor():Boolean {

    try {
        close()
    } catch (e: IOException) {
        return false
    }

    return true
}
