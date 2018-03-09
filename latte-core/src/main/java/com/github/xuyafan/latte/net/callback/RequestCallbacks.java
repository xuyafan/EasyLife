package com.github.xuyafan.latte.net.callback;

import android.os.Handler;

import com.github.xuyafan.latte.ui.LatteLoader;
import com.github.xuyafan.latte.ui.LoaderStyle;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author： xuyafan
 * description:
 */

public class RequestCallbacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYPE;
    private static final Handler HANDLER=new Handler();


    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYPE =loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();

    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }

        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading() {
        if(LOADER_STYPE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
