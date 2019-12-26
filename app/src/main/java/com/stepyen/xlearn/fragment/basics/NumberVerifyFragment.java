package com.stepyen.xlearn.fragment.basics;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.widget.linearlayout.flow_tag.FlowTagLayout;
import com.stepyen.xutil.shape.ShapeBuilder;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import androidx.core.content.ContextCompat;

/**
 * date：2019/7/5
 * author：stepyen
 * description：url scheme 跳转学习
 */
@Page(name = "数字校验", extra = R.drawable.ic_widget_imageview)
public class NumberVerifyFragment extends BaseFragment {
    private String[] complexFontArray = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private ArrayList<Integer> verifyNumberList = new ArrayList();
    private LinearLayout mVerifyLL;
    private FlowTagLayout mInputFtl;
    private int verifyNumberSize = 3;
    private int currentCheckIndex = 0;  // 当前校验的数字

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_number_verify;
    }

    @Override
    protected void initViews() {
        mVerifyLL = findViewById(R.id.ll_verify);
        mInputFtl = findViewById(R.id.ll_input);

        randomObtainNumber();
        initVerifyNumber();
        initInputView();


        TextView tv_test = findViewById(R.id.tv_test);

    }


    /**
     * 随机获取3个数字
     */
    private void randomObtainNumber() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Random random = new Random();
        int size = list.size();
        Set<Integer> totals = new HashSet<>();
        while (totals.size() < verifyNumberSize) {
            totals.add(list.get(random.nextInt(size)));
        }

        verifyNumberList.clear();
        Iterator iterator = totals.iterator();
        while (iterator.hasNext()) {
            int next = (int) iterator.next();
            verifyNumberList.add(next);
        }
    }


    private void setVerifyNumberCheck(TextView tv, boolean isCheck) {
        int bgColor ;
        int textColor;
        if (isCheck) {
            bgColor = R.color.verify_number_bg_check;
            textColor = R.color.verify_number_text_check;
        }else{
            bgColor = R.color.verify_number_bg_default;
            textColor = R.color.verify_number_text_default;
        }

        ShapeBuilder.create(getContext())
                .shape(GradientDrawable.OVAL)
                .solid(bgColor)
                .build(tv);

        tv.setTextColor(ContextCompat.getColor(getContext(),textColor));
    }


    private void initVerifyNumber() {
        mVerifyLL.removeAllViews();
        for (int i = 0; i < verifyNumberSize; i++) {
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtils.dp2px(68), DensityUtils.dp2px(68));
            tv.setLayoutParams(lp);
            String text = complexFontArray[verifyNumberList.get(i)];
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,DensityUtils.dp2px(38));
            setVerifyNumberCheck(tv, false);
            mVerifyLL.addView(tv);
        }
    }

    private void resetVerifyNumber() {
        int size = mVerifyLL.getChildCount();
        for (int i = 0; i < size; i++) {
            TextView tv = (TextView) mVerifyLL.getChildAt(i);
            setVerifyNumberCheck(tv, false);
        }
    }

    private boolean verifyNumber(int index, int checkNumber) {
        return verifyNumberList.get(index) == checkNumber;
    }



    private void initInputView() {
        mInputFtl.setItems("1", "2","3","4","5","6","7","8","9","0");
        mInputFtl.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                int number ;
                if (position<9) {
                    number = position + 1;
                }else{
                    number = 0;
                }

                boolean isVerity = verifyNumber(currentCheckIndex, number);
                if (isVerity) {
                    setVerifyNumberCheck((TextView) mVerifyLL.getChildAt(currentCheckIndex), true);
                    // 校验到最后一个数字了
                    if (currentCheckIndex == verifyNumberSize-1) {
                        ToastUtils.toast("全部校验成功");

                        return;
                    }

                    currentCheckIndex += 1;
                }else{
                    resetVerifyNumber();
                    resetInputView();
                    currentCheckIndex = 0;
                }
            }
        });

    }

    private void resetInputView() {
        mInputFtl.setItems("1", "2","3","4","5","6","7","8","9","0");
    }

}