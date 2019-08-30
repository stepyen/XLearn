package com.stepyen.xlearn;
import butterknife.BindView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.google.android.material.tabs.TabLayout;
import com.stepyen.xlearn.base.BaseActivity;
import com.stepyen.xlearn.fragment.BasicsFragment;
import com.stepyen.xlearn.fragment.ExpandsFragment;
import com.stepyen.xlearn.fragment.UtilitysFragment;

/**
 *
 *
 * 记录学习历程
 */
public class MainActivity extends BaseActivity {

    TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabLayout = findViewById(R.id.tabs);
        initView();
    }

    private void initView() {
        openPage(BasicsFragment.class);
        initTab();
    }

    private void initTab() {
        TabLayout.Tab component = mTabLayout.newTab();
        component.setText("基础");
//        component.setIcon(R.drawable.selector_icon_tabbar_component);
        mTabLayout.addTab(component);

        TabLayout.Tab util = mTabLayout.newTab();
        util.setText("工具");
//        util.setIcon(R.drawable.selector_icon_tabbar_util);
        mTabLayout.addTab(util);

        TabLayout.Tab expand = mTabLayout.newTab();
        expand.setText("拓展");
//        expand.setIcon(R.drawable.selector_icon_tabbar_expand);
        mTabLayout.addTab(expand);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        switchPage(BasicsFragment.class);
                        break;
                    case 1:
                        switchPage(UtilitysFragment.class);
                        break;
                    case 2:
                        switchPage(ExpandsFragment.class);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
