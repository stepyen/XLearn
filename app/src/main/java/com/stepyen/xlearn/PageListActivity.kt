package com.stepyen.xlearn

import android.content.Intent
import android.view.View
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.bean.PageBean

/**
 * date：2020-02-12
 * author：stepyen
 * description：
 *
 */
class PageListActivity : BasePageActivity() {

    companion object{
        const val KEY_PAGE_LIST = "KEY_PAGE_LIST"
    }

    override fun initView() {

        for (pageBean in intent.getParcelableArrayListExtra<PageBean>(KEY_PAGE_LIST)) {
            addButton(pageBean.title, View.OnClickListener {
                startActivity(Intent(this@PageListActivity, pageBean.cls).apply {
                })
            })
        }

    }
}