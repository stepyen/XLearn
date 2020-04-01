package com.stepyen.xlearn.activity.java.file;
import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.common.BasePageActivity
import java.io.File
import java.text.DecimalFormat

/**
 * date：2020-03-06
 * author：stepyen
 * description：文件
 *
 */
class FileActivity : BasePageActivity() {
    override fun initView() {
        addButton("生成文件的uri", View.OnClickListener {
            val uri = File(externalCacheDir?.absolutePath).toURI()

            Logger.d(uri)   // file:/storage/emulated/0/Android/data/com.stepyen.xlearn/cache/
        })

    }


    private val fileIntegerFormat = DecimalFormat("#0")
    private val fileDecimalFormat = DecimalFormat("#0.#")

    /**
     * 单位换算
     *
     * @param size 单位为B
     * @param isInteger 是否返回取整的单位
     * @return 转换后的单位
     */
    fun formatFileSize(size: Long, isInteger: Boolean): String? {
        val df = if (isInteger) fileIntegerFormat else fileDecimalFormat
        var fileSizeString = "0M"
        fileSizeString = if (size < 1024 && size > 0) {
            df.format(size.toDouble()) + "B"
        } else if (size < 1024 * 1024) {
            df.format(size.toDouble() / 1024) + "K"
        } else if (size < 1024 * 1024 * 1024) {
            df.format(size.toDouble() / (1024 * 1024)) + "M"
        } else {
            df.format(size.toDouble() / (1024 * 1024 * 1024)) + "G"
        }
        return fileSizeString
    }

}