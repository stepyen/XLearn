package com.stepyen.xlearn.activity.permission

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xlearn.fragment.basics.version.PermissionActivity
import com.stepyen.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_permissions.*

/**
 * 权限
 *
 *
 *
 */
class PermissionActivity : BasePageActivity() {

    companion object{
        const val REQUEST_AUDIO_CODE = 123

    }



    override fun initView() {
        addView(R.layout.activity_permissions)


        btn_has_audio_permission_normal.setOnClickListener {

            toastPermissionIsEnable(AudioPeimissionUtils.isHasPermission(this, android.Manifest.permission.RECORD_AUDIO))
        }


        btn_has_audio_permission_record.setOnClickListener {

            toastPermissionIsEnable(AudioPeimissionUtils.isHasRecordPermission(this@PermissionActivity))
        }


        testOriginalPermission()

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionActivity.REQUEST_AUDIO_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastPermissionIsEnable(true)
            } else {
                val rationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)
                if (rationale) {
                    AlertDialog.Builder(this).setMessage("允许录音权限，才能正常使用功能")
                            .setPositiveButton("确定") { dialog, which ->
                                ActivityCompat.requestPermissions(this@PermissionActivity, arrayOf(Manifest.permission.RECORD_AUDIO), PermissionActivity.REQUEST_AUDIO_CODE)
                                dialog.dismiss()
                            }
                            .setNegativeButton("取消") { dialog, which ->
                                ToastUtils.toast("没有录音权限，您将无法使用功能")
                                dialog.dismiss()
                            }
                            .show()
                } else {
                    ToastUtils.toast("没有录音权限，您将无法使用功能")
                }
            }
        }
    }


    private fun testOriginalPermission() {
        testOriginalPermissionBtn.setOnClickListener {
            btn_has_audio_permission_record.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    toastPermissionIsEnable(true)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), PermissionActivity.REQUEST_AUDIO_CODE)
                }

            }

        }
    }


    private fun toastPermissionIsEnable(isEnale: Boolean) {
        if (isEnale) {
            Toast.makeText(this, "权限可用", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "权限不可用", Toast.LENGTH_SHORT).show()
        }
    }

}