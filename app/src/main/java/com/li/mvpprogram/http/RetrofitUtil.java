/**/

package com.li.mvpprogram.http;

import com.google.gson.GsonBuilder;
import com.li.mvpprogram.config.ENVController;
import com.li.mvpprogram.utils.json.MyDateDeserializer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.DateFormat;
import java.util.Date;

/**
 * Retrofit工具类
 */
public class RetrofitUtil {

    private static volatile Retrofit retrofit;
    private static volatile Retrofit fileRetrofit;

    /**
     * 创建接口的retrofit和service
     *
     * @return
     */
    public static ApiService createService() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ENVController.URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create(
                                            new GsonBuilder().registerTypeAdapter(Date.class, new MyDateDeserializer())
                                                    .setDateFormat(DateFormat.LONG).create()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(OkHttpUtil.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit.create(ApiService.class);
    }

    /**
     * 创建loan接口的retrofit和service
     *
     * @return
     */
    public static ApiLoanService createLoanService() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ENVController.URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create(
                                            new GsonBuilder().registerTypeAdapter(Date.class, new MyDateDeserializer())
                                                    .setDateFormat(DateFormat.LONG).create()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(OkHttpUtil.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return retrofit.create(ApiLoanService.class);
    }

    /**
     * 创建文件上传下载的retrofit和service
     *
     * @return
     */
    public static FileService createFileService() {
        if (fileRetrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (fileRetrofit == null) {
                    fileRetrofit = new Retrofit.Builder()
                            .baseUrl(ENVController.FILE_URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create(
                                            new GsonBuilder().registerTypeAdapter(Date.class, new MyDateDeserializer())
                                                    .setDateFormat(DateFormat.LONG).create()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(OkHttpUtil.createFileClient())
                            .build();
                }
            }
        }
        return fileRetrofit.create(FileService.class);
    }
}
