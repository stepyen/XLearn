package com.stepyen.xlearn.activity.java;

import com.orhanobut.logger.Logger;

/**
 * date：2020-03-13
 * author：stepyen
 * description：转义字符
 */
public class TransferredMeaning {

    public static void main(String[] args) {


        String url = "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openGame?{\\\"url\\\":\\\"{\\\\\\\"type\\\\\\\":\\\\\\\"https:\\\\\\\\\\\\\\/\\\\\\\\\\\\\\/beta-wx.kid58.com\\\\\\\\\\\\\\/double12\\\\\\\\\\\\\\/index\\\\\\\",\\\\\\\"data\\\\\\\":\\\\\\\"\\\\\\\"}\\\"}\"}";

        String game = "babybus://push/openGame";

        String tempUrl = url.replaceAll("\\\\", "");
        if (tempUrl.contains(game)) {
            System.out.println("包含");
        }else{
            System.out.println("不包含");

        }
    }
}
