package com.github.xuyafan.easylife;

import android.app.Application;

import com.github.xuyafan.latte.app.Latte;
import com.github.xuyafan.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * author： xuyafan
 * description:
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
