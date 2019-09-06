package com.stepyen.xlearn.java;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date：2019/9/6
 * author：stepyen
 * description：java 时间学习
 */
public class JavaTime {


    public static void main(String[] args) {

        // 获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("当前时间戳: "+currentTimeMillis);    // 1567737840991

        // 格式化时间
        Date date = new Date();// 当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 解析格式
        String format = sdf.format(date);// 解析
        System.out.println("格式化时间: "+format);     //  2019-09-06 10:44:00

        // 时间戳格式化
        String formatCurrentTimeMillis = sdf.format(currentTimeMillis);
        System.out.println("时间戳格式化: "+formatCurrentTimeMillis);     // 2019-09-06 10:44:00


    }



}
