package com.stepyen.xlearn.fragment.basics.image;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：图片 学习
 *
 * 1、Tint 属性
 *
 *
 *
 */
@Page(name = "图片",extra = R.drawable.ic_widget_imageview)
public class ImageFragment extends BaseFragment {
    private static final String TAG = "ImageFragment";
    private ImageView iv_tint;
    private ImageView iv_tint2;
    private SeekBar seekbar_tint;
    private TextView tv_alpha_hint;
    private Button btn_change;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initViews() {

        iv_tint = findViewById(R.id.iv_tint);
        seekbar_tint = findViewById(R.id.seekbar_tint);
        tv_alpha_hint = findViewById(R.id.tv_alpha_hint);
        iv_tint2 = findViewById(R.id.iv_tint2);
        btn_change = findViewById(R.id.btn_change);
        seekbar_tint.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setImageViewTint(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_tint.setProgress(50);
        setImageViewTint(50);


        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (change) {
                    Drawable drawable = iv_tint2.getDrawable();
                    Drawable drawableWrap = DrawableCompat.wrap(drawable);
                    DrawableCompat.setTint(drawableWrap, Color.parseColor("#55000000"));
                    iv_tint2.setImageDrawable(drawableWrap);
                }else{
                    Drawable drawable = iv_tint2.getDrawable();
                    Drawable drawableWrap = DrawableCompat.wrap(drawable);
                    DrawableCompat.setTint(drawableWrap, Color.parseColor("#00000000"));
                    iv_tint2.setImageDrawable(drawableWrap);
                }

                change = !change;
            }
        });

    }

    boolean change = false;

    private void setImageViewTint(int progress) {

        int alpha = (int) (255 / 100f * progress);

        tv_alpha_hint.setText("progress: "+progress+"\nalpha(10):  "+alpha+"\nalpha(16)："+intToHex(alpha));


        int[][] states = new int[1][];
        states[0] = new int[]{android.R.attr.state_enabled};

        int color = Color.argb(alpha, 0,0,0);

        int[] colors = new int[]{color};

        ColorStateList colorStateList = new ColorStateList(states,colors);
        iv_tint.setImageTintList(colorStateList);
    }


    /**
     * 10进制 转为 16进制
     * @param n
     * @return
     */
    private String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }


    @OnClick(R.id.sava_resource_image)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.sava_resource_image:
                sava_resource_image();
                break;

            case R.id.sava_network_image:

                break;
        }

    }


    private void sava_resource_image() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_dog);
        saveImageView(getContext(),bitmap);
        ToastUtils.toast("保存图片成功");
    }

    private void sava_network_image() {

    }


    private void saveImageView(Context context,Bitmap bitmap) {
        // 创建文件
        File appDir = new File(context.getExternalCacheDir(), "image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);

        // 保存文件
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 文件插入系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }
}