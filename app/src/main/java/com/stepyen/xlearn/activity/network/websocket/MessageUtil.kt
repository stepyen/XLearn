package com.stepyen.xlearn.activity.network.websocket

import com.stepyen.xlearn.activity.network.websocket.entity.BaseMessageBean
import com.stepyen.xlearn.activity.network.websocket.entity.HeartBean
import com.stepyen.xlearn.activity.network.websocket.entity.SendMsgBean
import com.stepyen.xlearn.activity.network.websocket.entity.SendMsgResponseBean
import com.stepyen.xlearn.utils.GsonUtils
import org.json.JSONObject

/**
 * date：2020-02-22
 * author：stepyen
 * description：
 *
 */
class MessageUtil {

    companion object{

        fun parseReponse(json:String):Pair<String, BaseMessageBean<out Any>?>{
            var jo = JSONObject(json)
            var cmd = jo.optString("cmd")?:""
            val bean =  when (cmd) {
                ChatConstant.CMD_HEART_BEAT_RESPONSE-> GsonUtils.parseGenericity<HeartBean>(json)
                ChatConstant.CMD_MSG_SEND_RESPONSE-> GsonUtils.parseGenericity<SendMsgResponseBean>(json)
                ChatConstant.CMD_MSG_RECEIVE-> GsonUtils.parseGenericity<SendMsgBean>(json)
                else->null
            }

            return Pair(cmd,bean)

        }


    }
}