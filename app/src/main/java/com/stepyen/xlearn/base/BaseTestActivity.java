package com.stepyen.xlearn.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepyen.xlearn.R;
import com.stepyen.xutil.display.DensityUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseTestActivity extends AppCompatActivity {

    private LinearLayout mParentLl;
    protected AppCompatActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        mActivity = this;
        mParentLl = findViewById(R.id.ll_parent);
        //在这里添加View
        addView();
        ButterKnife.bind(this);
        initView();

    }

    public void addView() {

    }

    public abstract void initView();

    public void addView(String title, final Class<?> cls) {
        addView(title, v->{
            Intent intent = new Intent(BaseTestActivity.this, cls);
            startActivity(intent);
        }, 0);
    }


    public void addView(String title, final Class<?> cls, int marginTop) {
        addView(title, v->{
            Intent intent = new Intent(BaseTestActivity.this, cls);
            startActivity(intent);
        }, marginTop);
    }

    public void addView(String title, View.OnClickListener clickListener) {
        addView(title,clickListener,0 );
    }

    public void addView(String title, View.OnClickListener clickListener, int marginTop) {
        Button button = new Button(this);
        button.setText(title);
        button.setOnClickListener(clickListener);
        mParentLl.addView(button);
        setLpMarginTop(button, marginTop);
    }

    public TextView addTextView(CharSequence txt, int marginTop) {
        TextView tv = new TextView(this);
        tv.setText(txt);
        mParentLl.addView(tv);
        setLpMarginTop(tv, marginTop);

        return tv;
    }

    private void setLpMarginTop(View view, int marginTop) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (lp != null) {
            lp.setMargins(0, DensityUtils.dp2px(mActivity, marginTop), 0, 0);
            view.setLayoutParams(lp);
        }
    }

    public void addTextView(CharSequence txt) {
        addTextView(txt, 0);
    }


    public View addView(int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        addView(view);

        return view;
    }

    public View addView(View view) {
        mParentLl.addView(view);
        return view;
    }

    public void onViewClick(View view) {

    }

    public void showToast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }

}
