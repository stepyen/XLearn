package com.stepyen.xlearn.activity.function

import android.util.Log
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_json.*
import org.json.JSONException
import org.json.JSONObject

/**
 *
 *
 * 功能：
    1、测试 put value 为 "" 情况
    2、测试 put value 为 null 情况
    2、测试 解析的 json 中，没有所需字段时的情况

    总结
    1、put value 为"" 会提交到json中；为 null 不提交到json中
    2、解析json时，使用 get 解析到json中没有的字段会抛出异常，稳妥起见，使用 opt

 */
class JsonActivity : BasePageActivity() {

    companion object{
        const val TAG = "JsonActivityTAG"
    }

    override fun initView() {

        addView(R.layout.activity_json)

        testValueIsEmpty()
        testParseJson()
    }


    /**
     * 测试 put 值为空的情况
     */
    private fun testValueIsEmpty() {

        val jsonObject = JSONObject()

        try {
            jsonObject.put("uid", "")  // 可以
            jsonObject.put("name", "小明")

            val age: String? = null
            jsonObject.put("age", age)     // age 为 null ，不会被 put 进 Json 中

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        Log.i(TAG, "testValueIsEmpty: Json: $jsonObject")

        tv_value_is_empty.text = "Json : $jsonObject"

    }

    /**
     * 解析 json 没有所需字段情况
     */
    private fun testParseJson() {
        val jo = JSONObject()
        try {
            jo.put("name", "小明")
            jo.put("age", 17)


            val jsonObject = JSONObject(jo.toString())
            //            String address = jsonObject.getString("address");   // 会报异常 org.json.JSONException: No value for address

            val address = jsonObject.optString("address")

            Log.i(TAG, "testParseJson: address: $address")

        } catch (e: JSONException) {
            e.printStackTrace()

        }

    }


    class User {
        var name: String? = null
        var age: Int = 0
    }

}
