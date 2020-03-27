package com.stepyen.xlearn.extend

import android.content.res.AssetFileDescriptor
import java.io.IOException

/**
 * date：2020-03-27
 * author：stepyen
 * description：
 *
 */

/**
 * 关闭 AssetFile
 */
inline fun AssetFileDescriptor.closeAssetFile():Boolean {

    try {
        close()
    } catch (e: IOException) {
        return false
    }

    return true
}
