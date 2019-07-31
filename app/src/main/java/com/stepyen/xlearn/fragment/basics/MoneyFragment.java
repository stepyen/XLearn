package com.stepyen.xlearn.fragment.basics;

import android.content.Intent;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.basics.activity.AliasActivity;
import com.stepyen.xlearn.util.MoneyConfig;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "金额", extra = R.drawable.ic_widget_imageview)
public class MoneyFragment extends BaseTestFragment {

    @Override
    public void initLayoutView() {
        String d1 = "2";
        String d2 = "2.0";
        String d3 = "2.01";
        String d4 = "2.0323213";
        String d5 = "哈哈哈";

        CharSequence str1 = MoneyConfig.getMoney(MoneyConfig.newBuilder().build());
        CharSequence str2 = MoneyConfig.getMoney(MoneyConfig.newBuilder().money(d1).prefix(MoneyConfig.PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build());
        CharSequence str3 = MoneyConfig.getMoney(MoneyConfig.newBuilder().money(d2).prefix(MoneyConfig.PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build());
        CharSequence str4 = MoneyConfig.getMoney(MoneyConfig.newBuilder().money(d3).isDecimalScale(true).isSymbolScale(true).build());
        CharSequence str5 = MoneyConfig.getMoney(MoneyConfig.newBuilder().money(d4).prefix(MoneyConfig.PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build());
        CharSequence str6 = MoneyConfig.getMoney(MoneyConfig.newBuilder().money(d5).prefix(MoneyConfig.PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build());


       addTextView(str1);
       addTextView(str2);
       addTextView(str3);
       addTextView(str4);
       addTextView(str5);
       addTextView(str6);

    }

    @Override
    protected void initViews() {

    }



}