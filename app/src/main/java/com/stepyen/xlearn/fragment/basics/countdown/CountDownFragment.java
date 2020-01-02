package com.stepyen.xlearn.fragment.basics.countdown;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.fragment.expands.contact.ContactsHelper;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：倒计时
 */
@Page(name = "倒计时", extra = R.drawable.ic_widget_imageview)
public class CountDownFragment extends BaseFragment {
    private static final String TAG = "CountDownFragment";



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_count_down;
    }

    @Override
    protected void initViews() {
        countTime();
        countLimitTime();
        countDownTime();
    }


    private void countTime() {
        TextView tv = findViewById(R.id.tv_count_time);
        CountTimeHelp help = CountTimeHelp.newCountUpHelp();
        help.setOnCountListener(new CountTimeHelp.OnCountListener() {
            @Override
            public void onCount(int time, int hour, int minute, int second) {
                tv.setText(hour+":"+minute+":"+second);
            }

            @Override
            public void onFinish() {

            }
        });
        help.start();

    }

    private void countLimitTime() {
        TextView tv = findViewById(R.id.tv_count_time_limit);
        CountTimeHelp help = CountTimeHelp.newCountUpHelp(9);
        help.setOnCountListener(new CountTimeHelp.OnCountListener() {
            @Override
            public void onCount(int time, int hour, int minute, int second) {
                tv.setText(hour+":"+minute+":"+second);
            }

            @Override
            public void onFinish() {
                ToastUtils.toast("计时结束");
            }
        });
        help.start();

    }


    private void countDownTime() {
        TextView tv = findViewById(R.id.tv_count_down_time);
        CountTimeHelp help = CountTimeHelp.newCountDownHelp(9);
        help.setOnCountListener(new CountTimeHelp.OnCountListener() {
            @Override
            public void onCount(int time, int hour, int minute, int second) {
                tv.setText(hour+":"+minute+":"+second);
            }

            @Override
            public void onFinish() {
                ToastUtils.toast("倒计时结束");
            }
        });
        help.start();
    }
}






