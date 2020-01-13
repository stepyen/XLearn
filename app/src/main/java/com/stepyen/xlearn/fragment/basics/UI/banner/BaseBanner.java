package com.stepyen.xlearn.fragment.basics.UI.banner;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import com.stepyen.xui.logs.UILog;
import com.stepyen.xui.widget.banner.widget.loopviewpager.FixedSpeedScroller;
import com.stepyen.xui.widget.banner.widget.loopviewpager.LoopViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * date：2020-01-07
 * author：stepyen
 * description：
 */
public abstract class BaseBanner<E, T extends BaseBanner<E, T>> extends RelativeLayout {
    protected Context mContext;

    /**
     * 定时任务
     */
    private Timer timer;

    /**
     * 无限循环 ViewPager
     */
    protected LoopViewPager mViewPager;

    /**
     * 数据源
     */
    protected List<E> mDatas = new ArrayList<>();
    /**
     * 多久后开始滚动
     */
    private long mDelay = 5000;

    /**
     * 滚动间隔
     */
    private long mPeriod = 5000;
    /**
     * 数据是否发生变化
     */
    private boolean mIsDataChanged = false;

    /**
     * 当前position
     */
    protected int mCurrentPosition;

    /**
     * 滚动速度
     */
    private int mScrollSpeed = 450;

    /**
     * 页面切换监听
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public BaseBanner addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
        return this;
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            scrollToNextItem(mCurrentPosition);
            return true;
        }
    });

    public BaseBanner(Context context) {
        this(context, null);
    }

    public BaseBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        init();
    }

    private void init() {
        mViewPager = new LoopViewPager(mContext);
        mViewPager.setOverScrollMode(OVER_SCROLL_NEVER);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        addView(mViewPager, lp);
    }

    public T setData(List<E> list) {
        this.mDatas = list;
        mIsDataChanged = true;
        return (T) this;
    }

    public E getItem(int position) {
        return size() > 0 ? mDatas.get(position) : null;
    }

    public int size() {
        return mDatas != null ? mDatas.size() : 0;
    }


    /**
     * 滚动延时,默认5000毫秒
     */
    public T setDelay(long delay) {
        this.mDelay = delay;
        return (T) this;
    }

    /**
     * 滚动间隔,默认5000毫秒
     */
    public T setPeriod(long period) {
        this.mPeriod = period;
        return (T) this;
    }


    /**
     * 开始滚动
     */
    public void startScroll() {
        if (mDatas == null) {
            throw new IllegalStateException("Data source is empty,you must setSource() before startScroll()");
        }

        if (mDatas.size() > 0 && mCurrentPosition > mDatas.size() - 1) {
            mCurrentPosition = 0;
        }

        setViewPager();

        goOnScroll();
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        UILog.d(this.getClass().getSimpleName() + "--->pauseScroll()");
    }


    /**
     * 设置viewpager
     */
    private void setViewPager() {
        BaseBanner.InnerBannerAdapter mInnerAdapter = new InnerBannerAdapter();
        mViewPager.setAdapter(mInnerAdapter);
        mViewPager.setOffscreenPageLimit(mDatas.size() - 1);

        mScrollSpeed = 450;
        setScrollSpeed();

        if (mInternalPageListener != null) {
            mViewPager.removeOnPageChangeListener(mInternalPageListener);
        }
        mViewPager.addOnPageChangeListener(mInternalPageListener);
    }


    /**
     * 继续滚动
     */
    public void goOnScroll() {
        if (!isValid()) {
            return;
        }

        stopScroll();

        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.obtainMessage().sendToTarget();
                }
            }, mDelay, mPeriod);
        }
    }




    /**
     * 设置滚动速率
     */
    private void setScrollSpeed() {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
            FixedSpeedScroller myScroller = new FixedSpeedScroller(mContext, interpolator, mScrollSpeed);
            mScroller.set(mViewPager, myScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 控制不能手动滑动
        return true;
    }

    /**
     * 页面变换监听
     */
    private ViewPager.OnPageChangeListener mInternalPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPosition = position % mDatas.size();

            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };


    protected boolean isValid() {
        if (mViewPager == null) {
            UILog.e("ViewPager is not exist!");
            return false;
        }

        if (mDatas == null || mDatas.size() <= 0) {
            UILog.e("DataList must be not empty!");
            return false;
        }


        return true;
    }


    /**
     * 滚动到下一个item
     */
    private void scrollToNextItem(int position) {
        if (isValid()) {
            if (mIsDataChanged) {
                updateViewPager();
            }
            position++;
            mViewPager.setCurrentItem(position);
        }
    }

    /**
     * 更新ViewPager
     */
    private void updateViewPager() {
        mViewPager.getPageAdapterWrapper().notifyDataSetChanged();
    }





    /**
     * 创建ViewPager的Item布局
     */
    public abstract View onCreateItemView(int position);


    private class InnerBannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDatas != null ? mDatas.size() : 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View inflate = onCreateItemView(position);
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }



}
