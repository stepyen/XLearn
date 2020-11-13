package com.stepyen.xlearn.activity.function.aviod_result.test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.stepyen.common.utils.L
import com.stepyen.xlearn.R
import com.stepyen.xlearn.activity.function.aviod_result.AvoidOnResult
import kotlinx.android.synthetic.main.activtity_avoid_result.*

/**
 * date：2020/11/12
 * author：stepyen
 * description：
 *
 */
class TestAvoidResultFragment : Fragment() {

    companion object{
        fun newInstance():TestAvoidResultFragment{
            val args = Bundle()

            val fragment = TestAvoidResultFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_avoid_result,container) as ViewGroup
        view.findViewById<Button>(R.id.testBtn).setOnClickListener {

            val intent = Intent(context, TestAvoidResultActivity::class.java)

            AvoidOnResult(activity)
                    .startForResult(intent, TestAvoidResultActivity.REQUEST_CODE) { requestCode, resultCode, data ->
                        L.d(AvoidResultActivity.TAG, "requestCode:$requestCode    resultCode:$resultCode     data:${data.getStringExtra(TestAvoidResultActivity.EXTRA_KEY_NAME)}")

                    }
        }

        return view
    }



}