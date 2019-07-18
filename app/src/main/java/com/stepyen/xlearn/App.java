package com.stepyen.xlearn;

import android.app.Application;
import android.content.Context;
import com.stepyen.xlearn.base.BaseActivity;
import com.stepyen.xutil.XUtil;
import com.xuexiang.xpage.PageConfig;
import com.xuexiang.xpage.PageConfiguration;
import com.xuexiang.xpage.model.PageInfo;

import java.util.List;

import androidx.multidex.MultiDex;

/**
 * date：2019/6/24
 * author：stepyen
 * description：
 */
public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        XUtil.init(this);

        initPage();

        initAOP();

    }

    private void initAOP() {
        //初始化插件
//        XAOP.init(this);
        //日志打印切片开启
//        XAOP.debug(BuildConfig.DEBUG);
        //设置动态申请权限切片 申请权限被拒绝的事件响应监听
//        XAOP.setOnPermissionDeniedListener(new PermissionUtils.OnPermissionDeniedListener() {
//            @Override
//            public void onDenied(List<String> permissionsDenied) {
//                ToastUtils.toast("权限申请被拒绝:" + StringUtils.listToString(permissionsDenied, ","));
//            }
//
//        });
    }

    private void initPage() {
        PageConfig.getInstance()
                .setPageConfiguration(new PageConfiguration() { //页面注册
                    @Override
                    public List<PageInfo> registerPages(Context context) {
                        //自动注册页面,是编译时自动生成的，build一下就出来了。如果你还没使用@Page的话，暂时是不会生成的。
                        return AppPageConfig.getInstance().getPages(); //自动注册页面

                    }
                })
                .debug("PageLog")       //开启调试
                .setContainActivityClazz(BaseActivity.class) //设置默认的容器Activity
                .enableWatcher(false)   //设置是否开启内存泄露监测
                .init(this);            //初始化页面配置
    }
}
