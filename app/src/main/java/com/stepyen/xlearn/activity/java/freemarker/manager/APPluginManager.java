package com.stepyen.xlearn.activity.java.freemarker.manager;

import com.stepyen.xlearn.activity.java.freemarker.FileUtils;
import com.stepyen.xlearn.activity.java.freemarker.FreeMarkerUtils;
import com.stepyen.xlearn.activity.java.freemarker.bean.APPlugin;
import com.stepyen.xlearn.activity.java.freemarker.bean.Plugin;

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
 * date：2020-03-20
 * author：stepyen
 * description：
 */
public class APPluginManager {
    public Configuration cfg;
    public HashMap<String, String> map;
    public Writer writer = null;

    public void create(String pluginName) {

        try {

            APPlugin plugin = new APPlugin();
            plugin.setName(pluginName);

            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(APPlugin.TEMPLATE_PATH));


            // 删除原来的插件文件夹
            File pluginPathFile = new File( plugin.plugin_path);
            pluginPathFile.delete();


            FileUtils.mkdirs( plugin.aar_path);
            FileUtils.mkdirs( plugin.depends_path);
            FileUtils.mkdirs( plugin.manifest_path);


            map = plugin.getMap();

            process(FreeMarkerUtils.getFtlEndPath(plugin.README_FILE_NAME),  plugin.readme_path);
            process(FreeMarkerUtils.getFtlEndPath(plugin.DEPENDS_FILE_NAME),  plugin.depends_file_path);
            process(FreeMarkerUtils.getFtlEndPath(plugin.ANDROIDMANIFEST_FILE_NAME),  plugin.androidManifest_path);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void process(String templateName, String outputFileNamePath) throws Exception {
        System.out.println(outputFileNamePath);


        Template t = cfg.getTemplate(templateName);
        File file = new File(outputFileNamePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        t.process(map, writer);

    }
}
