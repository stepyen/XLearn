package com.stepyen.xlearn

import com.stepyen.chivox.ChivoxActivity
import com.stepyen.xlearn.activity.CopyActivity
import com.stepyen.xlearn.activity.animotion.AnimotionTestActivity
import com.stepyen.xlearn.activity.network.socket.SocketActivity
import com.stepyen.xlearn.activity.thrid.ument.UMengActivity
import com.stepyen.xlearn.activity.app.ManifestActivity
import com.stepyen.xlearn.activity.app.PhoneParamActivity
import com.stepyen.xlearn.activity.app.window_soft_input_mode.WindowSoftInputModeActivity
import com.stepyen.xlearn.activity.audio.AudioActivity
import com.stepyen.xlearn.activity.device.DeviceActivity
import com.stepyen.xlearn.activity.exception.ExceptionActivity
import com.stepyen.xlearn.activity.function.*
import com.stepyen.xlearn.activity.function.aviod_result.test.AvoidResultActivity
import com.stepyen.xlearn.activity.function.countdown.CountDownActivity
import com.stepyen.xlearn.activity.function.encrypt.EncryptActivity
import com.stepyen.xlearn.activity.java.JavaActivtity
import com.stepyen.xlearn.activity.java.file.FileActivity
import com.stepyen.xlearn.activity.kotlin.KotlinActivity
import com.stepyen.xlearn.activity.module.activity.ActivityActivity
import com.stepyen.xlearn.activity.module.contentprovider.ContentProviderActivity
import com.stepyen.xlearn.activity.module.fragment.TestFragmentActivity
import com.stepyen.xlearn.activity.module.intent.IntentActivity
import com.stepyen.xlearn.activity.module.service.ServiceActivity
import com.stepyen.xlearn.activity.module.task.TaskActivity
import com.stepyen.xlearn.activity.network.okhttp.OkhttpActivity
import com.stepyen.xlearn.activity.network.retrofit.RetrofitActivity
import com.stepyen.xlearn.activity.network.websocket.WebSocketActivity
import com.stepyen.xlearn.activity.other.LearnDebugActivity
import com.stepyen.xlearn.activity.screen_adapter.FoldScreenActivity
import com.stepyen.xlearn.activity.thrid.getui.GetuiActivity
import com.stepyen.xlearn.activity.thrid.glide.GlideActivity
import com.stepyen.xlearn.activity.thrid.gson.GsonActivity
import com.stepyen.xlearn.activity.thrid.log.LogActivity
import com.stepyen.xlearn.activity.thrid.msa.MsaActivity
import com.stepyen.xlearn.activity.view.constraintLayout.ConstraintLayoutActivity
import com.stepyen.xlearn.activity.view.imageview.ImageViewActivity
import com.stepyen.xlearn.activity.view.notification.NotificationActivity
import com.stepyen.xlearn.activity.view.surfaceview.SurfaceViewActivity
import com.stepyen.xlearn.activity.view.surfaceview.TwoSurfaceViewOrderActivity
import com.stepyen.xlearn.activity.view.textview.TextViewActivity
import com.stepyen.xlearn.activity.view.webview.WebViewH5Activity
import com.stepyen.xlearn.activity.view_basics.ViewOverlayActivity
import com.stepyen.xlearn.activity.view_custom.ViewCustomActivity
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


        put("设备", arrayListOf<PageBean>().apply {

            add(PageBean("设备信息", DeviceActivity::class.java))


        })

        put("测试", arrayListOf<PageBean>().apply {

            add(PageBean("kotlin", KotlinActivity::class.java))
            add(PageBean("Java测试", JavaActivtity::class.java))
            add(PageBean("Copy", CopyActivity::class.java))

        })


        put("Java", arrayListOf<PageBean>().apply {


            add(PageBean("补间动画", ActivityActivity::class.java))
            add(PageBean("属性动画", ActivityActivity::class.java))
            add(PageBean("文件", FileActivity::class.java))

        })


        put("kotlin", arrayListOf<PageBean>().apply {



        })


        put("App", arrayListOf<PageBean>().apply {

            add(PageBean("配置清单", ManifestActivity::class.java))
            add(PageBean("手机参数", PhoneParamActivity::class.java))
            add(PageBean("WindowSoftInputMode", WindowSoftInputModeActivity::class.java))


        })

        put("View", arrayListOf<PageBean>().apply {
            add(PageBean("ConstraintLayout", ConstraintLayoutActivity::class.java))
            add(PageBean("TextView", TextViewActivity::class.java))
            add(PageBean("ImageView", ImageViewActivity::class.java))
            add(PageBean("Notification", NotificationActivity::class.java))
            add(PageBean("SurfaceView", SurfaceViewActivity::class.java))
            add(PageBean("两个 SurfaceView 层级顺序研究", TwoSurfaceViewOrderActivity::class.java))
            add(PageBean("Android 和 h5 交互", WebViewH5Activity::class.java))


        })
        put("View 基础", arrayListOf<PageBean>().apply {

            add(PageBean("Notification", NotificationActivity::class.java))
            add(PageBean("View 重叠", ViewOverlayActivity::class.java))


        })
        put("View 自定义", arrayListOf<PageBean>().apply {

            add(PageBean("自定义View", ViewCustomActivity::class.java))


        })

        put("组件", arrayListOf<PageBean>().apply {

            add(PageBean("Activity", ActivityActivity::class.java))
            add(PageBean("Service", ServiceActivity::class.java))
            add(PageBean("ContentProvide", ContentProviderActivity::class.java))
            add(PageBean("BroadcastReceiver", ActivityActivity::class.java))
            add(PageBean("Intent", IntentActivity::class.java))
            add(PageBean("Fragment", TestFragmentActivity::class.java))
            add(PageBean("Task", TaskActivity::class.java))

        })
        put("动画", arrayListOf<PageBean>().apply {

            add(PageBean("测试动画", AnimotionTestActivity::class.java))
            add(PageBean("帧动画", ActivityActivity::class.java))
            add(PageBean("补间动画", ActivityActivity::class.java))
            add(PageBean("属性动画", ActivityActivity::class.java))

        })


        put("网络", arrayListOf<PageBean>().apply {

            add(PageBean("socket", SocketActivity::class.java))
            add(PageBean("websocket", WebSocketActivity::class.java))
            add(PageBean("OkHttp", OkhttpActivity::class.java))
            add(PageBean("Retrofit", RetrofitActivity::class.java))

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
            add(PageBean("Glide", GlideActivity::class.java))
            add(PageBean("日志", LogActivity::class.java))

        })

        put("功能", arrayListOf<PageBean>().apply {

            add(PageBean("Handle", HandleActivity::class.java))
            add(PageBean("Json", JsonActivity::class.java))
            add(PageBean("Uri", UriActivity::class.java))
            add(PageBean("加解密", EncryptActivity::class.java))
            add(PageBean("异常", ExceptionActivity::class.java))
            add(PageBean("ShemeUrl", SchemeUrlActivity::class.java))
            add(PageBean("Assets", AssetsActivity::class.java))
            add(PageBean("Bitmap", BitmapActivity::class.java))
            add(PageBean("倒计时", CountDownActivity::class.java))
            add(PageBean("避免 startForResult", AvoidResultActivity::class.java))

        })



        put("其他", arrayListOf<PageBean>().apply {

            add(PageBean("学习 debug", LearnDebugActivity::class.java))


        })
        put("屏幕适配", arrayListOf<PageBean>().apply {

            add(PageBean("折叠屏", FoldScreenActivity::class.java))


        })


    }

    private fun put(title: String, list: ArrayList<PageBean>) {
        data[PageBean(title, PageListActivity::class.java)] = list
    }


}