package com.stepyen.xlearn.activity.module.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stepyen.xlearn.R

/**
 * date：2020/11/13
 * author：stepyen
 * description：静态
 *
 */
class StaticFragment :Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_module_static, container,false)
        with(view) {

        }
        return view
    }

}