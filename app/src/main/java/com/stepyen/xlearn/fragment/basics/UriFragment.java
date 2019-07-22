package com.stepyen.xlearn.fragment.basics;

import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "Uri", extra = R.drawable.ic_widget_imageview)
public class UriFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_uri;
    }

    @Override
    protected void initViews() {
        testUri();
    }

    private void testUri() {
        StringBuilder sb = new StringBuilder();

        String uriStr = "http://www.baidu.com:8080/wenku/jiatiao.html?id=123456&name=jack#fragment=123";
        Uri uri = Uri.parse(uriStr);

        sb.append("Uri: " + uriStr + "\n");
        sb.append("\n");
        sb.append("Scheme: " +  uri.getScheme() + "\n");
        sb.append("Host: " + uri.getHost() + "\n");
        sb.append("Port: " + uri.getPort() + "\n");
        sb.append("Path: " + uri.getPath() + "\n");
        sb.append("Query: " + uri.getQuery() + "\n");
        sb.append("Fragment: " + uri.getFragment() + "\n");
        sb.append("\n\n");
        sb.append("获取每一部分的Path: \n");
        List<String> pathSegments = uri.getPathSegments();
        for (String path : pathSegments) {
            sb.append(path+"\n");
        }
        sb.append("\n");
        mTv.setText(sb.toString());

    }

}