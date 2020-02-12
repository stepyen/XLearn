package com.stepyen.xlearn.utils;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * 创建时间：2018/6/1
 * 作者：yfj
 * 描述：
 */

public class EditTextUtil {
    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }







}
