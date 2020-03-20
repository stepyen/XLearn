package com.stepyen.xlearn.activity.java.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Template;

/**
 * date：2020-03-20
 * author：stepyen
 * description：
 */
public class FreeMarkerUtils {


    public static String getFtlEndPath(String fileName) {
        return fileName + ".ftl";

    }
}
