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
 *
 *
 * 关注：
 * 1、指定开始和结束状态
 * 2、从哪个位置开始，有三种指定方式
 *      1、绝对位置 Animation.ABSOLUTE               xml中对应：android:pivotX="50"
 *      2、自身位置  Animation.RELATIVE_TO_SELF      xml中对应：android:pivotX="50%"
 *      3、父布局位置 Animation.RELATIVE_TO_PARENT   xml中对应：android:pivotX="50%p"
 *
 * 3、其他的设置属性
 * android:duration：动画时长
 * android:fillAfter：为true，则控件动画结束时，保持结束时的状态。
 * android:fillBefore：为true，则控件动画结束时，将还原到初始化状态。
 * android:fillEnabled：有待斟酌，会第一时间补上该属性与上面两个属性的本质区别。
 * android:repeatCount：指定动画重复次数，取值infinite时,表示无限循环。
 * android:repeatMode：用于设定重复类型，有reverse和restart两个值。其中reverse:表示倒序回放；restart表示重放,并且必须与repeatCount一起使用才有效果。
 * android:interpolator:设定插值器，其实就是指定的动画效果，比如弹跳效果。
 *
 *
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

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_tween_animotion);
    }

    @Override
    protected void initViews() {

    }
    @OnClick({R.id.btn_Scale, R.id.btn_Translate, R.id.btn_Rotate, R.id.btn_Alpha, R.id.btn_Scale_xml,R.id.btn_Scale_and_translate})
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
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 2f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 2f
        );
        animation.setDuration(2000);
        mIv.startAnimation(animation);
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