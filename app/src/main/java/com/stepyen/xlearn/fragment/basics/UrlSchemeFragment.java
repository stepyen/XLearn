package com.stepyen.xlearn.fragment.basics;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：url scheme 跳转学习
 */
@Page(name = "url scheme", extra = R.drawable.ic_widget_imageview)
public class UrlSchemeFragment extends BaseFragment {

    String test_uri = "stepyen://xiaoming@host.com/record/path?address=china&phone=1875912233#fragment=fragment123";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_url_scheme;
    }

    @Override
    protected void initViews() {
        EditText etUri = findViewById(R.id.et_uri);

        findViewById(R.id.btn_jump_input).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(etUri.getText().toString().trim()));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                ToastUtils.toast("Activity 未找到");
            }

        });

        TextView tv = findViewById(R.id.tv_uri);
        tv.setText("测试uri: \n" + test_uri);


        findViewById(R.id.btn_jump_fixation).setOnClickListener(v -> {

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(test_uri));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                ToastUtils.toast("Activity 未找到");
            }
        });


        try {

            TextView tv_html_uri = findViewById(R.id.tv_html_uri);
            TextView tv_test_html_uri = findViewById(R.id.tv_test_html_uri);

            StringBuilder sb = new StringBuilder();
            sb.append("<a href='");
            sb.append(test_uri);
            sb.append("'>点击跳转</a>");

            tv_test_html_uri.setText("网页测试 uri：\n"+sb.toString());

            tv_html_uri.setText(Html.fromHtml(sb.toString()));
            tv_html_uri.setMovementMethod(LinkMovementMethod.getInstance());

        } catch (ActivityNotFoundException e) {
            ToastUtils.toast("Activity 未找到");
        }


        try {
            String babybusUri = "com.kid58.tiyong.characters://{\"type\":\"gogamepage\",\"data\":\"{\"age\":17}\"}";
            TextView tv_test_babybus_uri = findViewById(R.id.tv_test_babybus_uri);
            TextView tv_babybus_uri = findViewById(R.id.tv_babybus_uri);

            StringBuilder sb = new StringBuilder();
            sb.append("<a href='");
            sb.append(babybusUri);
            sb.append("'>点击跳转</a>");

            tv_test_babybus_uri.setText("网页测试 uri：\n"+sb.toString());

            tv_babybus_uri.setText(Html.fromHtml(sb.toString()));
            tv_babybus_uri.setMovementMethod(LinkMovementMethod.getInstance());

        } catch (Exception e) {
            e.printStackTrace();

        }



        findViewById(R.id.btn_open_main).setOnClickListener(v->{


        });


        findViewById(R.id.btn_open_scheme).setOnClickListener(v->{

        });


    }

}