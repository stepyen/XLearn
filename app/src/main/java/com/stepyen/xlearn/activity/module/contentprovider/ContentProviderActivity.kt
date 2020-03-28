package com.stepyen.xlearn.activity.module.contentprovider

import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_content_provider.*

/**
 * date：2020-02-28
 * author：stepyen
 * description：
 *
 */
class ContentProviderActivity : BasePageActivity() {
    override fun initView() {
        addView(R.layout.activity_content_provider)


        val dbHelper = DBHelper(this)
        val sqLiteDatabase = dbHelper.writableDatabase
        val studentDao = StudentDao(sqLiteDatabase)


        btn_insert.setOnClickListener {
            studentDao.insertData("小明", "12")
            studentDao.insertData("小绿", "15")
            studentDao.insertData("小哈", "18")
            studentDao.insertData("小花", "21")
        }
        btn_delete.setOnClickListener {
            studentDao.deleteData("小花")
        }
        btn_update.setOnClickListener {
            studentDao.updateData("小绿", "89")
        }
        btn_query.setOnClickListener {
            studentDao.queryData("15")
        }


        contentResolver
    }
}