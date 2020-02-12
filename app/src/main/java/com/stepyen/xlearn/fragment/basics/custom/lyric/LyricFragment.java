package com.stepyen.xlearn.fragment.basics.custom.lyric;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.fragment.basics.countdown.CountTimeHelp;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;

/**
 * date：2019/7/5
 * author：stepyen
 * description：歌词
 */
@Page(name = "歌词")
public class LyricFragment extends BaseFragment {
    private static final String TAG = "LyricFragment";

    private int index = 0;
    private CountTimeHelp mCountTimeHelp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lyric;
    }

    @Override
    protected void initViews() {
        showLyric();
    }

    private void showLyric() {
        ArrayList<String> data = new ArrayList<>();
        LyricView lyricView = findViewById(R.id.lyricview);

        findViewById(R.id.btn_lyric_start).setOnClickListener(v -> {
            lyricView.setData(data);
            mCountTimeHelp.start();
        });
        findViewById(R.id.btn_lyric_stop).setOnClickListener(v -> {
            mCountTimeHelp.stop();
        });


        data.add("0风急天高猿啸哀\n");
        data.add("1渚清沙白鸟飞回\n");
        data.add("2无边落木萧萧下\n");
        data.add("3不尽长江滚滚来\n");
        data.add("4万里悲秋常作客\n");
        data.add("5百年多病独登台\n");
        data.add("6艰难苦恨繁霜鬓\n");
        data.add("7潦倒新停浊酒杯\n");


        mCountTimeHelp = CountTimeHelp.newCountUpHelp();
        mCountTimeHelp.setOnCountListener(new CountTimeHelp.OnCountListener() {
            @Override
            public void onCount(int time, int hour, int minute, int second) {
                if (second % 3 == 0) {
                    lyricView.setCurrentIndex(index);
                    index += 1;
                    if (index == data.size()) {
                        index = 0;
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        });


    }

    @Override
    public void onDestroy() {
        mCountTimeHelp.stop();
        super.onDestroy();
    }
}







