package com.stepyen.xlearn.base;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.stepyen.xlearn.R;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.resource.ResUtils;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xpage.utils.TitleUtils;

import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
public  abstract class BaseTestFragment extends BaseFragment {
    protected LinearLayout mParentLl;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_test;
    }

    @Override
    protected void initPage() {
        mParentLl = findViewById(R.id.ll_parent);
        initTitle();
        initLayoutView();
        initViews();
        initListeners();
    }

    public abstract void initLayoutView();

    public void addView(String title, final Class<?> cls) {
        addView(title, v->{
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, 0);
    }


    public void addView(String title, final Class<?> cls, int marginTop) {
        addView(title, v->{
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, marginTop);
    }

    public void addView(String title, View.OnClickListener clickListener) {
        addView(title,clickListener,0 );
    }

    public void addView(String title, View.OnClickListener clickListener, int marginTop) {
        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setPadding(DensityUtils.dp2px(15), 0, 0,0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(47));
        tv.setLayoutParams(lp);
        tv.setBackgroundColor(ResUtils.getColor(R.color.white));
        tv.setText(title);
        tv.setOnClickListener(clickListener);
        mParentLl.addView(tv);
        setLpMarginTop(tv, marginTop);

        // 加条线
        View lineView = new View(getContext());
        LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(ResUtils.getDimens(R.dimen.line)));
        lineView.setLayoutParams(lineLp);
        lineView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.line));
        mParentLl.addView(lineView);

    }

    public TextView addTextView(CharSequence txt, int marginTop) {
        TextView tv = new TextView(getContext());
        tv.setText(txt);
        mParentLl.addView(tv);
        setLpMarginTop(tv, marginTop);

        return tv;
    }

    private void setLpMarginTop(View view, int marginTop) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (lp != null) {
            lp.setMargins(0, DensityUtils.dp2px(mActivity, marginTop), 0, 0);
            view.setLayoutParams(lp);
        }
    }

    public void addTextView(CharSequence txt) {
        addTextView(txt, 0);
    }

    public View addView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, mParentLl,true);
        return view;
    }

    public View addView(View view) {
        mParentLl.addView(view);
        return view;
    }

    public void onViewClick(View view) {

    }

    public void showToast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }

}
