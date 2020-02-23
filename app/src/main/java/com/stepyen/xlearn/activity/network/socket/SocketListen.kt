package com.stepyen.xlearn.activity.network.socket

/**
 * date：2020-02-15
 * author：stepyen
 * description：
 *
 */
interface SocketListen {
    fun connect(status:Boolean)
    fun response(msg:String)
}