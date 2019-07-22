package com.stepyen.xlearn.fragment.utils.launch;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.stepyen.xlearn.base.BaseTestActivity;

/**
 * date：2019/4/4
 * author：stepyen
 * description：
 */
public class CActivity extends BaseTestActivity {
    private static final String TAG = "CActivity";
    @Override
    public void initView() {
        TextView tv = new TextView(this);
        tv.setText("CActivity");
        addView(tv);

        addView("BActivity", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, BActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        Log.i(TAG, "onCreate: ");
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
}
