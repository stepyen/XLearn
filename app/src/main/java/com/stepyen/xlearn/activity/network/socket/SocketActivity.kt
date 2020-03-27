package com.stepyen.xlearn.activity.network.socket

import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.extend.trim
import com.stepyen.common.BasePageActivity
import com.stepyen.xui.utils.DensityUtils
import kotlinx.android.synthetic.main.activity_socket.*
import org.json.JSONException
import org.json.JSONObject

/**
 * date：2020-02-15
 * author：stepyen
 * description：socket 学习
 *
 */
class SocketActivity : BasePageActivity() {

    private var socketHelp: AndroidSocketClientHelp? = null

    override fun initData() {

    }

    override fun initView() {
        addView(R.layout.activity_socket)

        libAndroidSocketClientTest()

    }


    /**
     * 第三方库 AndroidSocketClient 使用学习
     * github https://github.com/vilyever/AndroidSocketClient
     * 版本 1.4.1（使用过这个版本）
     */
    private fun libAndroidSocketClientTest() {

        val ip = "139.9.231.20"
        val port = 7777

        et_socket_ip.setText(ip)
        et_socket_port.setText(port.toString())


        btn_socket_connect.setOnClickListener {
            initSocket()
            socketHelp?.connect()
        }

        btn_socket_send.setOnClickListener {
            val msg = et_socket_send_message.trim()
            socketHelp?.send(msg)
            addChatTv(false, msg)
        }
        btn_socket_clear_chat.setOnClickListener {
            ll_socket_chat.removeAllViews()
        }
    }

    private fun initSocket() {


        val config = AndroidSocketClientConfig(et_socket_ip.trim(), et_socket_port.trim().toInt()).apply {
            heartBeatMessage = getHeartMessage(et_socket_userId.trim())

            Logger.d(heartBeatMessage)
        }

        socketHelp = AndroidSocketClientHelp(config)
        socketHelp?.socketListen = object : SocketListen {
            override fun connect(status: Boolean) {
                if (status) {
                    tv_socket_connect_status.text = "连接状态：成功"
                } else {
                    tv_socket_connect_status.text = "连接状态：失败"
                }
            }

            override fun response(msg: String) {
                addChatTv(true, msg)
            }

        }
    }

    private fun getHeartMessage(userId: String): String {

        var result = ""
        try {
            result = JSONObject().apply {
                put("cmd", "heartBeat")
                put("data", JSONObject().apply {

                    put("userId", userId)

                })
            }.toString()
        } catch (e: JSONException) {

        }

        Log.d("haha",result)

       return result
    }

    private fun addChatTv(isResponse: Boolean, msg: String) {
        val tv = TextView(this).apply {
            text = msg
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(20f))

            gravity = if (isResponse) {
                Gravity.LEFT or Gravity.CENTER_VERTICAL
            } else {
                Gravity.RIGHT or Gravity.CENTER_VERTICAL
            }

        }

        ll_socket_chat.addView(tv)

    }
}