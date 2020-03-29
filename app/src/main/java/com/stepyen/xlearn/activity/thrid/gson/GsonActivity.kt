package com.stepyen.xlearn.activity.thrid.gson

import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import com.stepyen.common.BasePageActivity
import com.stepyen.xlearn.activity.thrid.gson.bean.BaseBean
import com.stepyen.xlearn.activity.thrid.gson.bean.Shop
import com.stepyen.xlearn.activity.thrid.gson.bean.User
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

        val json2 = JSONObject().apply {
            put("code",1)
            put("data",JSONObject().apply {
                put("name","薯片思密达")
            })
        }.toString()


        addButton("范型解析1", View.OnClickListener {
            val baseShop = parseGenericity<Shop>(json2)
            Logger.d(baseShop.data?.name)
        })

        addButton("范型解析2", View.OnClickListener {
            val baseShop = Gson().fromJson<BaseBean<Shop>>(json2, object : TypeToken<BaseBean<Shop>>() {}.type)
            Logger.d(baseShop.data?.name)
        })


    }

    private  fun <T> parseGenericity(json:String): BaseBean<T> {

        return Gson().fromJson<BaseBean<T>>(json, object : TypeToken<BaseBean<T>>() {}.type)

    }

    private  fun <T> parseGenericity2(json:String): BaseBean<T> {

        return Gson().fromJson<BaseBean<T>>(json, object : TypeToken<BaseBean<T>>() {}.type)

    }
}