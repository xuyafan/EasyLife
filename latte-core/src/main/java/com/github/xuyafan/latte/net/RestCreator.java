package com.github.xuyafan.latte.net;

import com.github.xuyafan.latte.app.ConfigKeys;
import com.github.xuyafan.latte.app.Latte;
import com.github.xuyafan.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author： xuyafan
 * description:
 */

public class RestCreator {


    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.RXREST_SERVICE;
    }

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);
        private static final OkHttpClient OK_HTTP_CLIENT =
                addInterceptor()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .build();

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService RXREST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }


}
