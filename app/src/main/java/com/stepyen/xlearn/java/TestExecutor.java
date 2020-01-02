package com.stepyen.xlearn.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * date：2019-11-24
 * author：stepyen
 * description：线程池
 */
public class TestExecutor {


    public static void main(String[] args) {
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
    }
}
