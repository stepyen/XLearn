package com.stepyen.xlearn.activity.java.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * date：2020-03-19
 * author：stepyen
 * description：
 */
public class TestFreeMarker {

    public static final String PLUGIN_NAME = "Test";

    public static Configuration cfg;
    public static HashMap<String, String> map;

    public static void main(String[] args) {


        try {

            Plugin plugin = new Plugin();
            plugin.setName(PLUGIN_NAME);

            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(Plugin.TEMPLATE_PATH));


            // 删除原来的插件文件夹
            File pluginPathFile = new File( plugin.plugin_path);
            pluginPathFile.delete();

            // 创建libs路径文件夹
            File libsPathFile = new File( plugin.libs_path);
            if (!libsPathFile.exists()) {
                libsPathFile.mkdirs();
            }

            // 创建java路径文件夹
            File javaPathFile = new File( plugin.java_path);
            if (!javaPathFile.exists()) {
                javaPathFile.mkdirs();
            }
            // 创建value文件夹
            File valuePathFile = new File(plugin.value_path);
            if (!valuePathFile.exists()) {
                valuePathFile.mkdirs();
            }

            map = plugin.getMap();

            process("AndroidManifest.xml.ftl",  plugin.androidManifest_path);
            process("build.gradle.ftl",  plugin.build_gradle_path);
            process("PluginJavaClass.kt.ftl", plugin.pluginName_class_path);
            process("proguard-rules.pro.ftl",  plugin.proguard_rules_path);
            process("strings.xml.ftl",  plugin.string_path);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void process(String templateName, String outputFileNamePath) throws Exception {
        System.out.println(outputFileNamePath);
        Writer writer = null;

        Template t = cfg.getTemplate(templateName);
        File file = new File(outputFileNamePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        t.process(map, writer);

    }
}
