package com.stepyen.xlearn.base;

import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.stepyen.xlearn.adapter.SimpleAdapter;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.base.XPageContainerListFragment;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xpage.utils.TitleUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;

/**
 * 解决输入法内存泄漏, 修改列表样式为主副标题显示
 */
public abstract class ComponentContainerFragment extends XPageContainerListFragment {

    @Override
    protected void initPage() {
        initTitle();
        initViews();
        initListeners();
    }

    protected TitleBar initTitle() {
        return TitleUtils.addTitleBarDynamic((ViewGroup) getRootView(), getPageTitle(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
    }

    @Override
    protected void initData() {
        mSimpleData = initSimpleData(mSimpleData);

        List<Map<String, String>> data = new ArrayList<>();
        for (String content : mSimpleData) {
            Map<String, String> item = new HashMap<>();
            int index = content.indexOf("\n");
            if (index > 0) {
                item.put(SimpleAdapter.KEY_TITLE, String.valueOf(content.subSequence(0, index)));
                item.put(SimpleAdapter.KEY_SUB_TITLE, String.valueOf(content.subSequence(index + 1, content.length())));
            } else {
                item.put(SimpleAdapter.KEY_TITLE, content);
                item.put(SimpleAdapter.KEY_SUB_TITLE, "");
            }
            data.add(item);
        }

        getListView().setAdapter(new SimpleAdapter(getContext(), data));
        initSimply();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        onItemClick(view, position);
    }

    @SingleClick
    private void onItemClick(View view, int position) {
        onItemClick(position);
    }

    @Override
    public void onDestroyView() {
//        KeyboardUtils.fixSoftInputLeaks(getContext());

        getListView().setOnItemClickListener(null);
        super.onDestroyView();
    }

    /**
     * 打开一个新的页面
     *
     * @param name
     * @param <T>
     * @return
     */
    public <T extends XPageFragment> Fragment openNewPage(String name) {
        return new PageOption(name)
                .setAnim(CoreAnim.slide)
                .setNewActivity(true)
                .open(this);
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

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getPageName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getPageName());
    }
}
