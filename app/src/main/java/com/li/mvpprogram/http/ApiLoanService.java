/**/

package com.li.mvpprogram.http;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * API LOAN 接
 */
public interface ApiLoanService {

    /**
     * loan 接口
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> currencyApi(@FieldMap Map<String, String> paramMap);

    /**
     * loan 认证
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> checkAuth(@FieldMap Map<String, String> paramMap);

    /**
     * 文件上传(走网关)
     *
     * @param paramMap
     * @return
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> fileUpload(@FieldMap Map<String, String> paramMap);

    /**
     * 文件下载(走网关)
     *
     * @param paramMap
     * @return
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> fileDownload(@FieldMap Map<String, String> paramMap);
}
