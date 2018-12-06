package com.li.mvpprogram.exception;

/**
 * 自动定义网络异常类
 */
public class HttpThrowable extends Throwable {

    /**失败状态码*/
    private int code = -1;

    public HttpThrowable() {
    }

    public HttpThrowable(String message) {
        super(message);
    }

    public HttpThrowable(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
