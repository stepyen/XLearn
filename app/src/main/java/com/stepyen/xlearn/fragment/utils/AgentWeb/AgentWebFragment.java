package com.stepyen.xlearn.fragment.utils.AgentWeb;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultDownloadImpl;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebListenerManager;
import com.stepyen.xlearn.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * date：2019/8/12
 * author：stepyen
 * description：
 */
public class AgentWebFragment extends BaseAgentWebFragment {
    public static final String KEY_URL = "KEY_URL";

    public static AgentWebFragment getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        return getInstance(bundle);
    }

    public static AgentWebFragment getInstance(Bundle bundle) {
        AgentWebFragment fragment = new AgentWebFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agentweb, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAgentWeb = AgentWeb.with(this)
                //传入AgentWeb的父控件。
                .setAgentWebParent((LinearLayout) view,
                        -1,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .useDefaultIndicator(-1, 3)
                //设置 IAgentWebSettings。
//                .setAgentWebWebSettings(getSettings())
                //WebViewClient ， 与 WebView 使用一致 ，但是请勿获取WebView调用setWebViewClient(xx)方法了,会覆盖AgentWeb DefaultWebClient,同时相应的中间件也会失效。
//                .setWebViewClient(mWebViewClient)
                //WebChromeClient
//                .setWebChromeClient(mWebChromeClient)
                //设置WebChromeClient中间件，支持多个WebChromeClient，AgentWeb 3.0.0 加入。
//                .useMiddlewareWebChrome(getMiddlewareWebChrome())
                //设置WebViewClient中间件，支持多个WebViewClient， AgentWeb 3.0.0 加入。
//                .useMiddlewareWebClient(getMiddlewareWebClient())
                //权限拦截 2.0.0 加入。
//                .setPermissionInterceptor(mPermissionInterceptor)
                //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //自定义UI  AgentWeb3.0.0 加入。
//                .setAgentWebUIController(new UIController(getActivity()))
                //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
//                .setDownloadListener(mDownloadListener) 4.0.0 删除该API//下载回调
//                .setWebLayout(getWebLayout())
//                .openParallelDownload()// 4.0.0删除该API 打开并行下载 , 默认串行下载。 请通过AgentWebDownloader#Extra实现并行下载
//                .setNotifyIcon(R.drawable.ic_file_download_black_24dp) 4.0.0删除该api //下载通知图标。4.0.0后的版本请通过AgentWebDownloader#Extra修改icon
                //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .interceptUnkownUrl()
                //创建AgentWeb。
                .createAgentWeb()
                .ready()//设置 WebSettings。
                //WebView载入该url地址的页面并显示。
                .go(getUrl());

    }

    public String getUrl() {
        String target = "";
        Bundle bundle = getArguments();
        if (bundle != null) {
            target = bundle.getString(KEY_URL);
        }

        return target;
    }


//    public IAgentWebSettings getSettings() {
//        return new AbsAgentWebSettings() {
//            private AgentWeb mAgentWeb;
//
//            @Override
//            protected void bindAgentWebSupport(AgentWeb agentWeb) {
//                this.mAgentWeb = agentWeb;
//            }
//
//            /**
//             * AgentWeb 4.0.0 内部删除了 DownloadListener 监听 ，以及相关API ，将 Download 部分完全抽离出来独立一个库，
//             * 如果你需要使用 AgentWeb Download 部分 ， 请依赖上 compile 'com.just.agentweb:download:4.0.0 ，
//             * 如果你需要监听下载结果，请自定义 AgentWebSetting ， New 出 DefaultDownloadImpl，传入DownloadListenerAdapter
//             * 实现进度或者结果监听，例如下面这个例子，如果你不需要监听进度，或者下载结果，下面 setDownloader 的例子可以忽略。
//             * @param webView
//             * @param downloadListener
//             * @return WebListenerManager
//             */
//            @Override
//            public WebListenerManager setDownloader(WebView webView, android.webkit.DownloadListener downloadListener) {
//                return super.setDownloader(webView,
//                        DefaultDownloadImpl
//                                .create((Activity) webView.getContext(),
//                                        webView,
//                                        mDownloadListenerAdapter,
//                                        mDownloadListenerAdapter,
//                                        this.mAgentWeb.getPermissionInterceptor()));
//            }
//        };
//    }

}
