package com.stepyen.xlearn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stepyen.xui.widget.linearlayout.StarLayout;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    private static final int GET_IMG = 1001;
    private String url = "http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg";
    private Button button;
    private ImageView image;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_test).setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER,"18762");
            startActivity(intent);

        });


        image = findViewById(R.id.imageView);
        button = findViewById(R.id.but);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncGet();
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == GET_IMG){
                    byte[] picture = (byte[]) message.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(picture,0,picture.length);
                    image.setImageBitmap(bitmap);     //主线程修改UI
                }
                return true;
            }
        });

    }

    private void AsyncGet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();   //默认为get请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"onFailure = " +e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                byte[] picture = response.body().bytes();
                Log.e(TAG,"response = " + picture);
                Message message = Message.obtain();
                message.what = GET_IMG;
                message.obj = picture;
                handler.sendMessage(message);
            }
        });
    }



}
