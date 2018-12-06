/**/

package com.li.mvpprogram.http;


import com.li.mvpprogram.exception.HttpThrowable;
import com.li.mvpprogram.utils.LogUtils;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.apache.http.conn.ConnectTimeoutException;
import retrofit2.HttpException;
import retrofit2.Response;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 接口调用返回结果统一处理类
 *
 * @param <T> 返回结果Response中的Result类型<br/>
 */
public abstract class HttpResponseSubscriber<T> implements Observer<Response<T>> {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e, e.getMessage());
        e.printStackTrace();
        HttpThrowable throwable = new HttpThrowable();
        // 在这里做全局的错误处理
        if (e instanceof ConnectException || e instanceof UnknownHostException ||
                e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException) {
            throwable = new HttpThrowable("网络不给力,请检查网络连接");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN: //权限错误，需要实现
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default://均视为网络错误
                    throwable = new HttpThrowable(httpException.code(), "网络不给力,请检查网络连接");
                    break;
            }
        }
        _onError(throwable);
    }

    @Override
    public void onNext(Response<T> response) {
        if (response == null) {
            _onError(new HttpThrowable("http请求无返回结果"));
            return;
        }

//        int code = response.getCode();
//        if (response.isSuccess()) {
//            onSuccess(response.getResult());
//        } else if (code == ResultEnum.SessionStatus.getCode()) { // session未过期，处理业务异常情况
//            RpcHelper.sessionExpiredProcess();
//        } else if (code == ResultEnum.UnAuth.getCode()) { // 未实名认证
//            RpcHelper.IdAuthProcess();
//        } else {
//            _onError(new HttpThrowable(code, response.getTips()));
//        }
    }

    /**
     * 业务成功
     */
    public abstract void onSuccess(T result);

    /**
     * 请求失败,包括业务失败和网络失败
     *
     * @param e
     */
    public abstract void _onError(HttpThrowable e);


}
