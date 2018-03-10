package com.github.xuyafan.latte.app;

import android.content.Context;

/**
 * author： xuyafan
 * description:
 */

public final class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance().getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }


}
