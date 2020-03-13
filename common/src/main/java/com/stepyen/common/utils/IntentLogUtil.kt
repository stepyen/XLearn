package com.stepyen.common.utils

import android.content.Intent

/**
 * date：2019-12-26
 * author：stepyen
 * description：Intent 日志打印工具类
 *
 */
object IntentLogUtil {

    /**
     * 获取 Intent 的全部信息
     */
    fun getIntentAllInfo(intent: Intent) :String {

        val sb = StringBuilder()

        intent.run {
            sb.append("\n\n")
            action?.let {
                sb.append("action: ${it.toString()}\n")
                .append("\n")
            }

            component?.let {
                sb.append("component: ${it.toString()}\n")
                .append("\n")
            }

            extras?.let {
                sb.append("extras: ${it.toString()}\n")
                    .append("遍历 extras info 如下：\n")
                for (obj in it.keySet()) {
                    sb.append("$obj : ${it.get(obj)}\n")
                }
                sb.append("\n")
            }

            data?.let {
                sb.append("data: ${it.toString()}\n")
                    .append("拆解 data info 如下：\n")
                    .append("sheme: ${it.scheme}\n")
                    .append("authority: ${it.authority}\n")
                    .append("host: ${it.host}\n")
                    .append("port: ${it.port}\n")
                    .append("path: ${it.path}\n")
                    .append("query: ${it.query}\n")
                    .append("fragment: ${it.fragment}\n")

                .append("\n")
            }
            categories?.let {
                sb.append("categories: ${it.toString()}\n")
                    .append("遍历 categories info 如下：\n")
                for (str in categories) {
                    sb.append("$str\n")
                }

                sb.append("\n")
            }

            flags?.let {
                sb.append("flags: ${getFlagStr(it)}\n")
            }


        }

        return sb.toString()

    }


    private fun getFlagStr(flags:Int) :String{

        val flagMap = mutableMapOf(
                Intent.FLAG_ACTIVITY_CLEAR_TASK to "FLAG_ACTIVITY_CLEAR_TASK",
                Intent.FLAG_ACTIVITY_NEW_TASK to "FLAG_ACTIVITY_NEW_TASK",
                Intent.FLAG_ACTIVITY_SINGLE_TOP to "FLAG_ACTIVITY_SINGLE_TOP",
                Intent.FLAG_ACTIVITY_CLEAR_TOP to "FLAG_ACTIVITY_CLEAR_TOP",
                Intent.FLAG_ACTIVITY_FORWARD_RESULT to "FLAG_ACTIVITY_FORWARD_RESULT",
                0x00400000 to "FLAG_RECEIVER_FROM_SHELL"
        )

        var tempFlags = 0

        val sb =StringBuilder().apply {

            for (flag in flagMap) {
                if (flags and flag.key == flag.key) {
                    append("${flag.value} or ")
                    tempFlags += flag.key
                }
            }

        }

        if (tempFlags == flags) {
            sb.append("\n 完全匹配")
        }else{
            sb.append("\n 不完全匹配 flag：$flags 当前匹配总值：$tempFlags 差值：${flags - tempFlags}")
        }


        return sb.append("\n\n").toString()


    }
}