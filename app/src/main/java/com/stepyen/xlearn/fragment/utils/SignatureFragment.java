package com.stepyen.xlearn.fragment.utils;

import android.content.pm.Signature;

import com.stepyen.xlearn.App;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xutil.app.AppUtils;
import com.xuexiang.xpage.annotation.Page;

import java.security.MessageDigest;

/**
 * date：2019/7/10
 * author：stepyen
 * description：Android apk 签名问题研究
 * <p>
 * <p>
 * 1、app在启动的时候应该加入签名证书自验证，防止被反编译后二次打包
 */
@Page(name = "apk签名", extra = R.drawable.ic_widget_imageview)
public class SignatureFragment extends BaseTestFragment {

    @Override
    public void addView() {

        addTextView("App 签名：" + AppUtils.getAppSignatureMD5());
        addTextView("App SHA1签名：" + AppUtils.getAppSignatureSHA1(), 10);
    }


}
