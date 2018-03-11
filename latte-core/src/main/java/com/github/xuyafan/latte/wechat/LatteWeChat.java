package com.github.xuyafan.latte.wechat;

import android.app.Activity;

import com.github.xuyafan.latte.app.ConfigKeys;
import com.github.xuyafan.latte.app.Latte;
import com.github.xuyafan.latte.wechat.callbacks.IWeChatSignInCallBack;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * author： xuyafan
 * description:
 */

public class LatteWeChat {

    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;

    private IWeChatSignInCallBack mIWeChatSignInCallBack = null;

    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);

    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public IWeChatSignInCallBack getmIWeChatSignInCallBack() {
        return mIWeChatSignInCallBack;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallBack callBack) {
        this.mIWeChatSignInCallBack = callBack;
        return this;
    }

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }
}
