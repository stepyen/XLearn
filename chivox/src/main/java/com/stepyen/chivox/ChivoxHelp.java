package com.stepyen.chivox;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.chivox.AIEngine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * date：2020-02-12
 * author：stepyen
 * description：
 */
public class ChivoxHelp {
    private static final String TAG = "ChivoxHelp";
    private final int STATE_ENGINE_FAIL = 0;    //
    private long engine = STATE_ENGINE_FAIL;    // 引擎实例的指针

    private static volatile ChivoxHelp instance;

    private ChivoxHelp() {
    }

    public static ChivoxHelp getInstance() {
        if (instance == null) {
            synchronized (ChivoxHelp.class) {
                if (instance == null) {
                    instance = new ChivoxHelp();
                }
            }
        }
        return instance;
    }


    public void create(Context context, String appkey, String secretKey) {
        String config = "";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appKey", appkey);
            jsonObject.put("secretKey", secretKey);
            jsonObject.put("provision", getAssetsCacheFile(context, "aiengine.provision"));

            JSONObject couldJo = new JSONObject();
            couldJo.put("server", "wss://cloud.chivox.com:443");
            couldJo.put("connectTimeout", 20);
            couldJo.put("serverTimeout", 60);
            jsonObject.put("cloud", couldJo);

            JSONObject prefJo = new JSONObject();
            prefJo.put("enable", 1);
            prefJo.put("output", context.getExternalCacheDir().getAbsolutePath());
            jsonObject.put("prof", prefJo);

            config = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "initEngine: config : " + config);

        engine = AIEngine.aiengine_new(config, context);
        if (engine == 0) {
            Log.d(TAG, "initEngine: 失败");
        } else {
            Log.d(TAG, "initEngine: 成功");
        }
    }


    public void destroy() {
        if (engine != 0) {
            AIEngine.aiengine_delete(engine);
            engine = 0;
        }
    }

    /**
     * 复制 Assets 下文件到 cache 下，并返回文件地址
     *
     * @param context
     * @param fileName
     * @return
     */
    public String getAssetsCacheFile(Context context, String fileName) {
        File cacheFile = new File(context.getCacheDir(), fileName);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = context.getAssets().open(fileName);
            outputStream = new FileOutputStream(cacheFile);
            byte[] buf = new byte[1024];

            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return cacheFile.getAbsolutePath();
    }

}
