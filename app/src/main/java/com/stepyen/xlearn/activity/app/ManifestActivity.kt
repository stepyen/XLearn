package com.stepyen.xlearn.activity.app

import android.content.pm.PackageManager
import com.orhanobut.logger.Logger
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import java.lang.Exception


/**
 *
 * 配置清单
 *
 * 1、meta-data
 *
 *
 *
 */
class ManifestActivity : BasePageActivity() {

    companion object {
        const val TAG = "ManifestActivityTAG"
    }

    override fun initView() {
        addView(R.layout.activity_manifest)


        getApplicationMetaInfo()
        getActivityMetaInfo()

    }


    /**
     * 获取 Application级别 MetaInfo
     *
     * 获取 value
     */
    private fun getApplicationMetaInfo() {
        var value: String? = ""

        try {

            val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            value = appInfo.metaData.getString("application.test")

        } catch (e: PackageManager.NameNotFoundException) {

        }

        Logger.d("Application MetaInfo value : $value")


    }

    /**
     * 获取 Activity 级别 MetaInfo
     *
     * 获取资源id
     */
    private fun getActivityMetaInfo() {
        var id: Int= 0

        try {

            val appInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            id = appInfo.metaData.getInt("activity.test")

        } catch (e: PackageManager.NameNotFoundException) {

        }

        Logger.d( "Activity MetaInfo id : $id")
        try {
            Logger.d( "Activity MetaInfo id具体的值为 : ${getString(id)}")
        } catch (e: Exception) {

        }


    }
}
