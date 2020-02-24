package com.stepyen.chivox;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.chivox.AIEngine;
import com.xs.SingEngine;
import com.xs.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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

    private Context mContext = null;

    private AIRecorder aiRecorder = null;

    // 基础路径
    protected String mAudioPath = "";

    //最后一个录音路径
    private String mLastRecordPath = "";

    private ChivoxHelp(Context context) {
        if (context == null) {
            return;
        }
        aiRecorder = new AIRecorder();


        mContext = context.getApplicationContext();
        mAudioPath = context.getExternalCacheDir().getAbsolutePath() + "/cs_record/";

    }

    public static ChivoxHelp newInstance(Context context) {
        return new ChivoxHelp(context);
    }

    /**
     * 设置录音文件基础路径
     *
     * @param wavPath
     */
    public void setWavPath(String wavPath) {
        this.mAudioPath = wavPath;
        if (!TextUtils.isEmpty(mAudioPath) && !mAudioPath.endsWith("/")) {
            mAudioPath += "/";
        }
    }

    /**
     * 获取录音文件地址
     *
     * @return
     */
    public String getSoundPath() {
        return this.mLastRecordPath;
    }

    public void create(String appkey, String secretKey) {


        String config = "";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appKey", appkey);
            jsonObject.put("secretKey", secretKey);
            jsonObject.put("provision", getAssetsCacheFile(mContext, "aiengine.provision"));

            JSONObject couldJo = new JSONObject();
            couldJo.put("useServerTime", "1");  // 1表示使用服务器时间作为认证时间戳。
            couldJo.put("server", "wss://cloud.chivox.com:443");
            couldJo.put("connectTimeout", 20);
            couldJo.put("serverTimeout", 60);
            jsonObject.put("cloud", couldJo);

            JSONObject vadJo = new JSONObject();
            vadJo.put("enable", 1);// 1 表示初始化 VAD 资源;0 表示不初始化
//            vadJo.put("res", getAssetsCacheFile(mContext, "vad.0.12.20160802.bin"));// vad 资源的路径
            vadJo.put("res", AIEngineHelper.extractResourceOnce(mContext,"vad.0.12.20160802.bin",false));// vad 资源的路径
            vadJo.put("speechLowSeek", 100);// 灵敏度，单位 10ms，设置 100 表示说话停止后 1 秒判定为结束
            vadJo.put("sampleRate", 16000);// 采样率，单位为 Hz
            vadJo.put("strip", 0);// 传给上层音频数据时，是否截掉首尾空白，一般设置为 0(避免误截) }
            jsonObject.put("vad", vadJo);

            JSONObject prefJo = new JSONObject();
            prefJo.put("enable", 1);
            prefJo.put("output", mContext.getExternalCacheDir().getAbsolutePath());
            jsonObject.put("prof", prefJo);

            config = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "initEngine: config : " + config);

        engine = AIEngine.aiengine_new(config, mContext);
        if (engine == 0) {
            Log.d(TAG, "initEngine: 失败");
        } else {
            Log.d(TAG, "initEngine: 成功");
        }
    }

    public void start(Map<String, Object> requestMap) {
        String param = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("coreProvideType", "cloud");

            JSONObject appJo = new JSONObject();
            appJo.put("userId", "guest");
            jsonObject.put("app", appJo);

            JSONObject vadJo = new JSONObject();
            vadJo.put("vadEnable", 0);// 1 表示启用 VAD 功能;0 表示不启用
            vadJo.put("refDuration", 2); // 设置音频 vad 延迟生效时长(单位:s)，在刚开始录音的几秒内屏蔽 VAD
            jsonObject.put("vad", vadJo);

            JSONObject audioJo = new JSONObject();
            audioJo.put("audioType", "wav");
            audioJo.put("channel", 1);
            audioJo.put("sampleBytes", 2);
            audioJo.put("sampleRate", 16000);
            jsonObject.put("audio", audioJo);

            JSONObject requestJo = new JSONObject();
            for (String key : requestMap.keySet()) {
                requestJo.put(key, requestMap.get(key));
            }

            jsonObject.put("request", requestJo);

            jsonObject.put("soundIntensityEnable", 1); // 是否实时返回音量

            param = jsonObject.toString();




            Log.d(TAG, "start: " + param);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        byte[] tokenId = new byte[64];
        AIEngine.aiengine_callback callback = new AIEngine.aiengine_callback() {
            @Override
            public int run(byte[] id, int type, byte[] data, int size) {
                Log.d(TAG, "run: id " + id);
                Log.d(TAG, "run: type " + type);
                Log.d(TAG, "run: data " + data);

                if (type == AIEngine.AIENGINE_MESSAGE_TYPE_JSON) {
                    final String result = new String(data, 0, size).trim(); //返回结果是json格式
                    Log.d(TAG, "run: data String " + result);
                }

                Log.d(TAG, "run: size " + size);
                return 0;
            }
        };

        AIEngine.aiengine_start(engine, param, tokenId, callback, mContext);

        mLastRecordPath = mAudioPath + System.currentTimeMillis() + ".wav";

        aiRecorder.start(mLastRecordPath, new AIRecorder.Callback() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onData(byte[] data, int size) {
                AIEngine.aiengine_feed(engine, data, size);
            }

            @Override
            public void onStopped() {
                AIEngine.aiengine_stop(engine);
            }
        });

    }

    /**
     * 处理 vad 结果
     */
    private void handleVadResult() {
//        final String result = new String(data, 0, size).trim(); /* must trim the end '\0' */
//        Log.d(TAG, result);
//        try {
//            JSONObject json = new JSONObject(result);
//            if (json.has("vad_status") || json.has("sound_intensity")) {
//                /* received vad_status report in json formatting */
//                int status = json.optInt("vad_status");
//                final int sound_intensity = json.optInt("sound_intensity");
//                if (status == 2) {
//                    runOnWorkerThread(new Runnable() {
//                        public void run() {
//                            recorder.stop();
//                        }
//                    });
//                }
//            } else {
//                /* received score result in json formatting */
//                if (recorder.isRunning()) {
//                    recorder.stop();
//                }
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        waitProgressBar.setVisibility(View.INVISIBLE);
//                        jsonResultTextEditor.setText(result);
//                        Log.d(TAG, result);
//                    }
//                });
//            }
//        } catch (JSONException e) {
//            /* parse result error */
//            Log.d(TAG, “pare result error !”);
//        }
    }

    public void stop() {
        aiRecorder.stop();
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
        if (cacheFile.exists()) {
            cacheFile.delete();
        }
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
