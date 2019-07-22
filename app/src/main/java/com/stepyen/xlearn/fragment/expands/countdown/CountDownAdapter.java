package com.stepyen.xlearn.fragment.expands.countdown;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stepyen.constant.DateFormatConstants;
import com.stepyen.xlearn.R;
import com.stepyen.xutil.data.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date：2019/7/22
 * author：stepyen
 * description：
 */
public class CountDownAdapter extends BaseQuickAdapter<CountDownEntity, BaseViewHolder> {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_time)
    TextView mTvTime;
//    @BindView(R.id.countdown)
//    SnapUpCountDownTimerView mCountdown;

    private SimpleDateFormat sdf = null;

    private Context mContext;
    public CountDownAdapter(Context context,  @Nullable List<CountDownEntity> data) {
        super(R.layout.vh_count_down, data);
        mContext = context;

        sdf = DateUtils.getSimpleDateFormat(DateFormatConstants.HHmmss, 0);

        startTime();
    }


    private void startTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int size = mData.size();
                        for (int i = 0; i < size; i++) {
                            CountDownEntity entity = mData.get(i);
                            long useTime = entity.time;
                            if (useTime > 0) {
                                useTime -= 1;
                                entity.time = useTime;
                                if (useTime <= 1 && entity.timeFlag) {
                                    entity.timeFlag = false;
                                }else {
                                    entity.timeFlag = true;
                                }
                                CountDownAdapter.this.notifyItemChanged(i);
                            }
                        }
                    }
                });
            }
        }, 0, 1000);
    }


    @Override
    protected void convert(BaseViewHolder helper, CountDownEntity item) {
        ButterKnife.bind(this, helper.itemView);

        mTvTitle.setText(item.title);
        mTvTime.setText(DateUtils.millis2String(item.time * 1000, sdf));
    }
}
