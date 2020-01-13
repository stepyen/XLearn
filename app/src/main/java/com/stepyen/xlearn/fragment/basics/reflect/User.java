package com.stepyen.xlearn.fragment.basics.reflect;

/**
 * date：2020-01-08
 * author：stepyen
 * description：
 */
public class User {
    private String name = "小明";
    private int age = 17;
    private OnChangeNameListen mOnChangeNameListen;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (mOnChangeNameListen != null) {
            mOnChangeNameListen.change(name);
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOnChangeNameListen(OnChangeNameListen onChangeNameListen) {
        mOnChangeNameListen = onChangeNameListen;
    }


}

