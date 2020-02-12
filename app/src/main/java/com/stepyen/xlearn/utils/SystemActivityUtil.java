package com.stepyen.xlearn.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;

/**
 * 创建时间：2018/5/7
 * 作者：yfj
 * 描述：系统Activity
 */

public class SystemActivityUtil {

    /**
     * 系统拍照
     * @param activity
     * @param requestCode
     * @param path
     */
    public static void imageCaptureActivity(Activity activity, int requestCode, String path) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动当前应用设置页面
     */
    public static void appSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 调用拨号界面
     * @param context
     * @param phone 电话号码
     */
    public static void dialActivity(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
