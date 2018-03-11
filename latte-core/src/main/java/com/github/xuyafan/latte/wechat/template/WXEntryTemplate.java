package com.github.xuyafan.latte.wechat.template;

import com.github.xuyafan.latte.wechat.BaseWXEntryActivity;
import com.github.xuyafan.latte.wechat.LatteWeChat;

/**
 * author： xuyafan
 * description:
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getmIWeChatSignInCallBack().onSignInSuccess(userInfo);
    }
}
