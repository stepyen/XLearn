package com.stepyen.xlearn.fragment.expands.SMSListen;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import androidx.annotation.NonNull;

/**
 *
 *
 * 内容监听
 */
public class GetSmsMessage extends ContentObserver{
	private Handler mHandler;

	public GetSmsMessage(Handler handler) {
		super(handler);
		mHandler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		mHandler.sendEmptyMessage(SMSListenFragment.WHAT_SMS_CHANGE);
	}

}
