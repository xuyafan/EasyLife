package com.github.xuyafan.latte.app;

import com.github.xuyafan.latte.util.storage.LattePreference;

/**
 * author： xuyafan
 * description:
 */

public class AccountManager {
    /**
     * 保存用户登录状态
     *
     * @param state 登录状态
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


    private enum SignTag {
        SIGN_TAG
    }
}
