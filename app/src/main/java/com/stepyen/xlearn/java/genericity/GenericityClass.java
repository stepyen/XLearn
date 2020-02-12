package com.stepyen.xlearn.java.genericity;

//import com.stepyen.javalib.Animal;
//import com.stepyen.javalib.Cat;
//import com.stepyen.javalib.Dog;
//import com.stepyen.javalib.genericity.Demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.xml.transform.Source;

/**
 * date：2019/5/6
 * author：stepyen
 * description：泛型学习
 */
public class GenericityClass {


    public static class Food{ }

    public static class Fruit extends Food{ }

    public static class Apple extends Fruit{}

    //    泛型类
    public static class Base<T> implements Comparable<T>{
        private T mT;

        public Base() {

        }
        public Base(T t) {
            this.mT = t;

        }

        public void print() {
            if (mT != null) {
                System.out.println(mT.toString());
            }
        }

        @Override
        public int compareTo(T t) {
            return 0;
        }
    }

    public static class Child extends Base {

    }




    public static void main(String[] args) {

//      数组有继承关系
        String[] strings = new String[3];
        Object[] objects = strings;

//      容器类中泛型没有继承关系
        ArrayList<Food> foods1 = new ArrayList<>();
        ArrayList<Fruit> fruit1 = new ArrayList<>();

//        foods1 = Fruit1; // 错误
        ArrayList<? extends Food> foods2 = new ArrayList<>(); // 兼容子类的写法
        foods2 = fruit1;

//        ? extends Fruit 类型不确定，无法添加类型，但是可以读取
//        ? extends Fruit 接受的是 Fruit 或者其子类
        ArrayList<? extends Fruit> fruits = new ArrayList<>();
//        fruits.add(new Food());   // 错误
//        fruits.add(new Fruit());  // 错误
//        fruits.add(new Apple());  // 错误

//        fruits = new ArrayList<Food>();   // 错误
        fruits = new ArrayList<Fruit>();
        fruits = new ArrayList<Apple>();

        Fruit fruit = fruits.get(0);

//        ? super Fruit 可以添加 Fruit类型或其子类，但是无法读取，因为无法保证读取到具体类型
//        ? super Fruit 接受的是 Fruit 或者其父类
        ArrayList<? super Fruit> fruits2 = new ArrayList<>();
//        fruits2.add(new Food()); // 错误
        fruits2.add(new Fruit());
        fruits2.add(new Apple());

        fruits2 = new ArrayList<Food>();
        fruits2 = new ArrayList<Fruit>();
//        fruits2 = new ArrayList<Apple>(); // 错误

        Object object = fruits2.get(0);



        method1(19);
        method2(new Base<Object>());
        method3(new Base<Object>());
        method4(new Fruit());
        ArrayList<Fruit> integers = new ArrayList<>();
        integers.add(new Fruit());
        method5(integers);


        DynamicArray<Fruit> fruits3 = new DynamicArray<>();
        fruits3.add(new Fruit());
        fruits3.add(new Fruit());
        DynamicArray<Apple> apples1 = new DynamicArray<>();

        fruits3.addAll(apples1);
    }



    /**
     * 泛型方法
     * @param t
     * @param <T>
     */
    public static <T> void method1(T t) {
        System.out.println("method1");
        System.out.println(t.toString());
    }

    /**
     * 容器类泛型方法
     * @param t
     * @param <T>
     */
    public static <T> void method2(Base<T> t) {
        System.out.println("method2");
        System.out.println(t.toString());
    }

    /**
     * 通配符简化写法, 等同于   method2
     * @param t
     */
    public static void method3(Base<?> t) {
        System.out.println("method3");
        System.out.println(t.toString());
    }

    /**
     * 限制上界
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Fruit> void method4(T t) {
        System.out.println("method4");
        System.out.println(t.toString());
    }


    /**
     * 限制下限
     *
     * @param t
     */
    public static void method5(List<? super Fruit> t) {
        System.out.println("method5");
        System.out.println(t.toString());
    }

    public static void method6(List<? extends Fruit> t) {
        System.out.println("method5");
        System.out.println(t.toString());
    }



    //    泛型迭代赋值
    public static class A<T>{

    }

    public static class B<T> extends A<T>{

    }
    public static class C extends B<Test>{

    }

    public static class Test{
        public void print() {
            System.out.println("Test print");
        }
    }
}
