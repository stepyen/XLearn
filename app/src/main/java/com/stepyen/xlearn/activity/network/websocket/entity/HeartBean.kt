package com.stepyen.xlearn.activity.network.websocket.entity

import android.text.TextUtils
import com.google.gson.Gson
import com.stepyen.xlearn.activity.network.websocket.ChatConstant
import com.stepyen.xlearn.utils.GsonUtils

/**
 * date：2020-02-22
 * author：stepyen
 * description：
 *
 * 客户端
 * {"cmd":"heartBeat","data":{"userId":"1"}}
 *
 * 服务端
 * {"cmd":"heartBeatAck","data":{"userId":"1"}}
 */
class HeartBean {
    var userId: String? = ""

    companion object {
        fun buildHeartBeat(userId: String): String {
            return Gson().toJson(BaseMessageBean<HeartBean>().apply {
                cmd = ChatConstant.CMD_HEART_BEAT
                data = HeartBean().apply {
                    this.userId = userId
                }
            }
            )
        }

        fun parseHeartBeat(json: String): BaseMessageBean<HeartBean>? {

            var result: BaseMessageBean<HeartBean>?= null

            val temp = GsonUtils.parseGenericity<HeartBean>(json)
            temp?.run {
                if (TextUtils.equals(cmd, ChatConstant.CMD_HEART_BEAT_RESPONSE)) {
                    result = this
                }
            }

            return result
        }
    }


}