package com.stepyen.xlearn.activity.freemarker;

import com.stepyen.xlearn.activity.freemarker.manager.APPluginManager;

/**
 * date：2020-03-19
 * author：stepyen
 * description：
 */
public class TestFreeMarker {


    public static void main(String[] args) {

        // 创建 BB module
//        new BBPluginManager().create("Test");

        // 创建 AP module
        new APPluginManager().create("Test");

    }
}
