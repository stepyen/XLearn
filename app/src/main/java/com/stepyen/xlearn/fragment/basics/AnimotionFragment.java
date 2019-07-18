package com.stepyen.xlearn.fragment.basics;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "动画", extra = R.drawable.ic_widget_imageview)
public class AnimotionFragment extends BaseTestFragment {
    ImageView mIvAnimationScale;

    @Override
    public void initLayoutView() {
        View view = addView(R.layout.fragment_animotion);
        mIvAnimationScale = view.findViewById(R.id.iv_animation_scale);
    }

    @Override
    protected void initViews() {

        addView("放大动画",v->{
            // 放大动画
            ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(500);
            mIvAnimationScale.startAnimation(animation);
        });

        addView("放大动画",v->{
            // 放大动画
            ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1,
                    Animation.ABSOLUTE, 0.5f,
                    Animation.ABSOLUTE, 0.5f);
            animation.setDuration(500);
            mIvAnimationScale.startAnimation(animation);
        });
    }
}