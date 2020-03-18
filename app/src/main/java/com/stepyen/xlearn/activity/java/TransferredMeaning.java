package com.stepyen.xlearn.activity.java;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * date：2020-03-13
 * author：stepyen
 * description：转义字符
 */
public class TransferredMeaning {


    /**
     * 斜杠 \
     */
    public void testSlash() {

        String url = "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openGame?{\\\"url\\\":\\\"{\\\\\\\"type\\\\\\\":\\\\\\\"https:\\\\\\\\\\\\\\/\\\\\\\\\\\\\\/beta-wx.kid58.com\\\\\\\\\\\\\\/double12\\\\\\\\\\\\\\/index\\\\\\\",\\\\\\\"data\\\\\\\":\\\\\\\"\\\\\\\"}\\\"}\"}";

        String game = "babybus://push/openGame";

        String tempUrl = url.replaceAll("\\\\", "");
        if (tempUrl.contains(game)) {
            System.out.println("包含");
        }else{
            System.out.println("不包含");

        }
    }

    /**
     * 测试 字符串 url 的加码和加码
     *
     * URLEncoder.encode(url) 加码
     * URLDecoder.decode(url,"UTF-8") 解码
     */
    public void testUrlEncodeAndUrlDecode() {

        System.out.println("-------------------");

        try {
            String url = "babybus://push/openPush?{\"id\":\"123\",\"type\":\"getui\",\"uri\":\"babybus:\\/\\/push\\/openHome\"}";

            System.out.println("原来url："+url);

            System.out.println("加码后："+ URLEncoder.encode(url));             // babybus%3A%2F%2Fpush%2FopenPush%3F%7B%22id%22%3A%22123%22%2C%22type%22%3A%22getui%22%2C%22uri%22%3A%22babybus%3A%5C%2F%5C%2Fpush%5C%2FopenHome%22%7D

            System.out.println("解码后："+ URLDecoder.decode(url,"UTF-8"));// babybus://push/openPush?{"id":"123","type":"getui","uri":"babybus:\/\/push\/openHome"}

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
