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
 * description：学习 RxJava
 *
 */
@Page(name = "RxJava", extra = R.drawable.ic_widget_imageview)
class RxJavaFragment : BaseFragment() {
    override fun initViews() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_rxjava


    override fun kotlinInitViews() {

    }



}
