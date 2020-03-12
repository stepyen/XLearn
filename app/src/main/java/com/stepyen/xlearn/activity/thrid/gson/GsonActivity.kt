package com.stepyen.xlearn.activity.thrid.gson

import android.view.View
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import org.json.JSONObject

/**
 * date：2020-03-10
 * author：stepyen
 * description：
 *
 */
class GsonActivity : BasePageActivity() {
    override fun initView() {
        val json = JSONObject().apply {
            put("name","小明")
            put("age",17)
        }.toString()


        addButton("Java 解析", View.OnClickListener {
            val user = Gson().fromJson<User>(json, User::class.java)
            Logger.d(user.toString())

        })
        addButton("Kotlin 解析", View.OnClickListener {
            val userKotlin = Gson().fromJson<UserKotlin>(json, UserKotlin::class.java)
            Logger.d(userKotlin.toString())
        })
    }
}