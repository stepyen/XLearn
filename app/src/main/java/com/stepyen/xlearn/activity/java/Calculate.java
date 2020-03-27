package com.stepyen.xlearn.activity.java;

/**
 * date：2020-03-16
 * author：stepyen
 * description：计算
 */
public class Calculate {

    /**
     * 测试 || 和 && 与 | 和 & 的区别，方法的调用区别
     * <p>
     * 结论：
     * || 和 && 判断第一个，如果能直接得出结果，就不判断第二个，可以联想 （obj != null && obj.length>0）
     * <p>
     * | 和 & 两个都判断
     */
    public void testOrAndMethorCall() {
        System.out.println("----------------testOrAnd 开始--------------------");


        System.out.println("// || 和 && 与 | 和 & 的区别");

        // 使用 method1 和 method2 方法的意义在于看方法是否被调用

        System.out.println("// 第一个为false 第二个为true的情况");

        System.out.println(method1() || method2());
        System.out.println("-----------");
        System.out.println(method1() | method2());
        System.out.println("-----------");
        System.out.println(method1() && method2());
        System.out.println("-----------");
        System.out.println(method1() & method2());


        System.out.println("--------第一个为true 第二个为false的情况------------");

        System.out.println(method2() || method1());
        System.out.println("-----------");
        System.out.println(method2() | method1());
        System.out.println("-----------");
        System.out.println(method2() && method1());
        System.out.println("-----------");
        System.out.println(method2() & method1());


        System.out.println("----------------testOrAnd 结束--------------------");
    }

    private boolean method1() {
        System.out.println("method1");
        return false;
    }

    private boolean method2() {
        System.out.println("method2");
        return true;
    }

    /**
     * 测试 | 和 & 数字运算
     */
    public void testOrAndNumber() {

        int a = 0x1;
        int b = 0x2;
        int all = 3;

        System.out.println(a + " | " + b + " = " + (a | b));
        System.out.println(a + " & " + all + " = " + (a & all));


    }


}
