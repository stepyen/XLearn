package com.stepyen.xlearn.activity.network.websocket

/**
 * date：2020-02-22
 * author：stepyen
 * description：聊天常量
 *
 */
class ChatConstant {
    companion object{
        // 心跳包发送
        const val CMD_HEART_BEAT = "heartBeat"
        // 心跳包回复
        const val CMD_HEART_BEAT_RESPONSE = "heartBeatAck"
        // 发送信息
        const val CMD_MSG_SEND = "sendMsg"
        // 发送信息回复
        const val CMD_MSG_SEND_RESPONSE = "sendMsgAck"
        // 接收信息
        const val CMD_MSG_RECEIVE = "revcMsg"
        // 在线用户列表
        const val CMD_GET_ONLE_USER_LIST = "getOnleUserList"

    }
}