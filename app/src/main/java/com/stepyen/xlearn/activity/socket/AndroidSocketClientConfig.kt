package com.stepyen.xlearn.activity.socket

/**
 * date：2020-02-15
 * author：stepyen
 * description：
 *
 */
class AndroidSocketClientConfig(var ip: String, var port: Int) {
    var charsetName = "UTF-8"
    var connectionTimeout = 15*1000
    var remoteNoReplyAliveTimeout = -1L
    var heartBeatMessage = ""
    var heartBeatInterval = 10 * 1000L



}