package com.stepyen.xlearn;

import android.content.Intent;
import android.os.Bundle;

import com.stepyen.xui.widget.linearlayout.StarLayout;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_test).setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER,"18762");
            startActivity(intent);

        });




    }

}
