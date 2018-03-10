package com.github.xuyafan.latte.net.rx;

import android.content.Context;

import com.github.xuyafan.latte.net.RestCreator;
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

public class RxRestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private RequestBody mBody = null;

    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;


    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_TYPE;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }


    public final RxRestClient build() {
        return new RxRestClient(
                mUrl,
                PARAMS,
                mBody,
                mLoaderStyle,
                mContext,
                mFile);
    }

}
