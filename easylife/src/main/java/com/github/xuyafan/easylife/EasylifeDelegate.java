package com.github.xuyafan.easylife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.net.RestClient;
import com.github.xuyafan.latte.net.callback.IError;
import com.github.xuyafan.latte.net.callback.IFailure;
import com.github.xuyafan.latte.net.callback.ISuccess;

/**
 * author： xuyafan
 * description:
 */

public class EasylifeDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_easylife;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceStat, View rootView) {
        testRestClientGet();
    }

    private void testRestClientGet(){
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

}
