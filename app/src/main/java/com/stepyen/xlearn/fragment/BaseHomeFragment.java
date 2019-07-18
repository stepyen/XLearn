package com.stepyen.xlearn.fragment;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.stepyen.xlearn.GridDividerItemDecoration;
import com.stepyen.xlearn.MainActivity;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.adapter.WidgetItemAdapter;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.base.BaseRecyclerAdapter;
import com.stepyen.xutil.common.ClickUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xpage.utils.TitleBar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 基础主页面
 */
public abstract class BaseHomeFragment extends BaseFragment implements BaseRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private WidgetItemAdapter mWidgetItemAdapter;

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setLeftVisible(false);
//        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_about) {
//            @Override
//            @SingleClick
//            public void performAction(View view) {
////                openNewPage(AboutFragment.class);
//                ToastUtils.toast("关于我们");
//            }
//        });
        return titleBar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_container;
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        int spanCount = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), spanCount));

        mWidgetItemAdapter = new WidgetItemAdapter(sortPageInfo(getPageContents()));
        mWidgetItemAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mWidgetItemAdapter);
    }

    /**
     * @return
     */
    protected abstract List<PageInfo> getPageContents();

    /**
     * 进行排序
     * @param pageInfoList
     * @return
     */
    private List<PageInfo> sortPageInfo(List<PageInfo> pageInfoList) {
        Collections.sort(pageInfoList, new Comparator<PageInfo>() {
            @Override
            public int compare(PageInfo o1, PageInfo o2) {
                return o1.getClassPath().compareTo(o2.getClassPath());
            }
        });
        return pageInfoList;
    }

    @Override
    @SingleClick
    public void onItemClick(View itemView, int pos) {
        PageInfo widgetInfo = mWidgetItemAdapter.getItem(pos);
        if (widgetInfo != null) {
            openNewPage(widgetInfo.getName());
        }
    }

    public MainActivity getContainer() {
        return (MainActivity) getActivity();
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click();
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕旋转时刷新一下title
        super.onConfigurationChanged(newConfig);
        ViewGroup root = (ViewGroup) getRootView();
        if (root.getChildAt(0) instanceof TitleBar) {
            root.removeViewAt(0);
            initTitle();
        }
    }

}
