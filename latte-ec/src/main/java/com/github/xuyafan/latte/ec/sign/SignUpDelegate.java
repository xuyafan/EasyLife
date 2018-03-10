package com.github.xuyafan.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.ec.R;
import com.github.xuyafan.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author： xuyafan
 * description:
 */

public class SignUpDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton mBtnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView nTvLinkSignIn;


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceStat, View rootView) {

    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
//            RestClient.builder()
//                    .url("sign_up")
//                    .params()
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();

            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码至少6位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;

    }


}
