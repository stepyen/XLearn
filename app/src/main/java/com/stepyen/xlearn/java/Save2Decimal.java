package com.stepyen.xlearn.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * date：2019/9/6
 * author：stepyen
 * description：金额保留两位小数
 *
 *
 *  运行后，只有第三种能正确保留两位小数
 */
public class Save2Decimal {
    public static void main(String[] args) {
        double value1 = 0.01;
        double value2 = 0.0123;
        double value3 = 1111330.1;
        double value4 = 1111330;

        System.out.println("----------------1------------");
        test1(value1);
        test1(value2);
        test1(value3);
        test1(value4);

        System.out.println("----------------2------------");
        test2(value1);
        test2(value2);
        test2(value3);
        test2(value4);

        System.out.println("----------------3------------");
        test3(value1);
        test3(value2);
        test3(value3);
        test3(value4);

        System.out.println("----------------4------------");
        test4(value1);
        test4(value2);
        test4(value3);
        test4(value4);
    }

    private static void test1(double doule) {
        BigDecimal bg = new BigDecimal(doule);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }

    private static void test2(double doule) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(doule));
    }

    private static void test3(double doule) {
        System.out.println(String.format("%.2f", doule));
    }

    private static void test4(double doule) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);  // 默认是true，会按照千分位进行输出，如100,000
        System.out.println(nf.format(doule));
    }
}
