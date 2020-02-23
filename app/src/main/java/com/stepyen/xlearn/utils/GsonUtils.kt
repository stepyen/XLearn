package com.stepyen.xlearn.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stepyen.xlearn.activity.network.websocket.entity.BaseMessageBean

/**
 * date：2020-02-22
 * author：stepyen
 * description：gson 工具类
 *
 */
class GsonUtils {

    companion object{

        /**
         * 解析范型
         */
        fun <T> parseGenericity(json:String):BaseMessageBean<T> {

            return Gson().fromJson<BaseMessageBean<T>>(json, object : TypeToken<BaseMessageBean<T>>() {}.type)

        }

    }




}