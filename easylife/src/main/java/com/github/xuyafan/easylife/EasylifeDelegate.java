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
import com.github.xuyafan.latte.net.rx.RxRestClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
//        testRestClientGet();
        testRxGet();
    }

    private void testRestClientGet() {
        RestClient.builder()
                .url("http://127.0.0.1/test")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
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

    private void testRxGet() {
        final String url = "http://127.0.0.1/test";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getContext(), "RxGet:" + s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
