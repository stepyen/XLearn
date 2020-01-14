package com.stepyen.xlearn.fragment.basics.network;

import com.stepyen.xlearn.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
}
