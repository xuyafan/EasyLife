package com.github.xuyafan.latte.net;

import android.content.Context;

import com.github.xuyafan.latte.net.callback.IError;
import com.github.xuyafan.latte.net.callback.IFailure;
import com.github.xuyafan.latte.net.callback.IRequest;
import com.github.xuyafan.latte.net.callback.ISuccess;
import com.github.xuyafan.latte.ui.loader.LatteLoader;
import com.github.xuyafan.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author： xuyafan
 * description:
 */

public class RestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;

    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;

    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_TYPE;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }


    public final RestClient build() {
        return new RestClient(
                mUrl,
                PARAMS,
                mRequest,
                mSuccess,
                mFailure,
                mError,
                mBody,
                mLoaderStyle,
                mContext,
                mFile,
                mDownloadDir,
                mExtension,
                mName);
    }

}
