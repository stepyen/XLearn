package com.stepyen.xlearn.fragment.basics.content_provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

/**
 * date：2019-12-20
 * author：stepyen
 * description：
 */
public class StudentDao {
    private static final String TAG = "StudentDao";

    private SQLiteDatabase mSQLiteDatabase;

    public StudentDao(SQLiteDatabase SQLiteDatabase) {
        mSQLiteDatabase = SQLiteDatabase;
    }


    public void insertData(String name, String age) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, name);
        values.put(DBHelper.AGE, age);
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.insert(DBHelper.TABLE_NAME, null, values);
            Log.d(TAG, "insertData: 添加成功：name："+name);
        }
    }

    public void deleteData(String name) {
        if (mSQLiteDatabase!= null) {
            int count = mSQLiteDatabase.delete(DBHelper.TABLE_NAME, DBHelper.NAME + " = ?", new String[]{name});
            Log.d(TAG, "deleteData: 删除成功：name："+name);
        }
    }

    public void updateData(String name, String age) {
        ContentValues values = new ContentValues();
        if (!TextUtils.isEmpty(name)) {
            values.put(DBHelper.NAME, name);
        }
        if (!TextUtils.isEmpty(age)) {
            values.put(DBHelper.AGE, age);
        }

        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.update(DBHelper.TABLE_NAME, values, DBHelper.NAME + " = ?", new String[]{name});
            if (!TextUtils.isEmpty(name)) {
                Log.d(TAG, "updateData: 更新成功：name："+name);
            }
            if (!TextUtils.isEmpty(age)) {
                Log.d(TAG, "updateData: 更新成功：age："+age);
            }
        }
    }


    public void queryData(String queryAge) {
        if (mSQLiteDatabase == null || TextUtils.isEmpty(queryAge)) {
            return;
        }


        Cursor cursor = mSQLiteDatabase.query(DBHelper.TABLE_NAME,
                new String[]{DBHelper.NAME, DBHelper.AGE},
                DBHelper.AGE + " > ?",
                new String[]{queryAge},
                null,
                null,
                DBHelper.AGE + " desc");// 注意空格！
        if (cursor == null) {
            return;
        }
        int nameIndex = cursor.getColumnIndex(DBHelper.NAME);
        int ageIndex = cursor.getColumnIndex(DBHelper.AGE);
        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String age = cursor.getString(ageIndex);

            Log.d(TAG, "查询成功：  name: " + name + ", age: " + age);
        }

    }

}


