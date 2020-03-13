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
                sb.append("flags: ${it.toString()}\n")
                val flag = when (it) {
                    0->"无"
                    32768-> "FLAG_ACTIVITY_CLEAR_TASK"      // 0X00008000
                    268435456->"FLAG_ACTIVITY_NEW_TASK"         // 10000000
                    270532608->"FLAG_ACTIVITY_NEW_TASK & FLAG_ACTIVITY_FORWARD_RESULT"         // 10200000
                    272629760 ->"FLAG_ACTIVITY_NEW_TASK & FLAG_ACTIVITY_CLEAR_TOP"         // 10400000
                    else->"无"
                }
                sb.append("$flag\n")
                    .append("\n")
            }


        }

        return sb.toString()

    }
}