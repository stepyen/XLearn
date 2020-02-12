package com.stepyen.xlearn.utils;

/**
 * date：2019/2/19
 * author：yfj
 * description：
 */
public class JsUtil {


    /**
     * 加载js的方法拼接
     * @param method 方法 如 androidQoocarPay.pay
     * @param param 参数
     * @return
     */
    public static String loadJs(String method, String... param) {

        StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(method);
        sb.append("('");
        int length = param.length;
        for (int i = 0; i < length; i++) {
            sb.append(param[i]);
            if (length != length - 1) {
                sb.append("','");
            }
        }

        sb.append("');");
        return sb.toString();
    }

}
