package com.stepyen.xlearn.activity.function.encrypt;


import android.util.Base64;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
    public DES() {
    }

    //测试
    public static void main(String args[]) throws Exception {
        {

            JSONObject json = new JSONObject();
            json.put("rt", "IOS");
            json.put("rd", "DEBUG");
            json.put("debugBundle", "的我我而去额  我而去+");

            String data = " {\"rt\":\"ANDROID\",\"rd\":\"DEBUG\",\"debugSha\":\"8B:CF:F9:05:88:71:AD:EE:FE:38:8E:D0:0D:CF:74:EA:D4:D8:C8:1A\"}";

            String str = "b1jqH5d6jL73M54niCXn4BzWY7Y3LKVTuhZQTNRU/szZwZOXRtUapCBxxv1HJfto7IpNayrg8h7ZwZOXRtUapNBDRn5iKMhQlHxhn6UlMAtAbhPwLb0kmiY1zTfIk04fUC6N6ruBLrvPpzcLIZbzwPeJ5iB23Sps1mRf4VvdZPktwQloufqr6wCngeOaxaiXsT41RdYdGmJUwxZw5cgvtjDPXNUiyICF";

            String password = "12313eqweqweqwe";

            String encrypt = encrypt(data, password);
            System.out.println("加密后：" + encrypt);


            String decryResult = DES.decrypt(encrypt, password);
            System.out.println("解密后：" + new String(decryResult));


        }
    }


    private static String charsetName = "utf-8";

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static String encrypt(String datasource, String password) throws InvalidKeyException {

        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
//创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
//Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
//用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
//现在，获取数据并加密
//正式执行加密操作
            byte b[] = cipher.doFinal(datasource.getBytes(charsetName));
            return Base64.encodeToString(b, Base64.DEFAULT);
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String src, String password) {
        byte[] b = Base64.decode(src.getBytes(), Base64.DEFAULT);

        try {
            return new String(decrypt(b, password), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, String password) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
// 创建一个DESKeySpec对象

            DESKeySpec desKey = new DESKeySpec(password.getBytes());
// 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
// 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
// Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
// 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
// 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}