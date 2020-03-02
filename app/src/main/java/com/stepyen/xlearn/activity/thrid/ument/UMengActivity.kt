package com.stepyen.xlearn.activity.thrid.ument

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.stepyen.xlearn.R
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.utils.ShareBoardlistener

/**
 * date：2019/8/6
 * author：stepyen
 * description：
 */
class UMengActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umeng)
        ButterKnife.bind(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，添加
        // 注意onActivityResult不可在fragment中实现，如果在fragment中调用登录或分享，需要在fragment依赖的Activity中实现
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

    @OnClick(R.id.btn_wx_login, R.id.btn_qq_login, R.id.btn_weibo_login, R.id.btn_wx_share, R.id.btn_QQ_share, R.id.btn_QQ_space_share)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.btn_wx_login -> UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener)
            R.id.btn_qq_login -> UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener)
            R.id.btn_weibo_login -> UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener)
            R.id.btn_wx_share -> {
                val boardlistener = ShareBoardlistener { platform, media ->
                    if (platform.mShowWord == "复制文本") {
                        Toast.makeText(this@UMengActivity, "复制文本按钮", Toast.LENGTH_LONG).show()
                    } else if (platform.mShowWord == "复制链接") {
                        Toast.makeText(this@UMengActivity, "复制链接按钮", Toast.LENGTH_LONG).show()
                    } else {
                        val url = "http://mobile.umeng.com/social"
                        val web = UMWeb(url)
                        web.title = "来自分享面板标题"
                        web.description = "来自分享面板内容"
                        web.setThumb(UMImage(this@UMengActivity, R.drawable.icon_dog))
                        ShareAction(this@UMengActivity).withMedia(web)
                                .setPlatform(media)
                                .setCallback(shareListener)
                                .share()
                    }
                }
                ShareAction(this)
                        .withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                        .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                        .setShareboardclickCallback(boardlistener)
                        .open()
            }
            R.id.btn_QQ_share -> {
            }
            R.id.btn_QQ_space_share -> ShareAction(this)
                    .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE) //分享平台
                    .addButton("umeng_sharebutton_custom", "umeng_sharebutton_custom", "info_icon_1", "info_icon_1") // 自定义按钮
//                        .setShareboardclickCallback(shareListener)//面板点击监听器
                    .open()
        }
    }

    var umAuthListener: UMAuthListener = object : UMAuthListener {
        /**
         * @desc 授权开始的回调
         * @param media 平台名称
         */
        override fun onStart(media: SHARE_MEDIA) {
            Log.i(TAG, "onStart: " + media.getName())
        }

        /**
         * @desc 授权成功的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        override fun onComplete(media: SHARE_MEDIA, i: Int, map: Map<String, String>) {
            Log.i(TAG, "onComplete: " + media.getName())
            for (title in map.keys) {
                Log.i(TAG, "onComplete: " + title + " : " + map[title])
            }
        }

        /**
         * @desc 授权失败的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         * @param throwable 错误原因
         */
        override fun onError(media: SHARE_MEDIA, i: Int, throwable: Throwable) {
            Log.i(TAG, "onError: " + media.getName())
            Log.i(TAG, "onError: " + throwable.message)
        }

        /**
         * @desc 授权取消的回调
         * @param media 平台名称
         * @param i 行为序号，开发者用不上
         */
        override fun onCancel(media: SHARE_MEDIA, i: Int) {
            Log.i(TAG, "onCancel: ")
        }
    }
    private val shareListener: UMShareListener = object : UMShareListener {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        override fun onStart(platform: SHARE_MEDIA) {}

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        override fun onResult(platform: SHARE_MEDIA) {
            Toast.makeText(this@UMengActivity, "成功了", Toast.LENGTH_LONG).show()
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        override fun onError(platform: SHARE_MEDIA, t: Throwable) {
            Toast.makeText(this@UMengActivity, "失败" + t.message, Toast.LENGTH_LONG).show()
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        override fun onCancel(platform: SHARE_MEDIA) {
            Toast.makeText(this@UMengActivity, "取消了", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 在使用分享或者授权的Activity中，重写onDestory()方法：避免内存泄露问题
        UMShareAPI.get(this).release()
    }

    companion object {
        private const val TAG = "UMengActivity"
    }
}