package com.stepyen.xlearn.activity.kotlin

/**
 * date：2020-03-13
 * author：stepyen
 * description：转义字符
 */
object TransferredMeaning {
    @JvmStatic
    fun main(args: Array<String>) {



        // 测试转义 \\
        val url = "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openGame?{\\\"url\\\":\\\"{\\\\\\\"type\\\\\\\":\\\\\\\"https:\\\\\\\\\\\\\\/\\\\\\\\\\\\\\/beta-wx.kid58.com\\\\\\\\\\\\\\/double12\\\\\\\\\\\\\\/index\\\\\\\",\\\\\\\"data\\\\\\\":\\\\\\\"\\\\\\\"}\\\"}\"}"
        val game = "babybus://push/openGame"
        val tempUrl = url.replace("\\\\".toRegex(), "")
        if (tempUrl.contains(game)) {
            println("包含")
        } else {
            println("不包含")
        }


    }


}