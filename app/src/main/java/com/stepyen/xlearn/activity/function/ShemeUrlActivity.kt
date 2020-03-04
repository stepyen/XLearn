package com.stepyen.xlearn.activity.function

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.method.LinkMovementMethod
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import com.stepyen.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_url_scheme.*

/**
 * date：2020-03-04
 * author：stepyen
 * description：ShemeUrl 学习
 *
 */
class ShemeUrlActivity : BasePageActivity() {

    companion object{
        const val test_uri = "stepyen://xiaoming@host.com/record/path?address=china&phone=1875912233#fragment=fragment123"

    }

    override fun initView() {
        addView(R.layout.activity_url_scheme)

        btn_jump_input.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(et_uri.text.toString().trim { it <= ' ' })
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                ToastUtils.toast("Activity 未找到")
            }
        }


        tv_uri.text = "测试uri: \n$test_uri"
        btn_jump_fixation.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(test_uri)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                ToastUtils.toast("Activity 未找到")
            }
        }



        try {
            val sb = StringBuilder()
            sb.append("<a href='")
            sb.append(test_uri)
            sb.append("'>点击跳转</a>")
            tv_test_html_uri.text = "网页测试 uri：\n$sb"
            tv_html_uri.text = Html.fromHtml(sb.toString())
            tv_html_uri.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: ActivityNotFoundException) {
            ToastUtils.toast("Activity 未找到")
        }

        try {
            val babybusUri = "characters://{\"type\":\"gogamepage\",\"data\":\"{\"age\":17}\"}"

            val sb = StringBuilder()
            sb.append("<a href='")
            sb.append(babybusUri)
            sb.append("'>点击跳转</a>")
            tv_test_babybus_uri.text = "网页测试 uri：\n$sb"
            tv_babybus_uri.text = Html.fromHtml(sb.toString())
            tv_babybus_uri.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }



    }
}