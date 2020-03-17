package com.stepyen.xlearn.activity.thrid.getui

import android.content.ComponentName
import android.content.Intent
import com.orhanobut.logger.Logger
import org.json.JSONObject

/**
 * date：2020-03-12
 * author：stepyen
 * description：宝宝巴士-个推
 *
 */
class BBGetui {


    companion object{

        /**
         * 首页
         */
        fun home() {


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openHome")
            }


            val jo = JSONObject().apply {
                put("title", "首页")
                put("content", "通知内容")
                put("isVibrate", "1")
                put("isSound", "1")
                put("extra", "babybus://push/openPush?$pushJo")
            }


            Logger.d(" 首页 $jo")
            Logger.d(" 首页 extra " + jo.getString("extra"))

        }

        /**
         * 游戏
         */
        fun game() {

            val gameJo = JSONObject().apply {
                put("type", "https://beta-wx.kid58.com/double12/index")
                put("data", "")
            }

            val pageJo = JSONObject().apply {
                put("url", gameJo.toString())
            }


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openGame?${pageJo.toString()}")
            }


            val jo = JSONObject().apply {
                put("title", "游戏内页面")
                put("content", "通知内容")
                put("isVibrate", "1")
                put("isSound", "1")
                put("extra", "babybus://push/openPush?$pushJo")
            }

            Logger.d(" 游戏内页面 $jo")
            Logger.d(" 游戏内页面 extra " + jo.getString("extra"))

        }
        /**
         * 外部浏览器
         */
        fun outBrower() {

            val pageJo = JSONObject().apply {
                put("url", "https://www.baidu.com/")
            }


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openBrowser?${pageJo.toString()}")
            }


            val jo = JSONObject().apply {
                put("title", "外部浏览器")
                put("content", "通知内容")
                put("isVibrate", "1")
                put("isSound", "1")
                put("extra", "babybus://push/openPush?$pushJo")
            }


            Logger.d(" 外部浏览器 $jo")
            Logger.d(" 外部浏览器 extra " + jo.getString("extra"))
        }
        /**
         * 内部浏览器
         */
        fun innerBrower() {

            val pageJo = JSONObject().apply {
                put("url", "https://www.luoxia.com/")
            }


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openActivity?${pageJo.toString()}")
            }


            val jo = JSONObject().apply {
                put("title", "内部浏览器")
                put("content", "通知内容")
                put("isVibrate", "1")
                put("isSound", "1")
                put("extra", "babybus://push/openPush?$pushJo")
            }


            Logger.d(" 内部浏览器 $jo")
            Logger.d(" 内部浏览器 extra " + jo.getString("extra"))
        }


        /**
         * 首页
         */
        fun homeUri() {

        }


        private fun commonUri() {
            val intent = Intent().apply {
                `package` = "com.kid58.tiyong.characters"
                component = ComponentName(`package`,"com.babybus.plugin.getui.GetuiPushActivity")
                putExtra("data","dataValue")
                action = "android.intent.action.view"
            }

            Logger.d(intent.toUri(Intent.URI_INTENT_SCHEME).toString())
        }
    }
}