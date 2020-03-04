package com.stepyen.xlearn.activity.module.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.fragment.basics.service.TestBindService
import com.stepyen.xlearn.fragment.basics.service.TestStartService
import kotlinx.android.synthetic.main.activity_service.*

/**
 *
 *
 *  服务
 *
 */
class ServiceActivity : BasePageActivity() {
    private var mServiceConnection: ServiceConnection? = null
    private var mTestBindService: TestBindService? = null

    companion object{
        const val TAG = "ServiceTAG"
    }

    override fun initView() {
        addView(R.layout.activity_service)

        btn_start_service.setOnClickListener {
            val intent = Intent(this@ServiceActivity, TestStartService::class.java)
            startService(intent)
        }
        btn_stop_service.setOnClickListener {
            val intent = Intent(this@ServiceActivity, TestStartService::class.java)
            stopService(intent)
        }
        btn_bind_service.setOnClickListener {
            if (mServiceConnection == null) {
                mServiceConnection = object : ServiceConnection {
                    override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                        Log.d(TAG, "onServiceConnected:")
                        val binder = iBinder as TestBindService.MyBind
                        mTestBindService = binder.service
                    }

                    override fun onServiceDisconnected(componentName: ComponentName) {
                        mTestBindService = null
                        Log.d(TAG, "onServiceDisconnected:")
                    }
                }
            }
            val intent = Intent(this@ServiceActivity, TestBindService::class.java)
            bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE)
        }

        btn_unbind_service.setOnClickListener {
            unbindService(mServiceConnection)
        }

    }
}
