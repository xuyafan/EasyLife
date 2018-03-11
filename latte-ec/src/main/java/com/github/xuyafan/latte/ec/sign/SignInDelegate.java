package com.github.xuyafan.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.github.xuyafan.latte.app.Latte;
import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.ec.R;
import com.github.xuyafan.latte.ec.R2;
import com.github.xuyafan.latte.net.RestClient;
import com.github.xuyafan.latte.net.callback.ISuccess;
import com.github.xuyafan.latte.util.log.LatteLogger;
import com.github.xuyafan.latte.wechat.LatteWeChat;
import com.github.xuyafan.latte.wechat.callbacks.IWeChatSignInCallBack;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author： xuyafan
 * description:
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton mBtnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView mTvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView mSignInWechat;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {


        if (checkForm()) {
            RestClient.builder()
                    .url(Latte.getAPIHost() + "/signIn/")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("sign_in", response);
                            SignHandler.onSignIn(response, mISignListener);

                        }
                    })
                    .build()
                    .post();


        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignInWeixin() {
        if (checkForm()) {
            LatteWeChat.getInstance()
                    .onSignSuccess(new IWeChatSignInCallBack() {
                        @Override
                        public void onSignInSuccess(String userInfo) {
                            LatteLogger.i("微信登录成功:" + userInfo);
                            Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                        }
                    })
                    .signIn();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLinkSignUp() {
        start(new SignUpDelegate());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceStat, View rootView) {

    }

    private boolean checkForm() {

        final String email = mEmail.getText().toString();

        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码至少6位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }


        return isPass;

    }

}
