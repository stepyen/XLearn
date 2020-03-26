package com.stepyen.xlearn

import android.content.Intent
import android.view.View
import com.stepyen.common.BaseLifePageActivity
import com.stepyen.xlearn.base.BasePageActivity

/**
 * 记录学习历程
 *
 *
 */

class MainActivity : BaseLifePageActivity() {
    override var TAG =  "MainActivityTAG"

    override fun initView() {
        for (data in PageDataManage.data) {
            val pageBean = data.key
            val pageBeanList = data.value
            addButton(pageBean.title, View.OnClickListener {

                // 只有一个选项时，直接跳转过去
                if (pageBeanList.size ==1) {
                    startActivity(Intent(this@MainActivity, pageBeanList[0].cls))
                }else{
                    startActivity(Intent(this@MainActivity, PageListActivity::class.java).apply {
                        putExtra(PageListActivity.KEY_PAGE_LIST , pageBeanList)
                    })
                }
            })
        }
    }


    private fun initDataSource() {

    }
}