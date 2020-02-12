package com.stepyen.xlearn.fragment.basics.UI

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.stepyen.xlearn.base.BaseFragment
import com.xuexiang.xpage.annotation.Page


/**
 * date：2020-01-17
 * author：stepyen
 * description：
 *
 */
@Page(name = "通知栏")
class NotificationFragment : BaseFragment() {
    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kotlinInitViews() {
        super.kotlinInitViews()
    }


//    fun showNotification1() { //文章  https://blog.csdn.net/u012758088/article/details/64906445
////              https://blog.csdn.net/vipzjyno1/article/details/25248021
//        val mNotificationManager = mActivity.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val notification: Notification = NotificationCompat.Builder(mActivity)
//                .setContentTitle("标题")
//                .setContentText("内容")
//                .setSubText("内容下面的文字") //收到通知时，在状态栏上提示的文字
//                .setTicker("收到通知时，在状态栏上提示的文字") //设置通知集合的数量
//                .setNumber(10) //设置通知栏点击意图
//                .setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL, this))
//                .setWhen(System.currentTimeMillis()) //设置该通知优先级
//                .setPriority(Notification.PRIORITY_DEFAULT) //点击通知栏时，通知栏消失
//                .setAutoCancel(true) //ture，设置他为一个正在进行的通知。
//                .setOngoing(false) //设置通知方式，声音，震动，呼吸灯等效果
//                .setDefaults(Notification.DEFAULT_VIBRATE)
//                .setSmallIcon(R.mipmap.icon_dele)
//                .build()
//        mNotificationManager.notify(1123, notification)
//    }
//
//
//    private fun getDefaultIntent(flags: Int, activity: Context): PendingIntent? {
//        val intent = Intent(activity, DialogActivity::class.java)
//        return PendingIntent.getActivity(activity, 1, intent, flags)
//    }
}