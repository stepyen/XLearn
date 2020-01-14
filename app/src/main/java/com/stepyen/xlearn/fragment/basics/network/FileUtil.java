package com.stepyen.xlearn.fragment.basics.network;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.stepyen.xlearn.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * date：2020-01-14
 * author：stepyen
 * description：
 */
public class FileUtil {

    public static void writeToFilesDir(String path, byte[] data) {
        writeToFile(App.get().getExternalCacheDir(), path, data);
    }

    private static void writeToFile(File parent, String path, byte[] data) {
        try {

            File file = new File(parent, path);

            if (!file.exists()) {
                File p = file.getParentFile();
                if (p != null && !p.exists()) {
                    p.mkdirs();
                }
                file.createNewFile();
            }
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 刷新 系统图片库
     * @param context
     * @param file
     */
    public static void refreshSystemImageDataBase(Context context, File file) {

        if (context == null || file == null|| file.isDirectory()) {
            return;
        }

        // 文件插入系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

    }
}
