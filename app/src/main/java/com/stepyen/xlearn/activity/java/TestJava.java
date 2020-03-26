package com.stepyen.xlearn.activity.java;


/**
 * date：2020-03-16
 * author：stepyen
 * description：Java 测试入口类
 */
public class TestJava {

    public static void main(String[] args) {
        // 转移字符
        TransferredMeaning transferredMeaning = new TransferredMeaning();
//        transferredMeaning.testSlash();
//        transferredMeaning.testUrlEncodeAndUrlDecode();


        String str = null;
        String msg = "";
        switch (str) {
            case "1":
                msg = "1";
                break;
            default:
                msg = "default";
                break;
        }

        System.out.println(msg);

    }

}
