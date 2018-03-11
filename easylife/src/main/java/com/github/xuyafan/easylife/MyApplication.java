package com.github.xuyafan.easylife;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.github.xuyafan.latte.app.Latte;
import com.github.xuyafan.latte.ec.database.DatabaseManager;
import com.github.xuyafan.latte.ec.icon.FontEcModule;
import com.github.xuyafan.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * author： xuyafan
 * description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())

                //localhost http://127.0.0.1
                //手机模拟器 http://10.0.2.2
                //同一局域网内 真机ipconfig查看
                .withApiHost("http://10.0.2.2")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .configure();


        DatabaseManager.getInstance().init(this);

        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
