

package com.li.mvpprogram.http;


import com.li.mvpprogram.bean.BannerVo;
import com.li.mvpprogram.bean.MapVo;
import com.li.mvpprogram.bean.MsgVo;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

/**
 * API接口
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> activateDevice(
            @FieldMap Map<String, String> paramMap
    );



    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> sendSms(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Long>> userFirstLogin(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Long>> resetUserLogin(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> getMobileByNodeCode(
            @FieldMap Map<String, String> paramMap
    );


    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> queryLastImport(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> queryImportList(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> logout(
            @FieldMap Map<String, String> paramMap
    );



    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> uploadAppsInfo(
            @FieldMap Map<String, String> paramMap
    );

    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<String>> submitPushInfo(
            @FieldMap Map<String, String> paramMap
    );


    /**
     * 查询交易密码状态
     *
     * @param paramMap
     * @return
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> isSetTradePassword(@FieldMap Map<String, String> paramMap);

    /**
     * 查询频道列表
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<List<MapVo>>> queryChannel(@FieldMap Map<String, String> paramMap);

    /**
     * 查询发现banner列表
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<List<BannerVo>>> getFoundBanners(@FieldMap Map<String, String> paramMap);

    /**
     * 新闻更新（阅读，分享）（V3.4）
     *
     * @param paramMap
     * @return
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<Boolean>> operateNews(@FieldMap Map<String, String> paramMap);


    /**
     * 新闻推荐（V3.4）
     *
     * @param paramMap
     * @return
     */
    @FormUrlEncoded
    @POST("api.json")
    Observable<Response<List<MsgVo>>> recommendNews(@FieldMap Map<String, String> paramMap);




}

