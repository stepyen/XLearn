package com.stepyen.xlearn.fragment.basics.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * date：2019-12-20
 * author：stepyen
 * description：学生 内容提供者
 */
public class StudentContentProvider extends ContentProvider {

    //这里的AUTHORITY就是我们在AndroidManifest.xml中配置的authorities
    private static final String AUTHORITY = "com.stepyen.StudentContentProvider";

    //匹配成功后的匹配码
    private static final int MATCH_CODE = 100;

    private static UriMatcher uriMatcher;

    //数据改变后指定通知的Uri
    private static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/student");

    static {
        //匹配不成功返回NO_MATCH(-1)
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //添加我们需要匹配的uri
        uriMatcher.addURI(AUTHORITY,"student", MATCH_CODE);
    }

    private SQLiteDatabase mSqLiteDatabase;


    @Override
    public boolean onCreate() {
        DBHelper dbHelper = new DBHelper(getContext());
        mSqLiteDatabase = dbHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri) == MATCH_CODE){
            Cursor cursor = mSqLiteDatabase.query(DBHelper.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder,null);
            return cursor;
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == MATCH_CODE){
            long insert = mSqLiteDatabase.insert(DBHelper.TABLE_NAME, null, values);
            if (insert > -1) {
                notifyChange();
                return  ContentUris.withAppendedId(uri, insert);
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        if (uriMatcher.match(uri) == MATCH_CODE){
            int delete = mSqLiteDatabase.delete(DBHelper.TABLE_NAME, selection, selectionArgs);
            if (delete > 0) {
                notifyChange();
                return delete;
            }
        }
        return 0;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == MATCH_CODE){
            int update = mSqLiteDatabase.update(DBHelper.TABLE_NAME, values,selection, selectionArgs);

            if (update > 0) {
                notifyChange();
                return update;
            }
        }

        return 0;
    }

    private void notifyChange(){
        getContext().getContentResolver().notifyChange(NOTIFY_URI,null);
    }
}
