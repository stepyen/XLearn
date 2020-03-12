package com.stepyen.xlearn.activity.thrid.gson;

import androidx.annotation.NonNull;

/**
 * date：2020-03-10
 * author：stepyen
 * description：
 */
public class User {
    public String name;
    public int age;


    @NonNull
    @Override
    public String toString() {
        return "name:" + name + " age:" + age;
    }
}
