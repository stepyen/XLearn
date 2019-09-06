package com.stepyen.xlearn.java;

import java.util.ArrayList;
import java.util.List;

/**
 * date：2019/9/6
 * author：stepyen
 * description：Java 泛型学习
 */
public class JavaGenericity {
    public static class Animal{

    }

    public static class Dog extends Animal {

    }


    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<Dog>();
        List<? extends Animal> animalList = dogList;


    }



}
