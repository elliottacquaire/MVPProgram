/**/

package com.li.mvpprogram.http.interceptor;

import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.config.ENVController;
import com.li.mvpprogram.utils.PreferencesUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;


/**
 * 拦截并修改http response
 * 保存cookie
 * 修改response result
 */
public class HandleResponseInterceptor implements Interceptor {

    public static final String SET_COOKIE = "Set-Cookie";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!chain.request().url().toString().startsWith(ENVController.URL)) { // 如果不是调用服务端接口,则不拦截数据
            return originalResponse;
        }

        // 存储cookie
        if (!originalResponse.headers(SET_COOKIE).isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            /*Observable.fromIterable(originalResponse.headers(SET_COOKIE))
                    .map(s -> {
                        String[] cookieArray = s.split(";");
                        return cookieArray[0];
                    })
                    .subscribe(cookie -> {
                        cookieBuffer.append(cookie).append(";");
                    });*/
            PreferencesUtils.putString(Constants.COOKIE_PREFERENCE, BaseApplication.getInstance().getApplicationContext(), Constants.COOKIE, cookieBuffer.toString());
        }

        return originalResponse;
    }

}
