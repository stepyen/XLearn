package com.stepyen.xlearn

import com.stepyen.chivox.ChivoxActivity
import com.stepyen.xlearn.activity.UMengActivity
import com.stepyen.xlearn.bean.PageBean

/**
 * date：2020-02-12
 * author：stepyen
 * description：页面数据管理
 *
 */
object DataManage {
    val data: HashMap<PageBean, ArrayList<PageBean>> = HashMap<PageBean, ArrayList<PageBean>>()

    init {

        put("第三方", arrayListOf<PageBean>().apply {
            add(PageBean("友盟", UMengActivity::class.java))
            add(PageBean("驰声", ChivoxActivity::class.java))
        })





    }

    private fun put(title: String, list: ArrayList<PageBean>) {
        data[PageBean(title, PageListActivity::class.java)] = list
    }


}