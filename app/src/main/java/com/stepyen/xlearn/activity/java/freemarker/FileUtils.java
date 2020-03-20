package com.stepyen.xlearn.activity.java.freemarker;

import java.io.File;

/**
 * date：2020-03-20
 * author：stepyen
 * description：
 */
public class FileUtils {

    public static void mkdirs(String path) {
        File aarPathFile = new File( path);
        if (!aarPathFile.exists()) {
            aarPathFile.mkdirs();
        }
    }
}
