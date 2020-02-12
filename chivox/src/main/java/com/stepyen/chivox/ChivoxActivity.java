package com.stepyen.chivox;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chivox.AIEngine;
import com.stepyen.chivox.R;
import com.stepyen.common.utils.AudioRecoderUtils;
import com.stepyen.common.widget.AudioRecoderDialog;
import com.stepyen.common.widget.TouchButton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * date：2020-02-12
 * author：stepyen
 * description：驰声sdk
 */
public class ChivoxActivity extends AppCompatActivity implements View.OnClickListener, AudioRecoderUtils.OnAudioStatusUpdateListener, TouchButton.OnEvaluationTouchListen {

    private static final String TAG = "ChivoxActivity";

    private AudioRecoderDialog mRecoderDialog;
    private AudioRecoderUtils mRecoderUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chivox);
        init();
    }

    private void init() {
        mRecoderUtils = new AudioRecoderUtils();
        mRecoderUtils.setOnAudioStatusUpdateListener(this);


        mRecoderDialog = new AudioRecoderDialog(this);
        mRecoderDialog.setShowAlpha(0.98f);


        //兼容6.0权限管理
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        } else {
            initEngine();
        }

        ((Button) findViewById(R.id.btn_english_stop)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_english_cancle)).setOnClickListener(this);
        ((TouchButton) findViewById(R.id.btn_english_letter)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_english_word)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_english_sent)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_chinese_word)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_chinese_words)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_chinese_idiom)).setOnEvaluationTouchListen(this);
        ((TouchButton) findViewById(R.id.btn_chinese_sent)).setOnEvaluationTouchListen(this);

        findViewById(R.id.btn_play_back).setOnClickListener(v -> {

        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                initEngine();
            } else {
                // Permission Denied
            }
        }
    }

    private void initEngine() {

        ChivoxHelp.getInstance().create(ChivoxActivity.this, Const.APP_KEY,Const.SECRET_KEY);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onUpdate(double db) {
        if (null != mRecoderDialog) {
            mRecoderDialog.setLevel((int) db);
        }
    }

    @Override
    public void down(int id) {

        if (id == R.id.btn_english_letter) {

        }
        if (id == R.id.btn_english_word) {

        }

        if (id == R.id.btn_english_sent) {

        }

        if (id == R.id.btn_chinese_word) {

        }

        if (id == R.id.btn_chinese_words) {

        }

        if (id == R.id.btn_chinese_idiom) {

        }

        if (id == R.id.btn_chinese_sent) {

        }


    }

    @Override
    public void up() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChivoxHelp.getInstance().destroy();
    }
}
