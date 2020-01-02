package com.stepyen.xlearn.fragment.basics.countdown;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * date：2020-01-02
 * author：stepyen
 * description：计时帮助类
 *
 * 支持 计时 和 倒计时
 */
public class CountTimeHelp {

    // 是否是倒计时
    private boolean isCountDownTime = false;

    //最大计时时间
    private int maxCountTime = -1;

    // 当前时间
    private int currentTime = 0;

    private OnCountListener mOnCountListener;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            if (isCountDownTime) {
                countDown();
            }else{
                countUp();
            }
        }
    };

    private Timer timer;



    private CountTimeHelp(boolean isCountDownTime, int maxCountTime) {
        this.isCountDownTime = isCountDownTime;
        this.maxCountTime = maxCountTime;
    }


    public static CountTimeHelp newCountDownHelp(int maxCountTime) {
        if (maxCountTime <=0) {
            throw new IllegalStateException("倒计时时间不能小于等于0");
        }
        return new CountTimeHelp(true, maxCountTime);
    }

    public static CountTimeHelp newCountUpHelp() {
        return new CountTimeHelp(false,-1);
    }

    public static CountTimeHelp newCountUpHelp(int maxCountTime) {
        if (maxCountTime <=0) {
            throw new IllegalStateException("计时时间不能小于等于0");
        }
        return new CountTimeHelp(false,maxCountTime);
    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }


    }


    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void countDown() {
        callbackCountTime(maxCountTime);
        maxCountTime -= 1;
        if (maxCountTime <0) {
            if (mOnCountListener!= null) {
                mOnCountListener.onFinish();
            }
            stop();
        }
    }
    private void countUp() {
        callbackCountTime(currentTime);
        currentTime += 1;
        // 有最大限制时间
        if (maxCountTime != -1 && currentTime > maxCountTime) {
            if (mOnCountListener!= null) {
                mOnCountListener.onFinish();
            }
            stop();
        }
    }

    private void callbackCountTime(int time) {
        if (mOnCountListener != null) {
            int hour = time / 3600;
            int minute = time/60%60;
            int second = time%60;
            mOnCountListener.onCount(maxCountTime, hour,minute,second);
        }
    }


    public void setOnCountListener(OnCountListener onCountListener) {
        this.mOnCountListener = onCountListener;
    }

    public interface OnCountListener {

        /**
         * 计时
         * @param time 总时间
         * @param hour 小时
         * @param minute 分钟
         * @param second 秒
         */
        void onCount(int time,int hour,int minute,int second);


        void onFinish();


    }


}
