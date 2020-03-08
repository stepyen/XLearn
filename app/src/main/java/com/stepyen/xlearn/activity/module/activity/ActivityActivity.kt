package com.stepyen.xlearn.activity.module.activity

import android.content.Intent
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_activity.*

class ActivityActivity : BasePageActivity() {


    override fun initView() {
        addView(R.layout.activity_activity)


        /**
         * 别名 使用场景
         * 1、app更换入口页面时，升级引发奔溃，因为手机的入口信息还是之前那个。
         * 2、桌面添加快捷入口
         * 3、微信登陆封装实现逻辑，在配置清单中，指定别名。
         */
        btn_alias.setOnClickListener {
            startActivity(Intent(this, AliasActivity::class.java))
        }


    }

}
