package com.stepyen.xlearn.activity.function.aviod_result.test

import android.content.Intent
import android.view.View
import com.stepyen.common.BasePageActivity

/**
 * date：2020/11/12
 * author：stepyen
 * description：
 */
class TestAvoidResultActivity : BasePageActivity() {

    companion object{
        const val REQUEST_CODE = 555
        const val RESULT_CODE = 777
        const val EXTRA_KEY_NAME = "name"
    }
    override fun initView() {
        addButton("设置结果", View.OnClickListener { v: View? ->
            val data = Intent()
            data.putExtra(EXTRA_KEY_NAME, "orange")
            setResult(RESULT_CODE, data)
            finish()
        })
    }
}