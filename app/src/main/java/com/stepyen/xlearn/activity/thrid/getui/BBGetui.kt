package com.stepyen.xlearn.activity.thrid.getui

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import com.orhanobut.logger.Logger
import org.json.JSONObject
import java.net.URLDecoder

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
        fun homeIntentUri() {
            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openHome")
            }

            val homeUri = "babybus://push/openPush?${pushJo.toString()}"


            Logger.d(explicitUri("首页 intent uri ",homeUri))

        }


        /**
         * 游戏
         */
        fun gameIntentUri() {
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

            val homeUri = "babybus://push/openPush?${pushJo.toString()}"

            Logger.d(explicitUri("游戏 intent uri ",homeUri))

        }


        /**
         * 外部浏览器
         */
        fun outBrowerIntentUri() {
            val pageJo = JSONObject().apply {
                put("url", "https://www.baidu.com/")
            }


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openBrowser?${pageJo.toString()}")
            }

            val homeUri = "babybus://push/openPush?${pushJo.toString()}"

            Logger.d(explicitUri("外部浏览器 intent uri ",homeUri))

        }

        /**
         * 内部浏览器
         */
        fun innerBrowerIntentUri() {
            val pageJo = JSONObject().apply {
                put("url", "https://www.luoxia.com/")
            }


            val pushJo = JSONObject().apply {
                put("id", "123")
                put("type", "getui")
                put("uri", "babybus://push/openActivity?${pageJo.toString()}")
            }

            val homeUri = "babybus://push/openPush?${pushJo.toString()}"

            Logger.d(explicitUri("内部浏览器 intent uri ",homeUri))

        }


        /**
         * 显示uri
         */
        private fun explicitUri(type:String,pushUri:String) {
            val intent = Intent().apply {
                `package` = "com.kid58.tiyong.characters"
                component = ComponentName(`package`,"com.babybus.plugin.getui.GetuiPushActivity")
                action = Intent.ACTION_VIEW
                putExtra("GETUI_OPEN_URL",pushUri)
            }


            Logger.d("$type---->${URLDecoder.decode(intent.toUri(Intent.URI_INTENT_SCHEME).toString(), "UTF-8")}")

        }
    }
}