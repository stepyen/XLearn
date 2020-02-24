package com.stepyen.chivox.record;

/**
 * date：2020-02-14
 * author：stepyen
 * description：
 */

import android.util.Log;

public class LogUtil {
    private static String TAG = "LogUtil";
    private static boolean isOutputLog = true;

    public LogUtil() {
    }

    public static void setOutputLog(boolean isOutputLog) {
        isOutputLog = isOutputLog;
    }

    public static void i(String msg) {
        if (isOutputLog) {
            Log.i(TAG, msg);
        }

    }

    public static void i(String tag, String msg) {
        if (isOutputLog) {
            Log.i(tag, msg);
        }

    }

    public static void d(String msg) {
        if (isOutputLog) {
            Log.d(TAG, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (isOutputLog) {
            Log.d(tag, msg);
        }

    }

    public static void e(String msg) {
        if (isOutputLog) {
            Log.e(TAG, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (isOutputLog) {
            Log.e(tag, msg);
        }

    }

    public static void f(String msg) {
        if (isOutputLog) {
            Log.d(TAG, msg);
        }

    }

    public static void f(String tag, String msg) {
        if (isOutputLog) {
            Log.d(tag, msg);
        }

    }

    public static void s(String msg) {
        if (isOutputLog) {
            Log.i(TAG, msg);
        }

    }

    public static void s(String tag, String msg) {
        if (isOutputLog) {
            Log.i(tag, msg);
        }

    }

    public static void w(String msg) {
        if (isOutputLog) {
            Log.w(TAG, msg);
        }

    }

    public static void w(String tag, String msg) {
        if (isOutputLog) {
            Log.w(tag, msg);
        }

    }
}
