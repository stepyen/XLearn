package com.stepyen.xlearn.activity.network.websocket

import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.stepyen.commonsdk.extend.trim

import com.stepyen.xlearn.R
import com.stepyen.xlearn.activity.network.websocket.entity.BaseMessageBean
import com.stepyen.xlearn.activity.network.websocket.entity.HeartBean
import com.stepyen.xlearn.activity.network.websocket.entity.SendMsgBean
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xui.utils.DensityUtils
import com.stepyen.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_web_socket.*
import java.net.URI

class WebSocketActivity : BasePageActivity() {

    var heartBeatTimes = 0


    override fun initData() {
        super.initData()
    }


    override fun initView() {
        addView(R.layout.activity_web_socket)

        initWebSocket()

    }


    private fun initWebSocket() {
        val uri = "ws://139.9.231.20:7777/websocket"
        et_socket_uri.setText(uri)

        btn_socket_connect.setOnClickListener {
            val uri = et_socket_uri.trim()

            WebSocketClientHelper.addWebSocketClientResultListener(object : WebSocketClientResultListener {
                override fun onConnectStatus(status: Boolean) {
                    runOnUiThread {
                        tv_socket_connect_status.text = "连接状态：${if (status) "成功" else "失败"}"
                    }
                }

                override fun onMessage(message: String?) {

                    runOnUiThread {
                        message?.run {

                            val result = MessageUtil.parseReponse(this)

                            when (result.first) {
                                ChatConstant.CMD_HEART_BEAT_RESPONSE -> {

                                    val time = if (result.second == null) "无返回" else ++heartBeatTimes
                                    tv_socket_heart_beat_times.text = "心跳次数：${time}"

                                }
                                ChatConstant.CMD_MSG_SEND_RESPONSE -> {

                                    ToastUtils.toast("发送成功")
//                                    result.second?.let {
//                                        val bean = it as BaseMessageBean<SendMsgBean>
//
//                                        addChatTv(false, bean.data?.content ?: "空")
//
//                                    }

                                }
                                ChatConstant.CMD_MSG_RECEIVE -> {
//                                    addChatTv(true, this)
                                }
                                else -> ""
                            }


                        }
                    }

                }
            })

            WebSocketClientHelper.heartBeatMessage = HeartBean.buildHeartBeat(et_socket_userId.trim())
            WebSocketClientHelper.connect(URI.create(uri))


        }

        btn_socket_send.setOnClickListener {

            val msgBean = BaseMessageBean<SendMsgBean>().apply {
                cmd = ChatConstant.CMD_MSG_SEND
                data = SendMsgBean().apply {
                    sendId = "1"
                    fromUserId = et_socket_userId.trim()
                    fromUserName = ""
                    fromUserHeadpic = ""
                    toUserId = et_socket_to_userId.trim()
                    toUserName = ""
                    toUserHeadpic = ""
                    content = et_socket_send_message.trim()
                    contentType = ChatMsgType.TEXT.type
                }
            }

            val json = Gson().toJson(msgBean)

            WebSocketClientHelper.send(json)

            addChatTv(false, et_socket_send_message.trim())
        }
        btn_socket_clear_chat.setOnClickListener {
            ll_socket_chat.removeAllViews()
        }
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
