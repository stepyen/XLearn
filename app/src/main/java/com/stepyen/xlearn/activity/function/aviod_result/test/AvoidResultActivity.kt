package com.stepyen.xlearn.activity.function.aviod_result.test

import android.content.Intent
import android.view.View
import com.stepyen.common.BasePageActivity
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.xlearn.activity.function.aviod_result.AvoidOnResult

/**
 * date：2020/11/12
 * author：stepyen
 * description：
 */
class AvoidResultActivity : BasePageActivity() {
    companion object {
        const val TAG = "Avoid_Result_TAG"
    }


    override fun initView() {

        addButton("Activity Start For Result", View.OnClickListener { v: View? ->
            val intent = Intent(this@AvoidResultActivity, TestAvoidResultActivity::class.java)
            AvoidOnResult(this@AvoidResultActivity)
                    .startForResult(intent, TestAvoidResultActivity.REQUEST_CODE) { requestCode, resultCode, data ->
                        L.d(Companion.TAG, "requestCode:$requestCode    resultCode:$resultCode     data:${data.getStringExtra(TestAvoidResultActivity.EXTRA_KEY_NAME)}")

                    }
        })


        addView(R.layout.activtity_avoid_result)

    }


}