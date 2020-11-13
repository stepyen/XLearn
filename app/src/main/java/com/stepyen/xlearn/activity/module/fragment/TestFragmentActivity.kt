package com.stepyen.xlearn.activity.module.fragment

import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.R
import kotlinx.android.synthetic.main.activity_module_test_fragment.*

/**
 * date：2020/11/13
 * author：stepyen
 * description：
 *
 */
class TestFragmentActivity :BasePageActivity(){

    override fun initView() {
        addView(R.layout.activity_module_test_fragment)

        showABtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContentFl, AFragment())
                    .commit()
        }
        showBBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContentFl, BFragment())
                    .commit()
        }
    }

}