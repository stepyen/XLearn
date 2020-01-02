package com.stepyen.xlearn.fragment.basics.lyric;

import android.view.View;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.fragment.basics.countdown.CountTimeHelp;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;

/**
 * date：2019/7/5
 * author：stepyen
 * description：歌词
 */
@Page(name = "歌词", extra = R.drawable.ic_widget_imageview)
public class LyricFragment extends BaseFragment {
    private static final String TAG = "LyricFragment";

    private int index =0 ;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lyric;
    }

    @Override
    protected void initViews() {
        showLyric();

    }

    private void showLyric() {
        LyricView lyricView= findViewById(R.id.lyricview);

        ArrayList<String> data = new ArrayList<>();
        data.add("0风急天高猿啸哀，");
        data.add("1渚清沙白鸟飞回。");
        data.add("2无边落木萧萧下，");
        data.add("3不尽长江滚滚来。");
        data.add("4万里悲秋常作客，");
        data.add("5百年多病独登台。");
        data.add("6艰难苦恨繁霜鬓，");
        data.add("7潦倒新停浊酒杯。");

        lyricView.setData(data);

        CountTimeHelp countTimeHelp = CountTimeHelp.newCountUpHelp();
        countTimeHelp.setOnCountListener(new CountTimeHelp.OnCountListener() {
            @Override
            public void onCount(int time, int hour, int minute, int second) {
                if (second%2==0) {
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
        countTimeHelp.start();
    }


}






