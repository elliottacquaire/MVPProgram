package com.li.mvpprogram.utils.net;

/**
 * 网络状态变化观察者
 * Created by zhanglisan on 11/01/2018.
 */
public interface NetStateChangeObserver {

    /**
     * 网络断开
     */
    void onNetDisconnected();

    /**
     * 网络连接
     * @param networkType 网络类型
     */
    void onNetConnected(int networkType);
}
