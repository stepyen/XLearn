package com.stepyen.xlearn.activity.module.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.base.BasePageActivity

/**
 *
 *
 *  服务
 *
 */
class ServiceActivity : BasePageActivity() {
    private lateinit var mServiceConnection: ServiceConnection
    private lateinit var mTestBindService: TestBindService

    companion object {
        const val TAG = "ServiceActivityTAG"
    }

    override fun initView() {


        addTextView("开启当前 app 的服务")

        addButton("开启服务", View.OnClickListener {
            val intent = Intent(this@ServiceActivity, TestStartService::class.java)
            startService(intent)
        })


        addButton("关闭服务", View.OnClickListener {
            val intent = Intent(this@ServiceActivity, TestStartService::class.java)
            stopService(intent)
        })



        addButton("绑定服务", View.OnClickListener {

            mServiceConnection = object : ServiceConnection {
                override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                    Log.d(TAG, "onServiceConnected:")
                    val binder = iBinder as TestBindService.MyBind
                    mTestBindService = binder.service
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    Log.d(TAG, "onServiceDisconnected:")
                }
            }

            val intent = Intent(this@ServiceActivity, TestBindService::class.java)
            bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE)
        })

        addButton("解绑服务", View.OnClickListener {
            unbindService(mServiceConnection)
        })

        addTextView("开启TestDemo app 的服务")

        addButton("用 ContentProvider 测试连通性",View.OnClickListener {
            val pingUri = "content://com.stepyen.testdemo.TestPingContentProvider/ping"
            val cursor = contentResolver.query(Uri.parse(pingUri),null,null,null,null)

            runOnUiThread(){
                if (cursor == null) {
                    Logger.d("未连通")
                    startActivity(Intent().apply {
                        setClassName("com.stepyen.testdemo","com.stepyen.testdemo.sharespace.NoDisplayActivity")
                    })

                }else{
                    Logger.d("已连通")

                }
            }

        })

        val packetName = "com.stepyen.testdemo"
        val serviceName = "com.stepyen.testdemo.sharespace.ShareSpaceService"

        addButton("显式开启服务", View.OnClickListener {

            val intent = Intent().apply {
                `package` = packetName
//                action = "com.stepyen.testdemo.service.TestStartService"
                setClassName(packetName,serviceName)
            }

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }else{
                startService(intent)
            }

        })
        addButton("隐式开启服务", View.OnClickListener {

            val intent = Intent().apply {
                `package` = packetName
                action = serviceName
            }

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }else{
                startService(intent)
            }

        })





        addButton("关闭服务", View.OnClickListener {

            val intent = Intent().apply {
                `package` = packageName
                action = "com.stepyen.testdemo.service.TestStartService"
            }
            stopService(intent)

        })

    }


}
