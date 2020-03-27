package com.stepyen.xlearn.activity.freemarker.bean;

import com.stepyen.xlearn.activity.freemarker.Constant;

import java.util.HashMap;

/**
 * date：2020-03-19
 * author：stepyen
 * description：插件
 */
public class APPlugin {

    /**
     * 模板地址
     */
    public static String TEMPLATE_PATH;

    public static final String README_FILE_NAME = "README";
    public static final String DEPENDS_FILE_NAME = "depends.json";
    public static final String ANDROIDMANIFEST_FILE_NAME = "AndroidManifest.json";

    static {
        TEMPLATE_PATH = Constant.TEMPLATES_PATH + "APModule";
    }

    public String name;

    public String plugin_path;
    public String aar_path;
    public String depends_path;
    public String manifest_path;

    public String readme_path;
    public String androidManifest_path;
    public String depends_file_path;


    public void setName(String name) {
        this.name = name;

        plugin_path = Constant.OUTPUT_PATH+name+"/";
        aar_path = plugin_path + "aar/";
        depends_path = plugin_path + "depends/";
        manifest_path = plugin_path + "manifest/";

        readme_path = aar_path+README_FILE_NAME;
        depends_file_path =  depends_path+DEPENDS_FILE_NAME;
        androidManifest_path =  manifest_path+ANDROIDMANIFEST_FILE_NAME;

    }


    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        return map;
    }

}
