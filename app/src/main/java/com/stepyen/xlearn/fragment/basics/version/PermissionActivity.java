package com.stepyen.xlearn.fragment.basics.version;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.stepyen.xlearn.R;
import com.stepyen.xutil.tip.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * date：2019-12-18
 * author：stepyen
 * description：权限
 */
public class PermissionActivity extends AppCompatActivity {

    public static final int REQUEST_AUDIO_CODE = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        findViewById(R.id.btn_request_audio).setOnClickListener(v -> {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                toastHasAudioPermission();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_CODE);
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastHasAudioPermission();
            } else {
                boolean rationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
                if (rationale) {
                    new AlertDialog.Builder(this).setMessage("允许录音权限，才能正常使用功能")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_CODE);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ToastUtils.toast("没有录音权限，您将无法使用功能");
                                    dialog.dismiss();
                                }
                            })
                            .show()
                    ;
                } else {
                    ToastUtils.toast("没有录音权限，您将无法使用功能");
                }

            }

        }
    }

    private void toastHasAudioPermission() {
        ToastUtils.toast("有录音权限");
    }
}
