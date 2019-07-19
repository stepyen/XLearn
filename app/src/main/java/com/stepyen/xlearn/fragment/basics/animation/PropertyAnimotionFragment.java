package com.stepyen.xlearn.fragment.basics.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "属性动画")
public class PropertyAnimotionFragment extends BaseTestFragment {

    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_property_animotion);
    }

    @Override
    protected void initViews() {
        addView("ValueAnimator 实现Scale", v -> {
            ValueAnimator animator = ValueAnimator.ofFloat(1, 0.5f, 1);
            animator.setDuration(3000);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    mIv.setScaleX(value);
                }
            });
            animator.start();
        });

        addView("ObjectAnimator 实现Scale", v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mIv, "scaleX", 1, 0.5f, 1);
            animator.setDuration(3000);
            animator.start();
        });

        addView("PropertyValuesHolder 实现Scale 多个动画效果", v -> {
            PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f, 1.0f);
            PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f, 1.0f);

            ObjectAnimator.ofPropertyValuesHolder(mIv, xHolder, yHolder)
                    .setDuration(3000)
                    .start();
        });

        addView("AnimatorSet 实现多个动画效果", v -> {
            ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mIv, "scaleX", 1.0f, 0.5f, 1.0f);
            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mIv, "scaleY", 1.0f, 0.5f, 1.0f);

            AnimatorSet animator = new AnimatorSet();
            animator.playTogether(xAnimator, yAnimator);    // 一起执行
            animator.setDuration(3000);
            animator.start();
        });


        addView("AnimatorSet 按顺序执行多个 playSequentially", v -> {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIv, "alpha", 1.0f, 0.5f, 1.0f);
            animator1.setDuration(2000);

            ObjectAnimator xAnimator2 = ObjectAnimator.ofFloat(mIv, "scaleX", 1.0f, 0.5f, 1.0f);
            ObjectAnimator yAnimator2 = ObjectAnimator.ofFloat(mIv, "scaleY", 1.0f, 0.5f, 1.0f);
            AnimatorSet scaleAnimator = new AnimatorSet();
            scaleAnimator.playTogether(xAnimator2, yAnimator2);
            scaleAnimator.setDuration(2000);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(mIv, "rotation", 0f, 360f);
            animator3.setDuration(2000);

            AnimatorSet animator = new AnimatorSet();
            animator.playSequentially(animator1, scaleAnimator, animator3);       //Sequentially  按顺序执行

            animator.start();
        });

        addView("AnimatorSet 控制动画执行顺序", v -> {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIv, "alpha", 1.0f, 0.5f, 1.0f);
            animator1.setDuration(2000);

            ObjectAnimator xAnimator2 = ObjectAnimator.ofFloat(mIv, "scaleX", 1.0f, 0.5f, 1.0f);
            xAnimator2.setDuration(2000);

            ObjectAnimator yAnimator2 = ObjectAnimator.ofFloat(mIv, "scaleY", 1.0f, 0.5f, 1.0f);
            yAnimator2.setDuration(2000);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(mIv, "rotation", 0f, 360f);
            animator3.setDuration(2000);

            AnimatorSet animator = new AnimatorSet();
            // 执行顺序 animator1  -> xAnimator2和yAnimator2  -> animator3
            animator.play(xAnimator2).with(yAnimator2).after(animator1).before(animator3);
            animator.start();
        });


          addView("Keyframes",v->{
              Keyframe keyframe1 = Keyframe.ofFloat(0f, 1.0f);
              Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.5f);
              Keyframe keyframe3 = Keyframe.ofFloat(0.4f, 1.0f);
              Keyframe keyframe4 = Keyframe.ofFloat(0.7f, 0f);
              Keyframe keyframe5 = Keyframe.ofFloat(1.0f, 1.0f);

              PropertyValuesHolder xHolder = PropertyValuesHolder.ofKeyframe("scaleX",
                      keyframe1, keyframe2, keyframe3, keyframe4, keyframe5);
              PropertyValuesHolder yHolder = PropertyValuesHolder.ofKeyframe("scaleY",
                      keyframe1, keyframe2, keyframe3, keyframe4, keyframe5);
              ObjectAnimator.ofPropertyValuesHolder(mIv, xHolder, yHolder)
                      .setDuration(3000)
                      .start();
                  });

          addView("TypeEvaluator",v->{
              TypeEvaluator<Float> typeEvaluator = new TypeEvaluator<Float>() {
                  FloatEvaluator evaluator = new FloatEvaluator();
                  @Override
                  public Float evaluate(float fraction, Float startValue, Float endValue) {
                      if (fraction <= 0.2f) {
                          return evaluator.evaluate(fraction/0.2f, 1.0f, 0.5f);
                      } else if (fraction <= 0.4f) {
                          return evaluator.evaluate((fraction - 0.2f)/0.2f, 0.5f, 1.0f);
                      } else if (fraction < 0.8f) {
                          return evaluator.evaluate((fraction - 0.4f)/0.4f, 1.0f, 0f);
                      } else if (fraction <= 1) {
                          return evaluator.evaluate((fraction - 0.8f)/0.2f, 0f, 1.0f);
                      }
                      return endValue;
                  }
              };
              PropertyValuesHolder xHolder = PropertyValuesHolder.ofObject("scaleX", typeEvaluator, 0f, 1.0f);
              PropertyValuesHolder yHolder = PropertyValuesHolder.ofObject("scaleY", typeEvaluator, 0f, 1.0f);
              ObjectAnimator.ofPropertyValuesHolder(mIv, xHolder, yHolder)
                      .setDuration(3000)
                      .start();
                  });
    }
}