package com.stepyen.xlearn.activity.function

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_uri.*

/**
 *
 */
class UriActivity : BasePageActivity() {


    override fun initView() {

        addView(R.layout.activity_uri)
        testUri()
    }


    private fun testUri() {
        val sb = StringBuilder()

        val uriStr = "http://www.baidu.com:8080/wenku/jiatiao.html?id=123456&name=jack#fragment=123"
        val uri = Uri.parse(uriStr)

        sb.append("Uri: $uriStr\n")
        sb.append("\n")
        sb.append("Scheme: " + uri.scheme + "\n")
        sb.append("Host: " + uri.host + "\n")
        sb.append("Port: " + uri.port + "\n")
        sb.append("Path: " + uri.path + "\n")
        sb.append("Query: " + uri.query + "\n")
        sb.append("Fragment: " + uri.fragment + "\n")
        sb.append("\n\n")
        sb.append("获取每一部分的Path: \n")
        val pathSegments = uri.pathSegments
        for (path in pathSegments) {
            sb.append(path + "\n")
        }
        sb.append("\n")
        tv_uri.text = sb.toString()

    }


}
