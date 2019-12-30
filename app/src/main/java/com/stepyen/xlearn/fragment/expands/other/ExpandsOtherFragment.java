package com.stepyen.xlearn.fragment.expands.other;

import android.util.Log;
import android.widget.TextView;

import com.bun.miitmdid.core.IIdentifierListener;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.supplier.IdSupplier;
import com.stepyen.xlearn.App;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 *
 *
 *
 */
@Page(name = "其他", extra = R.drawable.ic_widget_imageview)
public class ExpandsOtherFragment extends BaseFragment {
    private static final String TAG = "ExpandsOtherFragment";
    @BindView(R.id.tv_ooid)
    TextView tv_ooid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expands_other;
    }

    @Override
    protected void initViews() {
        getOoid();
    }

    private void getOoid() {
        // 反射调用
        MdidSdkHelper.InitSdk(getContext(), true, new IIdentifierListener() {
            @Override
            public void OnSupport(boolean b, IdSupplier supplier) {

                App.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (supplier.isSupported()) {
                            tv_ooid.setText("支持获取ooid，为 "+supplier.getOAID());
                            Log.i(TAG, "run: oaid    "+supplier.getOAID());
                            Log.i(TAG, "run: vaid    "+supplier.getVAID());
                            Log.i(TAG, "run: aaid    "+supplier.getAAID());
                        }else{
                            tv_ooid.setText("不支持获取ooid");
                        }
                    }
                });
            }
        });
    }

}