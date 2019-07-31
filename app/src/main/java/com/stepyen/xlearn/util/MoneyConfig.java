package com.stepyen.xlearn.util;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import com.stepyen.xlearn.R;
import com.stepyen.xutil.common.SpanUtils;
import com.stepyen.xutil.resource.ResUtils;

/**
 * date：2019/7/31
 * author：stepyen
 * description：
 */
public class MoneyConfig {
    public static final String PREFIX_RMB = "￥";
   private boolean isSymbolScale;       // 前缀符号缩放
   private boolean isDecimalScale;       // 小数部分缩放
   private boolean isMoneyInt;       // 金额是整数
   private String prefix;      // 前缀
   private String money;

    public static CharSequence getMoney(MoneyConfig config) {
        if (config == null) {
            return "";
        }

        SpanUtils spanUtils = new SpanUtils();
        if (!TextUtils.isEmpty(config.prefix)) {
            spanUtils.append(config.prefix);
            if (config.isSymbolScale) {
                spanUtils.setFontProportion(0.8f);
            }
        }
        // 金额小数部分缩放，并且不是整数
        if (config.isDecimalScale) {
            String[] split = config.money.split("\\.");
            if (split.length ==2) {
                spanUtils.append(split[0]+".");
                spanUtils.append(split[1])
                        .setFontProportion(0.8f);
            }else{
                spanUtils.append(config.money);
            }
        } else {
            spanUtils.append(config.money);
        }
        return spanUtils.create();
    }


    private MoneyConfig(Builder builder) {
        isSymbolScale = builder.isSymbolScale;
        isDecimalScale = builder.isDecimalScale;
        isMoneyInt = builder.isMoneyInt;
        prefix = builder.prefix;
        money = builder.money;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private boolean isSymbolScale = false;
        private boolean isDecimalScale = true;
        private boolean isMoneyInt = true;
        private String prefix = "";
        private String money = "0";

        private Builder() {
        }

        public Builder isSymbolScale(boolean val) {
            isSymbolScale = val;
            return this;
        }

        public Builder isDecimalScale(boolean val) {
            isDecimalScale = val;
            isMoneyInt = false;
            return this;
        }

        public Builder isMoneyInt(boolean val) {
            isMoneyInt = val;
            isDecimalScale = false;
            return this;
        }

        public Builder prefix(String val) {
            prefix = val;
            return this;
        }

        public Builder money(String val) {
            money = TextUtils.isEmpty(val) ? money : val;
            return this;
        }

        public MoneyConfig build() {
            double doubleMoney = 0;
            try {
                doubleMoney = Double.valueOf(money);
            } catch (NumberFormatException e) {
            }

            if (isMoneyInt) {
                money = (int) doubleMoney + "";
            } else {
                money = String.format(ResUtils.getString(R.string.common_money), doubleMoney);
            }


            return new MoneyConfig(this);
        }
    }

    public boolean isSymbolScale() {
        return isSymbolScale;
    }

    public boolean isDecimalScale() {
        return isDecimalScale;
    }

    public boolean isMoneyInt() {
        return isMoneyInt;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMoney() {
        return money;
    }


    public static void main(String[] args) {
        String formatStr = "%.2f";
        String d1 = "2";
        String d2 = "2.0";
        String d3 = "2.01";
        String d4 = "2.0323213";
        String d5 = "哈哈哈";


        System.out.println(getMoney(MoneyConfig.newBuilder().build()));
        System.out.println(getMoney(MoneyConfig.newBuilder().money(d1).prefix(PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build()));
        System.out.println(getMoney(MoneyConfig.newBuilder().money(d2).isDecimalScale(true).build()));
        System.out.println(getMoney(MoneyConfig.newBuilder().money(d3).prefix(PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build()));
        System.out.println(getMoney(MoneyConfig.newBuilder().money(d4).prefix(PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build()));
        System.out.println(getMoney(MoneyConfig.newBuilder().money(d5).prefix(PREFIX_RMB).isDecimalScale(true).isSymbolScale(true).build()));
    }

}
