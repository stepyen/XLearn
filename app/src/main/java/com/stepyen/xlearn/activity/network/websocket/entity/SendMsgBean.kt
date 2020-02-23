package com.stepyen.xlearn.activity.network.websocket.entity

/**
 * date：2020-02-22
 * author：stepyen
 * description：
 *
 *   {"cmd":"sendMsg","data":{"sendId":"1","fromUserId":"1","fromUserName":"aa","fromUserHeadpic":"/aaaa.jpg","toUserId":"1","toUserName":"bb","toUserHeadpic":"/bbbb.jpg","content":"aaa","contentType":1}}
 */
class SendMsgBean {
    var id: String? = null      // 服务端生成id
    var sendId: String? = null  // 客户端生成id

    var fromUserId: String? = null
    var fromUserName: String? = null
    var fromUserHeadpic: String? = null

    var toUserId: String? = null
    var toUserName: String? = null
    var toUserHeadpic: String? = null

    var content: String? = null
    var contentType: Int = 0

    var time:String?=null       // 服务端生成时间


}