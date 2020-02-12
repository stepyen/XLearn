package com.stepyen.xlearn.java.annotation;

import java.lang.reflect.Method;

/**
 * date：2019/5/8
 * author：stepyen
 * description：
 */
public class TestAnnotationMain {


    public static void main(String[] args) {
        TestAnnotationClass testAnnotationClass = new TestAnnotationClass();
        Class<? extends TestAnnotationClass> annotationClassClass = testAnnotationClass.getClass();
        Method[] methods = annotationClassClass.getMethods();
        for (Method method:methods) {
            boolean present = method.isAnnotationPresent(TestAnnotation.class);
            if (present) {
                TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                int size = testAnnotation.size();
                System.out.println("TestAnnotationClass "+size);
            }
        }
    }
}
