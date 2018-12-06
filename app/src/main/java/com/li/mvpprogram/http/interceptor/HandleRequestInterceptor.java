/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.http.interceptor;

import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.config.ENVController;
import com.li.mvpprogram.utils.PreferencesUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 拦截并修改http request
 * http增加did和agent
 */
public class HandleRequestInterceptor implements Interceptor {

    public static final String COOKIE = "Cookie";

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!chain.request().url().toString().startsWith(ENVController.URL)) { // 如果不是调用服务端接口,则不拦截数据
            return chain.proceed(chain.request());
        }

        Request request = chain.request()
                .newBuilder()
                .addHeader(Constants.USER_AGENT, Constants.AGENT) // add agent "android"
                .addHeader( // add Cookie "sessionId"
                        COOKIE,
                        PreferencesUtils.getString(
                                Constants.COOKIE_PREFERENCE,
                                BaseApplication.getInstance().getApplicationContext(),
                                Constants.COOKIE
                        )
                )
                .addHeader( // add did
                        Constants.DID,
                        PreferencesUtils.getString(
                                Constants.DID_PREFERENCE,
                                BaseApplication.getInstance().getApplicationContext(),
                                Constants.SP_KEY_DEVICE_ID,
                                Constants.DEFAULT_DID
                        )
                ).build();

        return chain.proceed(request);
    }
}
