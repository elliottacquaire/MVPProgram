/**/

package com.li.mvpprogram.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.li.mvpprogram.BuildConfig;
import com.li.mvpprogram.http.interceptor.HandleRequestInterceptor;
import com.li.mvpprogram.http.interceptor.HandleResponseInterceptor;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * OkHttp工具类
 */
public class OkHttpUtil {

    private static OkHttpClient client;
    private static OkHttpClient fileClient;

    /**
     * 获取默认okhttp客户端
     *
     * @return
     */
    public static OkHttpClient defaultOkHttpClient() {
        if (client != null) {
            return client;
        }
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HandleResponseInterceptor())
                .addInterceptor(new HandleRequestInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(logging)
                ;

        if (BuildConfig.DEBUG) {
            okHttpBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }
        client = okHttpBuilder.build();
        return client;
    }

    /**
     * 上传下载文件的okhttp客户端
     *
     * @return
     */
    public static OkHttpClient createFileClient() {
        if (fileClient != null) {
            return fileClient;
        }

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        if (BuildConfig.DEBUG) {
            okHttpBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }
        fileClient = okHttpBuilder.build();
        return fileClient;
    }
}
