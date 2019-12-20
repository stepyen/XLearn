package com.stepyen.xlearn.fragment.basics.content_provider;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：ContentProvider 学习
 */
@Page(name = "ContentProvider", extra = R.drawable.ic_widget_imageview)
public class ContentProviderFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_content_provider;
    }

    @Override
    protected void initViews() {

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        StudentDao studentDao = new StudentDao(sqLiteDatabase);

        findViewById(R.id.btn_insert).setOnClickListener(v -> {

            studentDao.insertData("小明", "12");
            studentDao.insertData("小绿", "15");
            studentDao.insertData("小哈", "18");
            studentDao.insertData("小花", "21");

        });
        findViewById(R.id.btn_delete).setOnClickListener(v -> {

            studentDao.deleteData("小花");

        });
        findViewById(R.id.btn_update).setOnClickListener(v -> {

            studentDao.updateData("小绿", "89");

        });
        findViewById(R.id.btn_query).setOnClickListener(v -> {

            studentDao.queryData("15");

        });
    }


}