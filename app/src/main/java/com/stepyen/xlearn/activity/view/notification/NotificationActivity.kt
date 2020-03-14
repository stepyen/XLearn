package com.stepyen.xlearn.activity.view.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.stepyen.xlearn.MainActivity
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import java.lang.Exception


/**
 * 通知栏
 *
 *
 */
class NotificationActivity : BasePageActivity() {

    companion object {
        //        渠道组
        const val CHANNEL_GRUOP_ID = "CHANNEL_GRUOP_ID"
        const val CHANNEL_GRUOP_1 = "CHANNEL_GRUOP_1"
        const val CHANNEL_GRUOP_2 = "CHANNEL_GRUOP_2"

        // 消息
        const val CHANNEL_ID_MESSAGE = "CHANNEL_ID_MESSAGE"

        // 重要性
        const val CHANNEL_ID_IMPORTANCE_MIN = "CHANNEL_ID_IMPORTANCE_MIN"
        const val CHANNEL_ID_IMPORTANCE_LOW = "CHANNEL_ID_IMPORTANCE_LOW"
        const val CHANNEL_ID_IMPORTANCE_DEFAULT = "CHANNEL_ID_IMPORTANCE_DEFAULT"
        const val CHANNEL_ID_IMPORTANCE_HIGH = "CHANNEL_ID_IMPORTANCE_HIGH"
        const val CHANNEL_ID_IMPORTANCE_MAX = "CHANNEL_ID_IMPORTANCE_MAX"

    }


    var messageChannel: NotificationChannel? = null
    private var importance = NotificationManager.IMPORTANCE_DEFAULT

    var id = 1

