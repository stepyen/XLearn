package com.stepyen.xlearn.activity.module.intent

import android.content.Intent
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_intent.*

/**
 *
 *
 *
 */
class IntentActivity : BasePageActivity() {

    override fun initView() {
        addView(R.layout.activity_intent)




    }

    private fun gotoPage() {
        /**
         * 拨号界面
         */
        btn_dial.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                putExtra(Intent.EXTRA_PHONE_NUMBER, "18762")
            })
        }
    }


}
