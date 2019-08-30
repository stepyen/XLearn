package com.stepyen.xlearn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stepyen.xlearn.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date：2019/8/6
 * author：stepyen
 * description：
 */
public class UMengActivity extends AppCompatActivity {
    private static final String TAG = "UMengActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng);
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，添加
        // 注意onActivityResult不可在fragment中实现，如果在fragment中调用登录或分享，需要在fragment依赖的Activity中实现
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @OnClick({R.id.btn_wx_login,
            R.id.btn_qq_login,
            R.id.btn_weibo_login,
            R.id.btn_wx_share,
            R.id.btn_QQ_share,
            R.id.btn_QQ_space_share

    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_wx_login:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.btn_qq_login:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.btn_weibo_login:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.btn_wx_share:
                ShareBoardlistener boardlistener = new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform platform, SHARE_MEDIA media) {
                        if (platform.mShowWord.equals("复制文本")) {
                            Toast.makeText(UMengActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (platform.mShowWord.equals("复制链接")) {
                            Toast.makeText(UMengActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();

                        } else {
                            String url ="http://mobile.umeng.com/social";
                            UMWeb web = new UMWeb(url);
                            web.setTitle("来自分享面板标题");
                            web.setDescription("来自分享面板内容");
                            web.setThumb(new UMImage(UMengActivity.this, R.drawable.icon_dog));

                            new ShareAction(UMengActivity.this).withMedia(web)
                                    .setPlatform(media)
                                    .setCallback(shareListener)
                                    .share();
                        }
                    }
                };
                new ShareAction(this)
                        .withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                        .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                        .setShareboardclickCallback(boardlistener)
                        .open();
                break;
            case R.id.btn_QQ_share:
                break;
            case R.id.btn_QQ_space_share:
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)//分享平台
                        .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","info_icon_1","info_icon_1")// 自定义按钮
//                        .setShareboardclickCallback(shareListener)//面板点击监听器
                        .open();
                break;
        }
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param media 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA media) {
            Log.i(TAG, "onStart: " + media.getName());
        }

        /**
         * @desc 授权成功的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA media, int i, Map<String, String> map) {
            Log.i(TAG, "onComplete: " + media.getName());
            for (String title : map.keySet()) {
                Log.i(TAG, "onComplete: " + title + " : " + map.get(title));
            }
        }

        /**
         * @desc 授权失败的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         * @param throwable 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA media, int i, Throwable throwable) {
            Log.i(TAG, "onError: " + media.getName());
            Log.i(TAG, "onError: " + throwable.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA media, int i) {
            Log.i(TAG, "onCancel: ");
        }
    };

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(UMengActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(UMengActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(UMengActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在使用分享或者授权的Activity中，重写onDestory()方法：避免内存泄露问题
        UMShareAPI.get(this).release();
    }
}
