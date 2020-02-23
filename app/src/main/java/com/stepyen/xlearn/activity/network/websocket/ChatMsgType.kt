package com.stepyen.xlearn.activity.network.websocket

/**
 * date：2020-02-22
 * author：stepyen
 * description：聊天信息类型
 *
 */
enum class ChatMsgType(val type :Int) {

    TEXT(1),
    VOICE(2),
    IMAGE(3),
    EXPRESSION(4)

}