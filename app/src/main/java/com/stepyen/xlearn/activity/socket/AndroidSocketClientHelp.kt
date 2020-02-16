package com.stepyen.xlearn.activity.socket

import android.annotation.SuppressLint
import com.orhanobut.logger.Logger
import com.vilyever.socketclient.SocketClient
import com.vilyever.socketclient.SocketResponsePacket

/**
 * date：2020-02-15
 * author：stepyen
 * description：
 */
class AndroidSocketClientHelp(config: AndroidSocketClientConfig) {
    companion object{
        const val TAG = "AndroidSocketClientHelpTAG"
    }
    private var mClient: SocketClient? = null
    var socketListen: SocketListen? = null

    init {
        init(config)
    }

    private fun init(config: AndroidSocketClientConfig) {
        mClient = SocketClient(config.ip,config.port).apply {
            charsetName =config.charsetName; // 设置发送和接收String消息的默认编码
            connectionTimeout = config.connectionTimeout//连接超时时间
            remoteNoReplyAliveTimeout = config.remoteNoReplyAliveTimeout; //设置远程端多长时间内没有消息发送到本地就自动断开连接，若值小于0则不自动断开
            setHeartBeatMessageString(config.heartBeatMessage)// 发送心跳数据
            heartBeatInterval = config.heartBeatInterval; //间隔多少秒发送一次心跳包


            registerSocketDelegate(object : SocketClient.SocketDelegate {
                @SuppressLint("LongLogTag")
                override fun onConnected(client: SocketClient) {
                    socketListen?.connect(true)

                }

                override fun onDisconnected(client: SocketClient) {
                    socketListen?.connect(false)
                }

                override fun onResponse(client: SocketClient, responsePacket: SocketResponsePacket) {
                    socketListen?.let {
                        it.response(responsePacket.message)
                    }

                    Logger.d(responsePacket.message)
                }
            })

        }
    }

    fun connect() {
        mClient?.connect()
    }

    fun disConnect() {
        mClient?.disconnect()
    }

    fun getState() {
        mClient?.state
    }

    fun send(msg: String) {
        mClient?.send(msg)
    }
}