    override fun initView() {
        addView(R.layout.activity_notification)

        addTag("渠道")

        addButton("创建渠道组", View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val groupId = CHANNEL_GRUOP_ID
                val groupName = "渠道组"
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannelGroup(NotificationChannelGroup(groupId, groupName))

                createNotificationChannel(CHANNEL_GRUOP_1, "渠道组1", "渠道组1描述", NotificationManager.IMPORTANCE_DEFAULT, CHANNEL_GRUOP_ID)
                createNotificationChannel(CHANNEL_GRUOP_2, "渠道组2", "渠道组2描述", NotificationManager.IMPORTANCE_DEFAULT, CHANNEL_GRUOP_ID)
            }

        })


        addButton("创建消息渠道", View.OnClickListener {

            messageChannel = createNotificationChannel(CHANNEL_ID_MESSAGE, "消息", "接收重要的消息", NotificationManager.IMPORTANCE_DEFAULT)


        })
        addButton("删除消息渠道", View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.deleteNotificationChannel(CHANNEL_ID_MESSAGE)

            }

        })



        addButton("设置消息渠道", View.OnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                try {
                    val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                        putExtra(Settings.EXTRA_CHANNEL_ID, messageChannel?.id)
                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    toast("未找到渠道")
                }


            } else {
                toast("8.0以下，没有通知渠道，无法跳转")
            }

        })

        addButton("通知设置", View.OnClickListener {


            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)

            }
            startActivity(intent)


        })


        addTag("点击通知栏动作")

        addButton("通知跳转到首页", View.OnClickListener {

            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：无所不能")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })

        addButton("通知跳转到常规页面", View.OnClickListener {

            // NotificationTestParentActivity 的父布局是 MainActivity
            val resultIntent = Intent(this, NotificationTestParentActivity::class.java)

            val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {

                addNextIntentWithParentStack(resultIntent)
                getPendingIntent(id++, PendingIntent.FLAG_UPDATE_CURRENT)

            }


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：常规页面")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })




        testPendingIntent()

        testImportant()

        testStyle()




        addTag("应用图标数量")

        addButton("显示图标数量", View.OnClickListener {

            // 发送通知
            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("标题")
                    .setContentText("内容：显示图标数量")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setNumber(33)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })

        addTag("通知栏接收页面测试")


        addButton("接收页面是 Standard", View.OnClickListener {

            val intent = Intent(this, NoificationReceiveStandardActivitity::class.java).apply {

            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：接收页面是 Standard")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })



        addButton("接收页面是 Standard，Intent 添加 Intent.FLAG_ACTIVITY_NEW_TASK", View.OnClickListener {

            val intent = Intent(this, NoificationReceiveStandardActivitity::class.java).apply {

            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：接收页面是 Standard")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })

        addButton("接收页面是 SingleTask", View.OnClickListener {

            val intent = Intent(this, NoificationReceiveSingleTaskActivitity::class.java).apply {

            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：接收页面是 SingleTask")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })

        addButton("接收页面是 SingleTask，Intent 添加 Intent.FLAG_ACTIVITY_NEW_TASK", View.OnClickListener {

            val intent = Intent(this, NoificationReceiveSingleTaskActivitity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：接收页面是 SingleTask")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })



        addTag("通知栏操作")

        addButton("清除所有通知", View.OnClickListener {

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()

        })


    }


    /**
     * 测试重要性
     */
    private fun testImportant(channelId: String) {


        var priority = NotificationCompat.PRIORITY_DEFAULT
        var importance = NotificationManager.IMPORTANCE_DEFAULT
        var name = ""
        var descriptor = ""

        when (channelId) {
            CHANNEL_ID_IMPORTANCE_MIN -> {
                priority = NotificationCompat.PRIORITY_MIN
                importance = NotificationManager.IMPORTANCE_MIN
                name = "重要性及低"
                descriptor = "重要性及低"
            }

            CHANNEL_ID_IMPORTANCE_LOW -> {
                priority = NotificationCompat.PRIORITY_LOW
                importance = NotificationManager.IMPORTANCE_LOW
                name = "重要性低"
                descriptor = "重要性低"
            }
            CHANNEL_ID_IMPORTANCE_DEFAULT -> {
                priority = NotificationCompat.PRIORITY_DEFAULT
                importance = NotificationManager.IMPORTANCE_DEFAULT
                name = "重要性默认"
                descriptor = "重要性默认"
            }

            CHANNEL_ID_IMPORTANCE_HIGH -> {
                priority = NotificationCompat.PRIORITY_HIGH
                importance = NotificationManager.IMPORTANCE_HIGH
                name = "重要性高"
                descriptor = "重要性高"
            }
            CHANNEL_ID_IMPORTANCE_MAX -> {
                priority = NotificationCompat.PRIORITY_MAX
                importance = NotificationManager.IMPORTANCE_MAX
                name = "重要性及高"
                descriptor = "重要性及高"
            }
            else -> {
                priority = NotificationCompat.PRIORITY_DEFAULT
                importance = NotificationManager.IMPORTANCE_DEFAULT
                name = "重要性默认"
                descriptor = "重要性默认"
            }
        }

        // 创建渠道
        createNotificationChannel(channelId, name, descriptor, importance)

        // 发送通知
        var builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.icon_dog)
                .setContentTitle("标题")
                .setContentText("内容：$name")
                .setPriority(priority)
        val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id++, builder.build())
    }


    /**
     * 创建通知渠道
     */
    private fun createNotificationChannel(channelId: String, channelName: String, channelDescription: String, importance: Int, gruopId: String? = ""): NotificationChannel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
                setShowBadge(true)  // 显示角标数量
                if (!TextUtils.isEmpty(gruopId)) {
                    group = gruopId
                }
            }

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            return channel
        }

        return null
    }


    private fun simpleNotification(content: String, pi: PendingIntent) {


        // 发送通知
        var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                .setSmallIcon(R.drawable.icon_dog)
                .setContentTitle("标题")
                .setContentText("内容：$content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id++, builder.build())

    }

    /**
     * 测试重要性
     */
    private fun testImportant() {
        addTag("重要性")

        addButton("重要性及低", View.OnClickListener {
            testImportant(CHANNEL_ID_IMPORTANCE_MIN)
        })
        addButton("重要性低", View.OnClickListener {
            testImportant(CHANNEL_ID_IMPORTANCE_LOW)
        })
        addButton("重要性默认", View.OnClickListener {
            testImportant(CHANNEL_ID_IMPORTANCE_DEFAULT)
        })
        addButton("重要性高", View.OnClickListener {
            testImportant(CHANNEL_ID_IMPORTANCE_HIGH)
        })
        addButton("重要性及高", View.OnClickListener {
            testImportant(CHANNEL_ID_IMPORTANCE_MAX)
        })
    }

    /**
     * 测试 PendingIntent
     */
    private fun testPendingIntent() {
        addTag("测试 PendingIntent")

        addButton("默认情况", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            simpleNotification("PendingIntent 默认情况",pendingIntent)
        })
        addButton("FLAG_ONE_SHOT", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            simpleNotification("PendingIntent FLAG_ONE_SHOT",pendingIntent)
        })

        addButton("FLAG_NO_CREATE", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_NO_CREATE)

            simpleNotification("PendingIntent FLAG_NO_CREATE",pendingIntent)
        })
        addButton("FLAG_CANCEL_CURRENT", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            simpleNotification("PendingIntent FLAG_CANCEL_CURRENT",pendingIntent)
        })

        addButton("FLAG_UPDATE_CURRENT", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            id = 1000
            simpleNotification("PendingIntent FLAG_UPDATE_CURRENT",pendingIntent)
        })
        addButton("FLAG_IMMUTABLE", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


            simpleNotification("PendingIntent FLAG_IMMUTABLE",pendingIntent)
        })

    }


    /**
     * 测试 样式
     */
    private fun testStyle() {
        addTag("通知样式")

        addButton("震动", View.OnClickListener {

            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：震动")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setTicker("收到来自远方的消息")
                    .setVibrate(longArrayOf(0L, 3000L, 1000L, 3000L))
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })


        addButton("铃声", View.OnClickListener {

            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：铃声")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setTicker("收到来自远方的消息")
                    .setDefaults(Notification.DEFAULT_ALL)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })
        addButton("铃声、震动", View.OnClickListener {

            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：铃声、震动")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setTicker("收到来自远方的消息")
                    .setVibrate(longArrayOf(0L, 3000L, 1000L, 30000L))
                    .setDefaults(Notification.DEFAULT_ALL)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })



        addButton("简单的通知", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon_tiger))
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：测试铃声、震动")
                    .setSubText("这是摘要")
                    .setContentInfo("显示指定文本")
                    .setNumber(33)
                    .setWhen(System.currentTimeMillis())
//                    .setOngoing(true)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setTicker("收到来自远方的消息")
                    .setAutoCancel(true)
//                   .setVibrate(longArrayOf(0L,1000L,0,1000L))
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())

        })

        addButton("多文本通知", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


            val style = NotificationCompat.BigTextStyle()
                    .bigText("这里可以写很长的一段话，不信你试试。这里可以写很长的一段话，不信你试试。这里可以写很长的一段话，不信你试试。")
                    .setBigContentTitle("点击后的标题")
                    .setSummaryText("这是摘要")

            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：多文本通知")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setStyle(style)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })

        addButton("扩展布局", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


            val events = arrayOf("第一", "第二", "第三", "第四", "第五", "第六", "第七")

            val style = NotificationCompat.InboxStyle()
                    .setBigContentTitle("展开后的标题")
                    .setSummaryText("这是摘要")
            for (event in events) {
                style.addLine(event)
            }

            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：扩展布局")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setStyle(style)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })

        addButton("大图样式", View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_tiger)

            val style = NotificationCompat.BigPictureStyle()
                    .setBigContentTitle("展开后的标题")
                    .setSummaryText("这是摘要")
                    .bigPicture(bitmap)


            var builder = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setLargeIcon(bitmap)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容：大图样式")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setStyle(style)

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(id++, builder.build())


        })

        addButton("进度条", View.OnClickListener {
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = NotificationCompat.Builder(this)
                    .setContentTitle("这是标题")
                    .setContentText("这是正文")
                    .setSmallIcon(R.drawable.icon_dog)

            Thread(Runnable {
                var progress = 0
                while (progress <= 100) {
                    builder.setProgress(100, progress, false)
                    mNotificationManager.notify(1000, builder.build())
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    progress += 5
                }
                builder.setContentText("下载完成").setProgress(0, 0, false)
                mNotificationManager.notify(1000, builder.build())
            }).start()

        })

        addButton("自定义布局", View.OnClickListener {
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationLayout = RemoteViews(packageName, R.layout.view_custom_notification)
            val notificationLayoutExpanded = RemoteViews(packageName, R.layout.view_custom_notification_large)

            val customNotification = NotificationCompat.Builder(this, CHANNEL_ID_MESSAGE)
                    .setSmallIcon(R.drawable.icon_dog)
                    .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(notificationLayout)
                    .setCustomBigContentView(notificationLayoutExpanded)
                    .build()
            mNotificationManager.notify(id++, customNotification)

        })


    }
}