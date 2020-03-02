package com.stepyen.xlearn.activity.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioRecord;
import android.os.Build;

import com.stepyen.xlearn.utils.L;

import java.lang.reflect.Method;

/**
 * date：2020-02-28
 * author：stepyen
 * description： 录音权限判断
 *
 *
 * 6.0以下手机，安全中心会控制权限
 * 当 安全中心控制权限为false时，API判断是true，实际的功能使用是不可用的。
 *
 */
public class AudioPeimissionUtils {

    /**
     * 是否有录音权限
     * @param context
     * @return
     */
    public static boolean isHasRecordPermission(Context context) {

        if (Build.VERSION.SDK_INT < 23) {
            boolean enable = isRecordEnable();
            L.d("检查："+enable+"      线程："+Thread.currentThread().getId());
            return enable;
        } else {
            return isHasPermission(context, Manifest.permission.RECORD_AUDIO);
        }
    }

    /**
     * 录音是否可用
     * @return
     */
    private static boolean isRecordEnable() {
        int sampleRateInHz = 16000;
        int bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, 16, 2);
        AudioRecord audioRecord = new AudioRecord(1, sampleRateInHz, 16, 2, bufferSizeInBytes);

        try {
            audioRecord.startRecording();
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
            L.e( "判断录音是否可用时，发生错误 ："+var4.getMessage());
        }

        L.e( "判断录音是否可用时，状态 ："+audioRecord.getRecordingState());

        if (audioRecord.getRecordingState() != 3) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
            return false;
        } else {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
            return true;
        }
    }

    /**
     * 权限判断
     * @param context
     * @param permission
     * @return
     */
    public static boolean isHasPermission(Context context, String permission) {

        try {
            Class<?> contextCompat = null;
            try {
                contextCompat = Class.forName("android.support.v4.content.ContextCompat");
            } catch (Exception e) {
                //ignored
            }

            if (contextCompat == null) {
                try {
                    contextCompat = Class.forName("androidx.core.content.ContextCompat");
                } catch (Exception e) {
                    //ignored
                }
            }

            if (contextCompat == null) {
                return true;
            }

            Method checkSelfPermissionMethod = contextCompat.getMethod("checkSelfPermission", Context.class, String.class);
            int result = (int) checkSelfPermissionMethod.invoke(null, new Object[]{context, permission});
            if (result != PackageManager.PERMISSION_GRANTED) {
                L.d("You can fix this by adding the following to your AndroidManifest.xml file:\n"
                        + "<uses-permission android:name=\"" + permission + "\" />");
                return false;
            }

            return true;
        } catch (Exception e) {
            L.d(e.toString());
            return true;
        }
    }


}
