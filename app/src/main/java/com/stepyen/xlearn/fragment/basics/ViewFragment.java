package com.stepyen.xlearn.fragment.basics;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "View",extra = R.drawable.ic_widget_imageview)
public class ViewFragment extends BaseFragment {
    @BindView(R.id.tv1)
    TextView mTv1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmen_view;
    }

    @Override
    protected void initViews() {
        expandTouchArea(mTv1);
    }


    @OnClick({R.id.intercept_ll,
            R.id.btn_toast,
            R.id.ll_toast,
            R.id.tv1,
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.intercept_ll:
                Toast.makeText(mActivity, "intercept_ll", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_toast:
                Toast.makeText(mActivity, "hahaha", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_toast:
                Toast.makeText(mActivity, "父布局哈哈哈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv1:
                ToastUtils.toast("点击1");
                break;
        }

    }


    public static void expandTouchArea(View view) {
        expandTouchArea(view, 20);
    }

    public static void expandTouchArea(View view, int size) {
        View parentView = (View) view.getParent();
        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);

                rect.top -= size;
                rect.bottom += size;
                rect.left -= size;
                rect.right += size;

                parentView.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });
    }
}