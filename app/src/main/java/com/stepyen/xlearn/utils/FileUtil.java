package com.stepyen.xlearn.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
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



    public static void writeToFile(String parentPath, String path, byte[] data) {
        try {

            File file = new File(parentPath, path);

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
     *
     * @param context
     * @param file
     */
    public static void refreshSystemImageDataBase(Context context, File file) {

        if (context == null || file == null || file.isDirectory()) {
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




    public static void saveImageWithBitmap( Context context,Bitmap bitmap) {

        // 创建文件
        File appDir = new File(context.getCacheDir(),"image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);

        // 保存文件
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshSystemImageDataBase(context,file);

//        // 文件插入系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
////        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));

    }


}
