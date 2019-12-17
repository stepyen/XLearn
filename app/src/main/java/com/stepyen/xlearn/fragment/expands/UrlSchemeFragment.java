package com.stepyen.xlearn.fragment.expands;

import android.content.ActivityNotFoundException;
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
 * description：
 */
@Page(name = "scheme跳转", extra = R.drawable.ic_widget_imageview)
public class UrlSchemeFragment extends BaseFragment {

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
        String uri = "stepyen://xiaoming@host.com/record/path?address=china&phone=1875912233#fragment=fragment123";
        tv.setText("测试uri: " + uri);

        findViewById(R.id.btn_jump_fixation).setOnClickListener(v -> {

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                ToastUtils.toast("Activity 未找到");
            }
        });


        try {

            TextView tv_html_uri = findViewById(R.id.tv_html_uri);

            StringBuilder sb = new StringBuilder();
            sb.append("<a href='");
            sb.append(uri);
            sb.append("'>点击跳转</a>");

            tv_html_uri.setText(Html.fromHtml(sb.toString()));
            tv_html_uri.setMovementMethod(LinkMovementMethod.getInstance());

        } catch (ActivityNotFoundException e) {
            ToastUtils.toast("Activity 未找到");
        }


    }

}