package com.stepyen.xlearn.fragment.basics.UI.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * date：2020-01-07
 * author：stepyen
 * description：
 */
public class ImageBanner extends BaseBanner<Integer, ImageBanner> {

    public ImageBanner(Context context) {
        this(context, null);
    }

    public ImageBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    @Override
    public View onCreateItemView(int position) {
        ImageView iv = new ImageView(mContext);
        Integer integer = getItem(position);
        Drawable drawable;
        if (integer!=null) {
            drawable = mContext.getResources().getDrawable(integer);
        }else{
            drawable = new ColorDrawable(Color.parseColor("#0000000"));
        }
        iv.setImageDrawable(drawable);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);

        return iv;
    }
}
