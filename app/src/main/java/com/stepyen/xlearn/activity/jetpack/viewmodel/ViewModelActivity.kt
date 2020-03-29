package com.stepyen.xlearn.activity.jetpack.viewmodel

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.App
import com.stepyen.xlearn.R

/**
 * date：2020-03-29
 * author：stepyen
 * description：学习 ViewModel
 *
 */

class ViewModelActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_test)

        addButton("", View.OnClickListener {

        })


        val userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(App.get()).create(UserViewModel::class.java)
        userViewModel.getUsers().observe(this, Observer<List<User>> {

        })
    }
}