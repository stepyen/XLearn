package com.stepyen.xlearn

import android.content.Intent
import android.view.View
import com.stepyen.xlearn.base.BasePageActivity

/**
 * 记录学习历程
 *
 *
 */
class MainActivity : BasePageActivity() {

    override fun initView() {
        for (data in DataManage.data) {
            val pageBean = data.key
            val pageBeanList = data.value
            addButton(pageBean.title, View.OnClickListener {
                startActivity(Intent(this@MainActivity, PageListActivity::class.java).apply {
                    putExtra(PageListActivity.KEY_PAGE_LIST , pageBeanList)
                })
            })
        }
    }
}