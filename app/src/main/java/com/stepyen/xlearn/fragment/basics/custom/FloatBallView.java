package com.stepyen.xlearn.fragment.basics.custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.stepyen.xlearn.utils.DisPlayUtil;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * date：2018/12/14
 * author：yfj
 * description：首页的浮动View
 *
 * <p>
 * 1、view 跟随手指移动
 * 2、view 在屏幕中间，自动吸附到屏幕边上
 * 3、点击 view，移动到屏幕正中间
 * 4、放开 view,自动吸附到屏幕边上
 */
public class FloatBallView extends AppCompatImageView {
    private int NAVIGATION_HEIGHT;  // 底部导航栏的高度
    private int TOOLBAR_HEIGHT;      // 首页toolbar高度

    private static final long ANIMOTION_DURATION_TIME = 300L;   // 动画执行时间
    private Context mContext;

    private int screenWidth = 0; // 屏幕的宽
    private int screenHeight = 0; // 屏幕的高
    private int limitTop = 0;   //view 上面限制
    private int limitBottom = 0; // view 下面限制

    private int width = 0;  // view 的宽
    private int height = 0; // view 的高
    private int showWidth = 0; // view 吸边时显示的宽
    private int hideWidth = 0; // view 吸边时隐藏的宽

    private int leftX = 0;  // 左边贴边位置
    private int rightX = 0;  // 右边贴边位置
    private int centerX = 0;    // X 中间位置
    private int centerY = 0;    // Y中间位置

    private float touchX;// 触摸时的 x 坐标
    private float touchY; // 触摸时的 y 坐标

    private long startTime = 0; // 手指开始 点击的时间
    private long endTime = 0; // 手指离开的时间

    private boolean activeClick = true; // 是否响应点击事件

    public FloatBallView(Context context) {
        super(context);
    }

    public FloatBallView(Context context,  AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        NAVIGATION_HEIGHT = DisPlayUtil.dp2px(mContext, 50);
        TOOLBAR_HEIGHT = DisPlayUtil.dp2px(mContext, 64);

        screenWidth = DisPlayUtil.getScreenWidth(mContext);
        screenHeight = DisPlayUtil.getScreenHeight(mContext);

        limitTop = TOOLBAR_HEIGHT + 5;
        limitBottom = screenHeight - NAVIGATION_HEIGHT - 5;
    }

    /**
     * 设置响应点击事件
     *
     * @param activeClick
     */
    public void setActiveClick(boolean activeClick) {
        this.activeClick = activeClick;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        showWidth = (int) (4.0 / 5 * width);
        hideWidth = (int) (1.0 / 5 * width);

        leftX = -hideWidth;
        rightX = screenWidth - showWidth;
        centerX = (screenWidth - width) / 2;
        centerY = (screenHeight - height) / 2;

        // 设置view 初始的位置
        setX(rightX);
        setY(centerY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                clearAnimation();
                startTime = System.currentTimeMillis();

                touchX = event.getX();
                touchY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:

                float nowX = event.getRawX() - touchX;  // view 左上角到屏幕左边的距离
                float nowY = event.getRawY() - touchY;
                nowX = nowX < 0 ? 0 : (nowX + width > screenWidth) ? (screenWidth - width) : nowX;  //
                nowY = nowY < 0 ? 0 : nowY;

                // 限制 view 的活动区域
                if (nowY < limitTop) {
                    nowY = limitTop;
                }

                if (nowY > limitBottom - height) {
                    nowY = limitBottom - height;
                }

                setY(nowY);
                setX(nowX);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                endTime = System.currentTimeMillis();
                // 按下到抬起在时间范围内，并且没有拖动行为，就是点击事件
                boolean isDoClick = (endTime - startTime) < 0.08 * 1000L;
                if (isDoClick && activeClick) {
                    doClick();
                } else {
                    //这里做动画贴边效果
                    float centerX = getX() + width / 2;
                    if (centerX > screenWidth / 2) {
                        // 移到右边
                        adsorbRight();
                    } else {
                        // 移到左边
                        adsorbLeft();
                    }
                }

                return true;
            default:
                return super.onTouchEvent(event);
        }
    }


    /**
     * 摆动动画
     */
    private ObjectAnimator swingAnimotion() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "rotation", 0, 15, -15, 15, 0);
        animator.setDuration(ANIMOTION_DURATION_TIME);
        return animator;
    }

    /**
     * 吸附到屏幕左边
     */
    private void adsorbLeft() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationX",
                getX(), leftX)
                .setDuration(ANIMOTION_DURATION_TIME);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(swingAnimotion())
                .after(objectAnimator);
        animatorSet.start();
    }

    /**
     * 吸附到屏幕右边
     */
    private void adsorbRight() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationX",
                getX(), rightX)
                .setDuration(ANIMOTION_DURATION_TIME);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(swingAnimotion())
                .after(objectAnimator);
        animatorSet.start();
    }

    /**
     * 触发点击事件
     */
    private void doClick() {
        if (mOnClickListen != null) {

            // 将View 移到在屏幕中间
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(this, "translationX",
                    getX(), centerX)
                    .setDuration(ANIMOTION_DURATION_TIME);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(this, "translationY",
                    getY(), centerY)
                    .setDuration(ANIMOTION_DURATION_TIME);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimatorX)
                    .with(objectAnimatorY)
                    .before(swingAnimotion());

            animatorSet.start();

            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mOnClickListen.onClick(FloatBallView.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    /**
     * 从中间位置统一回到屏幕右边中间
     */
    public void returnDeult() {
        // 将View 移到在屏幕中间
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(this, "translationX",
                getX(), rightX);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(this, "translationY",
                getY(), centerY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMOTION_DURATION_TIME)
                .setStartDelay(100);

        animatorSet.play(objectAnimatorX)
                .with(objectAnimatorY)
                .before(swingAnimotion());
        animatorSet.start();
    }

    public interface OnClickListen {
        void onClick(View view);
    }

    private OnClickListen mOnClickListen;

    public void setOnClickListen(OnClickListen onClickListen) {
        mOnClickListen = onClickListen;
    }
}
