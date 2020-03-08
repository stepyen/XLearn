package com.stepyen.xlearn.activity.module.intent

import android.content.ComponentName
import android.content.Intent
import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.utils.IntentLogUtil

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


        addButton("获取Intent的全部信息",View.OnClickListener {
            IntentLogUtil.getIntentAllInfo(intent)
        })


        addButton("生成Intent的uri",View.OnClickListener {

            val intent = Intent().apply {
                `package` = "com.stepyen.XLearn"
                component = ComponentName("com.stepyen.XLearn","com.stepyen.XLearn.activity.module.intent.IntentActivity")
                putExtra("data","dataValue")
                putExtra("poet","poet_list_value")
                action = "android.intent.action.view"
            }

            Logger.d(intent.toUri(Intent.URI_INTENT_SCHEME).toString())         // intent:#Intent;action=android.intent.action.view;package=com.stepyen.XLearn;component=com.stepyen.XLearn/.activity.module.intent.IntentActivity;S.data=dataValue;S.poet=poet_list_value;end

            Logger.d(intent.toUri(Intent.URI_ANDROID_APP_SCHEME).toString())    // android-app://com.stepyen.XLearn#Intent;action=android.intent.action.view;component=com.stepyen.XLearn/.activity.module.intent.IntentActivity;S.data=dataValue;S.poet=poet_list_value;end

        })





    }

}
