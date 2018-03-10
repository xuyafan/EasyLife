package com.github.xuyafan.latte.net.rx;

import android.content.Context;

import com.github.xuyafan.latte.net.HttpMethod;
import com.github.xuyafan.latte.net.RestCreator;
import com.github.xuyafan.latte.ui.loader.LatteLoader;
import com.github.xuyafan.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * author： xuyafan
 * description:
 */

public class RxRestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    //上传
    private final File FILE;


    RxRestClient(String url,
                 WeakHashMap<String, Object> params,
                 RequestBody body,
                 LoaderStyle loaderStyle,
                 Context context,
                 File file) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;

    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;

        }

        return observable;
    }


    public final Observable<String> get() {

        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("POST_RAW params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }

    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("PUT_RAW params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    public final Observable<String> delete() {

        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {

        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
//        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR)
//                .handleDownload();

        return RestCreator.getRxRestService().download(URL, PARAMS);

    }
}
