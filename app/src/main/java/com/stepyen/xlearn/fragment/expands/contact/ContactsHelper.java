package com.stepyen.xlearn.fragment.expands.contact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机通讯录联系人
 */

public class ContactsHelper {

    Context mContext;
    List<ContactsBean> datas = new ArrayList<>();

    public ContactsHelper(Context context) {
        this.mContext = context;
        getContacts();
    }

    public String getContactsJson(int userid) throws Exception {
        JSONObject json = new JSONObject();
        json.put("userid", userid + "");
        JSONArray userArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            ContactsBean data = datas.get(i);
            JSONObject userp = new JSONObject();
            userp.put("name", data.name);
            userp.put("phone", data.number);
            userArray.put(userp.toString());
        }
        json.put("userp", userArray);
        String params = json.toString();
//        String strParams = DesUtil.encrypt(params);
        return params;
    }

    private void getContacts() {
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            ContactsBean bean = new ContactsBean();
            bean.name = cursor.getString(nameFieldColumnIndex);
            bean.number = cursor.getString(numberFieldColumnIndex);
            datas.add(bean);

            Log.d("haha", "getContacts: bean.name " + bean.name);
            Log.d("haha", "getContacts: bean.number " + bean.number);
        }
    }

    class ContactsBean {
        String name;
        String number;
    }
}
