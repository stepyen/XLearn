package com.stepyen.xlearn.activity.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * date：2019/7/31
 * author：stepyen
 * description：java 测试总入口
 */
public class JavaTest {


    public static void main(String[] args) {


        JavaTest test = new JavaTest();

//        test.testOrAnd();
        test.testTime();
        test.testSave2Decimal();
        test.testExecutor();


    }


    /**
     * 测试时间
     */
    public void testTime() {
        System.out.println("----------------testTime 开始--------------------");


        // 获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("当前时间戳: " + currentTimeMillis);    // 1567737840991

        // 格式化时间
        Date date = new Date();// 当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 解析格式
        String format = sdf.format(date);// 解析
        System.out.println("格式化时间: " + format);     //  2019-09-06 10:44:00

        // 时间戳格式化
        String formatCurrentTimeMillis = sdf.format(currentTimeMillis);
        System.out.println("时间戳格式化: " + formatCurrentTimeMillis);     // 2019-09-06 10:44:00

        System.out.println("----------------testTime 结束--------------------");
    }

    /**
     * 金额保留两位小数
     * <p>
     * 运行后，只有第三种能正确保留两位小数
     */
    public void testSave2Decimal() {
        System.out.println("---------------- 开始--------------------");

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


        System.out.println("---------------- 结束--------------------");
    }


    private void test1(double doule) {
        BigDecimal bg = new BigDecimal(doule);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }

    private void test2(double doule) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(doule));
    }

    private void test3(double doule) {
        System.out.println(String.format("%.2f", doule));
    }

    private void test4(double doule) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);  // 默认是true，会按照千分位进行输出，如100,000
        System.out.println(nf.format(doule));
    }





    /**
     * 测试-线程池
     */
    public void testExecutor() {
        System.out.println("----------------testExecutor 开始--------------------");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " run");
                } catch (Exception e) {

                }
            }
        };


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                5,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2));

        System.out.println("---初始化---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);


        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());

        System.out.println("----------------testExecutor 结束--------------------");
    }


    public void testListAndArray() {
        System.out.println("----------------testListAndArray 开始--------------------");

        array2List();

        list2Array();

        System.out.println("----------------testListAndArray 结束--------------------");
    }
    /**
     * 数组转集合
     */
    private  void array2List() {
        String[] array = getArray();
        List<String> list = Arrays.asList(array);
    }

    /**
     * 集合转数组
     */
    private  void list2Array() {
        ArrayList<String> data = getList();
        /**
         * 报错
         *
         * 泛型只针对单个类型
         * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
         */
//        String[] objects = (String[]) data.toArray();

        String[] strings2 = data.toArray(new String[data.size()]);


    }

    /**
     * 获取集合
     * @return
     */
    private  ArrayList<String>  getList() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("data" + i);
        }

        return data;
    }

    /**
     * 获取数组
     * @return
     */
    private String[] getArray() {
        String[] data = new String[50];
        for (int i = 0; i < 20; i++) {
            data[i] = "data" + i;
        }
        return data;
    }

    public void testMap() {
        System.out.println("----------------testMap 开始--------------------");




        System.out.println("----------------testMap 结束--------------------");
    }




    public void test() {
        System.out.println("---------------- 开始--------------------");


        System.out.println("---------------- 结束--------------------");
    }


}
