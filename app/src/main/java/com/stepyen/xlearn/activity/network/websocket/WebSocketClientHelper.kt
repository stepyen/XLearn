package com.stepyen.xlearn.activity.network.websocket

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

/**
 * date：2020-02-22
 * author：stepyen
 * description：
 *
 */
object WebSocketClientHelper {
    const val TAG = "WebSocketTAG"
    const val WHAT_HEART_BEAT = 1111


    private val resultListenerList: ArrayList<WebSocketClientResultListener> by lazy {
        ArrayList<WebSocketClientResultListener>()
    }

    private var webSocketClient: WebSocketClient? = null

    var heartBeatMessage: String = ""

    var heartBeatIntervalTime = 20 * 1000L


    private val handle: Handler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                msg?.run {
                    when (this.what) {
                        WHAT_HEART_BEAT -> {
                            if (!TextUtils.isEmpty(heartBeatMessage)) {

                                webSocketClient?.let {
                                    send(heartBeatMessage)
                                    handle.sendEmptyMessageDelayed(WHAT_HEART_BEAT, heartBeatIntervalTime)

                                }

                            }
                        }
                    }
                }

            }
        }
    }



    fun connect(uri: URI) {

        webSocketClient = object : WebSocketClient(uri) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                Log.d(TAG, "onOpen")
                for (resultListener in resultListenerList) {
                    resultListener.onConnectStatus(true)
                }


                handle.sendEmptyMessage(WebSocketClientHelper.WHAT_HEART_BEAT)

            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.d(TAG, "onClose")
                for (resultListener in resultListenerList) {
                    resultListener.onConnectStatus(false)
                }
            }

            override fun onMessage(message: String?) {
                Log.d(TAG, "onMessage：${message ?: ""}")
                message?.run {
                    for (resultListener in resultListenerList) {
                        resultListener.onMessage(message)
                    }
                }
            }

            override fun onError(ex: Exception?) {
                ex?.run {
                    ex.printStackTrace()
                }
                Log.d(TAG, "onError：${ex ?: ""}")
            }

        }

        webSocketClient?.connectBlocking()


    }

    fun send(msg: String) {
        Log.d(TAG, "send：${msg ?: ""}")
        if (webSocketClient?.isOpen == true) {
            webSocketClient?.send(msg)
        }

    }

    fun disconnect() {

        try {

            webSocketClient?.close()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            webSocketClient = null
        }

    }

    fun addWebSocketClientResultListener(resultListener: WebSocketClientResultListener) {

        resultListenerList.add(resultListener)

    }


}