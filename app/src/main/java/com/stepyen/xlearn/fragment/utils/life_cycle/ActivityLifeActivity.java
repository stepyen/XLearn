package com.stepyen.xlearn.fragment.utils.life_cycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.stepyen.xlearn.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * activity 生命周期
 */
public class ActivityLifeActivity extends AppCompatActivity {
    private static final String TAG = "ActivityLifeActivityTAG";
    @BindView(R.id.lifecycleview)
    LifecycleView mLifecycleview;
    @BindView(R.id.lifecycleviewgruop)
    LifecycleViewGruop mLifecycleviewgruop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_life);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(LifeFragment.newInstance(), LifeFragment.class.getSimpleName())
                .commit();


//        getSupportFragmentManager().executePendingTransactions();
        Log.i(TAG, "onCreate: ");

//        mLifecycleview.setSize(1);
//        mLifecycleviewgruop.setSize(1);
//        mLifecycleviewgruop.addView(new TextView(this));


    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.i(TAG, "onAttachFragment: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    /*********生命周期记录*****************/

/**
 * 横竖屏切换
 *
 * onPause:
 * onSaveInstanceState:
 * onStop:
 * onDestroy:
 * onCreate:
 * onStart:
 * onRestoreInstanceState:
 * onResume:
 */


}
