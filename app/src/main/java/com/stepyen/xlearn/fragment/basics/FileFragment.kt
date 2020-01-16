package com.stepyen.xlearn.fragment.basics

import android.annotation.SuppressLint
import android.os.Environment
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.stepyen.xutil.tip.ToastUtils
import com.xuexiang.xpage.annotation.Page
import kotlinx.android.synthetic.main.fragment_file.*
import java.io.File

/**
 * date：2020-01-16
 * author：stepyen
 * description：学习 文件
 *
 * 1、内部存储 和 外部存储
 *
 * 2、File 类 API 学习
 */
@Page(name = "文件", extra = R.drawable.ic_widget_imageview)
class FileFragment : BaseFragment() {
    override fun initViews() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_file


    override fun kotlinInitViews() {
        storageInfo()
        testMkdir()
    }


    private fun storageInfo() {
        val sb = StringBuilder().apply{
            append("外部存储-根路径：${Environment.getDataDirectory().absolutePath}\n")
            append("外部存储-状态：${Environment.getExternalStorageState()}\n")
            append("内部存储-cache：${context!!.cacheDir.absolutePath}\n")
            append("内部存储-files：${context!!.filesDir.absolutePath}\n")
            append("外部存储-cache：${context!!.externalCacheDir.absolutePath}\n")
        }




        tv_storage_info.text = sb.toString()
    }


    /**
     * 创建文件夹
     *
     * mkdir  只能创建一个父文件夹
     *
     * mkdirs 可以根据深度，连续创建文件夹
     *
     */
    private fun testMkdir() {
        val path = "${context!!.externalCacheDir.absolutePath}/file/file1/file2/file3"
        tv_file_path.text = "文件夹 Path : $path"
        val file = File(path)
        btn_mkdir.setOnClickListener {
            ToastUtils.toast("创建${file.mkdir()}")
        }
        btn_mkdirs.setOnClickListener {
            ToastUtils.toast("创建${file.mkdirs()}")
        }
    }

}
