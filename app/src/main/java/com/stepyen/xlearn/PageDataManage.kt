package com.stepyen.xlearn

import com.stepyen.chivox.ChivoxActivity
import com.stepyen.xlearn.activity.socket.SocketActivity
import com.stepyen.xlearn.activity.UMengActivity
import com.stepyen.xlearn.activity.audio.AudioActivity
import com.stepyen.xlearn.bean.PageBean

/**
 * date：2020-02-12
 * author：stepyen
 * description：页面数据管理
 *
 */
object PageDataManage {
    val data: HashMap<PageBean, ArrayList<PageBean>> = HashMap<PageBean, ArrayList<PageBean>>()

    init {

        put("第三方", arrayListOf<PageBean>().apply {
            add(PageBean("友盟", UMengActivity::class.java))
            add(PageBean("驰声", ChivoxActivity::class.java))
        })

        put("网络", arrayListOf<PageBean>().apply {
            add(PageBean("socket", SocketActivity::class.java))

        })
        put("音视频", arrayListOf<PageBean>().apply {
            add(PageBean("音频", AudioActivity::class.java))

        })






    }

    private fun put(title: String, list: ArrayList<PageBean>) {
        data[PageBean(title, PageListActivity::class.java)] = list
    }


}