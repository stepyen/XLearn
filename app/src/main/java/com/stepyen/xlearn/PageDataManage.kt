package com.stepyen.xlearn

import com.stepyen.chivox.ChivoxActivity
import com.stepyen.xlearn.activity.network.socket.SocketActivity
import com.stepyen.xlearn.activity.UMengActivity
import com.stepyen.xlearn.activity.audio.AudioActivity
import com.stepyen.xlearn.activity.function.HandleActivity
import com.stepyen.xlearn.activity.function.JsonActivity
import com.stepyen.xlearn.activity.function.UriActivity
import com.stepyen.xlearn.activity.module.activity.ActivityActivity
import com.stepyen.xlearn.activity.network.websocket.WebSocketActivity
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
            add(PageBean("websocket", WebSocketActivity::class.java))

        })
        put("音视频", arrayListOf<PageBean>().apply {

            add(PageBean("音频", AudioActivity::class.java))

        })
        put("功能", arrayListOf<PageBean>().apply {

            add(PageBean("Handle", HandleActivity::class.java))
            add(PageBean("Json", JsonActivity::class.java))
            add(PageBean("Uri", UriActivity::class.java))

        })

        put("测试", arrayListOf<PageBean>().apply {

            add(PageBean("测试", TestActivity::class.java))

        })
        put("组件", arrayListOf<PageBean>().apply {

            add(PageBean("Activity", ActivityActivity::class.java))
            add(PageBean("Service", ActivityActivity::class.java))
            add(PageBean("ContentProvide", ActivityActivity::class.java))
            add(PageBean("BroadcastReceiver", ActivityActivity::class.java))

        })
        put("动画", arrayListOf<PageBean>().apply {

            add(PageBean("帧动画", ActivityActivity::class.java))
            add(PageBean("补间动画", ActivityActivity::class.java))
            add(PageBean("属性动画", ActivityActivity::class.java))

        })











    }

    private fun put(title: String, list: ArrayList<PageBean>) {
        data[PageBean(title, PageListActivity::class.java)] = list
    }


}