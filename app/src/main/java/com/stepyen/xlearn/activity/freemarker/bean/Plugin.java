package com.stepyen.xlearn.activity.freemarker.bean;

import com.stepyen.xlearn.activity.freemarker.Constant;

import java.util.HashMap;

/**
 * date：2020-03-19
 * author：stepyen
 * description：插件
 */
public class Plugin {

    /**
     * 模板地址
     */
    public static String TEMPLATE_PATH;

    static {
        TEMPLATE_PATH = Constant.TEMPLATES_PATH + "PluginModule";
    }

    public String name;
    public String name_lower_case;
    public String pluginName;
    public String plugin_name;
    public String plugin_name_lower_case;

    public String plugin_path;
    public String libs_path;
    public String main_path;
    public String java_path;
    public String pluginName_class_path;
    public String value_path;
    public String string_path;
    public String androidManifest_path;
    public String build_gradle_path;
    public String proguard_rules_path;


    public void setName(String name) {
        this.name = name;
        name_lower_case= name.toLowerCase();
        pluginName = "Plugin" + name;
        plugin_name = "Plugin_" + name;
        plugin_name_lower_case = plugin_name.toLowerCase();

        plugin_path = Constant.OUTPUT_PATH+plugin_name+"/";
        libs_path =plugin_path+"libs/";
        main_path = plugin_path + "src/main/";
        java_path = main_path+"java/com/babybus/plugin/"+name_lower_case+"/";
        value_path = main_path + "res/values/";

        pluginName_class_path = java_path + pluginName + ".kt";
        string_path = value_path + "strings.xml";
        androidManifest_path = main_path + "AndroidManifest.xml";
        build_gradle_path = plugin_path + "build.gradle";
        proguard_rules_path = plugin_path + "proguard-rules.pro";

    }


    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("name_lower_case", name_lower_case);
        map.put("pluginName",pluginName );
        map.put("plugin_name",plugin_name );
        map.put("plugin_name_lower_case", plugin_name_lower_case);
        return map;
    }

}
