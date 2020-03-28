package com.stepyen.xlearn.activity.thrid.msa

import android.util.Log
import com.stepyen.xlearn.App
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_msa.*

/**
 * 移动安全联盟
 *
 *
 *
 */
class MsaActivity : BasePageActivity() {


    companion object{
        const val TAG = "MsaTAG"
    }
    override fun initView() {
        addView(R.layout.activity_msa)

        getOaid()
    }

    private fun getOaid() {
        MsaHelp.getInstance().initSdk(this)
        MsaHelp.getInstance().initData(this) { isSupport, supplier ->

            Log.i(TAG, "initData: onSupport ")

            App.handler.post {

                if (isSupport) {
                    tv_ooid.text = "支持获取 oaid，为 " + supplier.oaid
                    Log.i(TAG, "run: oaid    " + supplier.oaid)
                    Log.i(TAG, "run: vaid    " + supplier.vaid)
                    Log.i(TAG, "run: aaid    " + supplier.aaid)
                } else {
                    tv_ooid.text = "不支持获取oaid"
                }

            }
        }
    }

}
