package com.stepyen.xlearn;

/**
 * date：2019/7/31
 * author：stepyen
 * description：
 */
public class JavaTest {
    public static void main(String[] args) {
        String formatStr = "%.2f";
        double d1 = 2;
        double d2 = 2.0;
        double d3 = 2.01;
        double d4 = 2.13231;
        System.out.println(String.format(formatStr, d1));//  2.00
        System.out.println(String.format(formatStr, d2));//  2.00
        System.out.println(String.format(formatStr, d3));//  2.01
        System.out.println(String.format(formatStr, d4));//  2.13

    }
}
