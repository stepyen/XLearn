package com.stepyen.xlearn.fragment.basics.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 *
 * AnimationDrawable
 *
 *
 *
 *
 */
@Page(name = "帧动画")
public class FrameAnimotionFragment extends BaseTestFragment {

    @BindView(R.id.iv_animation_scale)
    ImageView mIvAnimationScale;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.btn_switch)
    Button mBtnSwitch;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    private boolean isXml = true;

    private AnimationDrawable animationDrawable;

    @Override
    public void initLayoutView() {
        addView(R.layout.fragment_frame_animotion);
    }

    @Override
    protected void initViews() {
        if (isXml) {
            animationDrawable = (AnimationDrawable) mIvAnimationScale.getDrawable();
        }else{
            animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(false);
            for (int i = 0; i < 77; i++) {
                int drawableId = getResources().getIdentifier("ic_frame_anim_" + i + 1, "drawable", getContext().getPackageName());
                Drawable drawable = getResources().getDrawable(drawableId);
                animationDrawable.addFrame(drawable, 100);
            }
        }

        mTvStatus.setText("当前为Xml实现");
    }


    @OnClick({R.id.btn_switch, R.id.btn_start, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_switch:
                isXml = !isXml;
                if (isXml) {
                    mTvStatus.setText("当前为Xml实现");
                }else{
                    mTvStatus.setText("当前为Java实现");
                }
                break;
            case R.id.btn_start:
                if (animationDrawable != null) {
                    animationDrawable.stop();
                    animationDrawable.start();
                }
                break;
            case R.id.btn_stop:
                if (animationDrawable != null) {
                    animationDrawable.stop();
                }
                break;
        }
    }
}