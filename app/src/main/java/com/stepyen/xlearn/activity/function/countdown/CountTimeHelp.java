package com.stepyen.xlearn.activity.function.countdown;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * date：2020-01-02
 * author：stepyen
 * description：计时帮助类
 *
 * 功能：
 * 1、支持 计时 和 倒计时
 * 2、支持开始、暂停、继续、释放
 *
 *
 */
public class CountTimeHelp {
    /**
     * 是否是倒计时
     */
    private boolean isCountDownTime = false;

    /**
     * 最大计时时间
     */
    private int maxCountTime = -1;

    /**
     * 当前时间
     */
    private int currentTime = 0;

    /**
     * 是否计时结束
     */
    private boolean isFinish = false;

    private OnCountListener mOnCountListener;

    private Timer timer;

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

    private CountTimeHelp(boolean isCountDownTime, int maxCountTime) {
        this.isCountDownTime = isCountDownTime;
        this.maxCountTime = maxCountTime;
        if (isCountDownTime) {
            currentTime = maxCountTime;
        }else{
            currentTime = 0;
        }
    }

    /**
     * 开始计时
     */
    public void start() {

        if (isFinish) {
            return;
        }

        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isCountDownTime) {
                        countDown();
                    }else{
                        countUp();
                    }
                }
            }, 0, 1000);
        }
    }

    /**
     * 计时结束
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /**
     * 重置
     */
    public void reset() {
        stop();
        if (isCountDownTime) {
            currentTime = maxCountTime;
        }else{
            currentTime = 0;
        }
        isFinish = false;
    }

    /**
     * 倒计时
     */
    private void countDown() {
        callbackCountTime(currentTime);
        currentTime -= 1;
        if (currentTime <0) {
            if (mOnCountListener!= null) {
                mOnCountListener.onFinish();
            }
            stop();
            isFinish = true;
        }
    }

    /**
     * 计时
     */
    private void countUp() {
        callbackCountTime(currentTime);
        currentTime += 1;
        // 有最大限制时间
        if (maxCountTime != -1 && currentTime > maxCountTime) {
            if (mOnCountListener!= null) {
                mOnCountListener.onFinish();
            }
            stop();
            isFinish = true;
        }
    }

    private void callbackCountTime(int time) {
        if (mOnCountListener != null) {
            int hour = time / 3600;
            int minute = time/60%60;
            int second = time%60;
            mOnCountListener.onCount(time, hour,minute,second);
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
        void onCount(int time, int hour, int minute, int second);


        /**
         * 计时结束
         */
        void onFinish();


    }

}
