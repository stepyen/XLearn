package com.stepyen.xlearn

import com.stepyen.chivox.ChivoxActivity
import com.stepyen.xlearn.activity.TestActivity
import com.stepyen.xlearn.activity.TestJavaActivtity
import com.stepyen.xlearn.activity.network.socket.SocketActivity
import com.stepyen.xlearn.activity.thrid.ument.UMengActivity
import com.stepyen.xlearn.activity.app.ManifestActivity
import com.stepyen.xlearn.activity.audio.AudioActivity
import com.stepyen.xlearn.activity.exception.ExceptionActivity
import com.stepyen.xlearn.activity.function.HandleActivity
import com.stepyen.xlearn.activity.function.JsonActivity
import com.stepyen.xlearn.activity.function.SchemeUrlActivity
import com.stepyen.xlearn.activity.function.UriActivity
import com.stepyen.xlearn.activity.function.encrypt.EncryptActivity
import com.stepyen.xlearn.activity.java.reflect.FileActivity
import com.stepyen.xlearn.activity.java.reflect.ReflectActivity
import com.stepyen.xlearn.activity.kotlin.KotlinActivity
import com.stepyen.xlearn.activity.module.activity.ActivityActivity
import com.stepyen.xlearn.activity.module.contentprovider.ContentProviderActivity
import com.stepyen.xlearn.activity.module.intent.IntentActivity
import com.stepyen.xlearn.activity.module.service.ServiceActivity
import com.stepyen.xlearn.activity.module.task.TaskActivity
import com.stepyen.xlearn.activity.network.okhttp.OkhttpActivity
import com.stepyen.xlearn.activity.network.websocket.WebSocketActivity
import com.stepyen.xlearn.activity.thrid.getui.GetuiActivity
import com.stepyen.xlearn.activity.thrid.gson.GsonActivity
import com.stepyen.xlearn.activity.thrid.msa.MsaActivity
import com.stepyen.xlearn.activity.view.notification.NotificationActivity
import com.stepyen.xlearn.bean.PageBean

/**
 * date：2020-02-12
 * author：stepyen
 * description：页面数据管理
 *
 */
object PageDataManage {
    val data: LinkedHashMap<PageBean, ArrayList<PageBean>> = LinkedHashMap<PageBean, ArrayList<PageBean>>()

    init {


        put("测试", arrayListOf<PageBean>().apply {

            add(PageBean("kotlin测试", TestActivity::class.java))
            add(PageBean("Java测试", TestJavaActivtity::class.java))

        })


        put("Java", arrayListOf<PageBean>().apply {

            add(PageBean("反射", ReflectActivity::class.java))
            add(PageBean("补间动画", ActivityActivity::class.java))
            add(PageBean("属性动画", ActivityActivity::class.java))
            add(PageBean("文件", FileActivity::class.java))

        })


        put("kotlin", arrayListOf<PageBean>().apply {

            add(PageBean("kotlin", KotlinActivity::class.java))


        })


        put("App", arrayListOf<PageBean>().apply {

            add(PageBean("配置清单", ManifestActivity::class.java))


        })

        put("View", arrayListOf<PageBean>().apply {

            add(PageBean("Notification", NotificationActivity::class.java))


        })
        put("View 基础", arrayListOf<PageBean>().apply {

            add(PageBean("Notification", NotificationActivity::class.java))


        })
        put("View 自定义", arrayListOf<PageBean>().apply {

            add(PageBean("Notification", NotificationActivity::class.java))


        })

        put("组件", arrayListOf<PageBean>().apply {

            add(PageBean("Activity", ActivityActivity::class.java))
            add(PageBean("Service", ServiceActivity::class.java))
            add(PageBean("ContentProvide", ContentProviderActivity::class.java))
            add(PageBean("BroadcastReceiver", ActivityActivity::class.java))
            add(PageBean("Intent", IntentActivity::class.java))
            add(PageBean("Task", TaskActivity::class.java))

        })
        put("动画", arrayListOf<PageBean>().apply {

            add(PageBean("帧动画", ActivityActivity::class.java))
            add(PageBean("补间动画", ActivityActivity::class.java))
            add(PageBean("属性动画", ActivityActivity::class.java))

        })


        put("网络", arrayListOf<PageBean>().apply {

            add(PageBean("socket", SocketActivity::class.java))
            add(PageBean("websocket", WebSocketActivity::class.java))
            add(PageBean("OkHttp", OkhttpActivity::class.java))

        })
        put("音视频", arrayListOf<PageBean>().apply {

            add(PageBean("音频", AudioActivity::class.java))

        })

        put("第三方", arrayListOf<PageBean>().apply {

            add(PageBean("友盟", UMengActivity::class.java))
            add(PageBean("驰声", ChivoxActivity::class.java))
            add(PageBean("msa", MsaActivity::class.java))
            add(PageBean("Gson", GsonActivity::class.java))
            add(PageBean("个推", GetuiActivity::class.java))

        })

        put("功能", arrayListOf<PageBean>().apply {

            add(PageBean("Handle", HandleActivity::class.java))
            add(PageBean("Json", JsonActivity::class.java))
            add(PageBean("Uri", UriActivity::class.java))
            add(PageBean("加解密", EncryptActivity::class.java))
            add(PageBean("异常", ExceptionActivity::class.java))
            add(PageBean("ShemeUrl", SchemeUrlActivity::class.java))

        })


    }

    private fun put(title: String, list: ArrayList<PageBean>) {
        data[PageBean(title, PageListActivity::class.java)] = list
    }


}