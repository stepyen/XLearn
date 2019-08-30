package com.stepyen.xlearn.fragment.utils.AgentWeb;

import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;

import com.stepyen.xlearn.R;
import com.stepyen.xutil.tip.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * date：2019/8/12
 * author：stepyen
 * description：
 */
public class AgentWebActivity extends AppCompatActivity {
    private AgentWebFragment mAgentWebFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);

        Uri uri = getIntent().getData();
        if (uri != null) {
            openFragment(uri.toString());
        } else {
            String url = getIntent().getStringExtra(AgentWebFragment.KEY_URL);
            if (url != null) {
                openFragment(url);
            } else {
                ToastUtils.toast("数据出错！");
                finish();
            }
        }
    }

    private void openFragment(String url) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_frame_layout, mAgentWebFragment = AgentWebFragment.getInstance(url));
        ft.commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AgentWebFragment agentWebFragment = mAgentWebFragment;
        if (agentWebFragment != null) {
            FragmentKeyDown fragmentKeyDown = agentWebFragment;
            if (fragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
