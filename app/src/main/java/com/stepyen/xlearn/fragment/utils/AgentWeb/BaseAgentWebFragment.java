package com.stepyen.xlearn.fragment.utils.AgentWeb;

import android.view.KeyEvent;

import com.just.agentweb.AgentWeb;

import androidx.fragment.app.Fragment;


/**
 * date：2019/8/12
 * author：stepyen
 * description：AgentWeb 生命周期管理
 */
public class BaseAgentWebFragment extends Fragment implements FragmentKeyDown{

    protected AgentWeb mAgentWeb;

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();//恢复
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause(); //暂停应用内所有WebView ， 调用mWebView.resumeTimers();/mAgentWeb.getWebLifeCycle().onResume(); 恢复。
        super.onPause();
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.destroy();
        super.onDestroyView();
    }
}
