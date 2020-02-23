package com.stepyen.xlearn.activity.network.websocket

/**
 * date：2020-02-22
 * author：stepyen
 * description：
 *
 */
interface WebSocketClientResultListener {

    fun onMessage(message: String?)

    fun onConnectStatus(status:Boolean)
}