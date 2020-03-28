package com.stepyen.xlearn.activity.module.intent

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.IntentLogUtil

/**
 *
 *
 *
 */
class IntentActivity : BasePageActivity() {

    override fun initView() {
//        addView(R.layout.activity_intent)

        addButton("拨号界面",View.OnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                putExtra(Intent.EXTRA_PHONE_NUMBER, "18762")
            })
        })
        addButton("Component Intent",View.OnClickListener {
            startActivity(Intent().apply {
                `package` = "com.stepyen.xlearn123"
                component = ComponentName(`package`,"com.stepyen.xlearn.activity.java.JavaActivtity")    // 包名，具体路径
            })
        })



        addButton("获取Intent的全部信息",View.OnClickListener {
            IntentLogUtil.getIntentAllInfo(intent)
        })


        addButton("生成Intent的uri",View.OnClickListener {

            val intent = Intent().apply {
                `package` = "com.kid58.tiyong.characters"
                component = ComponentName(`package`,"com.sinyee.babybus.SplashAct")
                putExtra("name","xiaoming")
                putExtra("age","17")
                data = Uri.parse("babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openActivity?{\\\"url\\\":\\\"https:\\\\\\/\\\\\\/www.luoxia.com\\\\\\/\\\"}\"}")
                action = "android.intent.action.view"
            }

            Logger.d(intent.toUri(Intent.URI_INTENT_SCHEME).toString())
            Logger.d(intent.toUri(Intent.URI_ANDROID_APP_SCHEME).toString())

        })





    }

}
