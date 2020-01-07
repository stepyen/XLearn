package com.stepyen.xlearn.fragment.basics.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 * <p>
 * 补间动画: 指定开始和结束的状态，中间的状态由系统自动完成。
 * 具体四种动画：
        Alpha Animation：透明度动画
        Scale Animation：缩放动画
        Translate Animation：平移动画
        Rotate Animation：旋转动画
 */
@Page(name = "补间动画")
public class TweenAnimotionFragment extends BaseTestFragment {
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.btn_Scale)
    Button mBtnScale;
    @BindView(R.id.btn_Translate)
    Button mBtnTranslate;
    @BindView(R.id.btn_Rotate)
    Button mBtnRotate;
    @BindView(R.id.btn_Alpha)
    Button mBtnAlpha;
    @BindView(R.id.btn_Scale_xml)
    Button mBtnScaleXml;


    private TranslateAnimation translateAnimation;

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_tween_animotion);
    }

    @Override
    protected void initViews() {

    }
    @OnClick({R.id.btn_Scale,
            R.id.btn_Translate,
            R.id.btn_Rotate,
            R.id.btn_Alpha,
            R.id.btn_Scale_xml,
            R.id.btn_Scale_and_translate,
            R.id.btn_cancle_translate,
            R.id.btn_reset_translate

    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Scale:
                scale();
                break;
            case R.id.btn_Translate:
                translate();
                break;
            case R.id.btn_Rotate:
                rotate();
                break;
            case R.id.btn_Alpha:
                alpha();
                break;
            case R.id.btn_Scale_xml:
                scaleXml();
                break;
            case R.id.btn_Scale_and_translate:
                scaleAndTranslate();
                break;
            case R.id.btn_cancle_translate:
                translateAnimation.cancel();
                break;
            case R.id.btn_reset_translate:
                translateAnimation.reset();
                break;

        }
    }



    /**
     * 缩放大小从0变成1，相对于自身的中心位置，时间500毫秒
     */
    private void scale() {
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        mIv.startAnimation(animation);
    }


    private void translate() {
        translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        translateAnimation.setDuration(3000);
        mIv.startAnimation(translateAnimation);
    }

    private void rotate() {
        RotateAnimation animation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        animation.setDuration(500);
        mIv.startAnimation(animation);
    }

    private void alpha() {
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(2000);
        mIv.startAnimation(animation);
    }

    private void scaleXml() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale);
        mIv.startAnimation(animation);
    }

    private void scaleAndTranslate() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_set_scale_and_translate);
        mIv.startAnimation(animation);
    }
}