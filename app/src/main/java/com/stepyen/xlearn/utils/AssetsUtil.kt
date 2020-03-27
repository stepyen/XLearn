package com.stepyen.xlearn.utils

import android.content.Context
import com.stepyen.xlearn.extend.closeIO
import java.io.File
import java.io.FileOutputStream

/**
 * date：2020-03-26
 * author：stepyen
 * description：
 *
 */
class AssetsUtil {


    companion object {
        /**
         * 从 assets 拷贝文件
         */
        fun copyFromAssets(context: Context, fileName: String, path: String) {

            val inputStream = context.assets.open(fileName)
            val file = File(path)
            val fileOutputStream = FileOutputStream(file)
            var buffer = ByteArray(1024)

            var byteCount = inputStream.read(buffer)

            while (byteCount != -1) {
                fileOutputStream.write(buffer, 0, byteCount)
                byteCount = inputStream.read(buffer)
            }

            fileOutputStream.flush()
            fileOutputStream.closeIO()
            inputStream.closeIO()
        }
    }


}