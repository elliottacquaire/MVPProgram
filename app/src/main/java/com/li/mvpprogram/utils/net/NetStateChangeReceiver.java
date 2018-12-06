package com.li.mvpprogram.utils.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import com.li.mvpprogram.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听网络变化的BroadcastReceiver
 * Created by zhanglisan on 11/01/2018.
 */
public class NetStateChangeReceiver extends BroadcastReceiver {

    private static class InstanceHolder {
        private static final NetStateChangeReceiver INSTANCE = new NetStateChangeReceiver();
    }

    private List<NetStateChangeObserver> mObservers = new ArrayList<>();

    private NetStateChangeReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            int curNetworkType = NetworkUtils.getCurNetworkType(context);
            notifyObservers(curNetworkType);
        }
    }

    /**
     * 注册网络监听
     * @param context
     */
    public static void registerReceiver(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(InstanceHolder.INSTANCE, intentFilter);
    }

    /**
     * 取消网络监听
     * @param context
     */
    public static void unregisterReceiver(@NonNull Context context) {
        context.unregisterReceiver(InstanceHolder.INSTANCE);
    }

    /**
     * 注册网络变化Observer
     * @param observer
     */
    public static void registerObserver(NetStateChangeObserver observer) {
        if (observer == null) {
            return;
        }
        if (!InstanceHolder.INSTANCE.mObservers.contains(observer)) {
            InstanceHolder.INSTANCE.mObservers.add(observer);
        }
    }

    /**
     * 取消网络变化observer的注册
     * @param observer
     */
    public static void unregisterObserver(NetStateChangeObserver observer) {
        if (observer == null) {
            return;
        }
        if (InstanceHolder.INSTANCE.mObservers == null) {
            return;
        }
        InstanceHolder.INSTANCE.mObservers.remove(observer);
    }

    /**
     * 通知所有的observer网络状态变化
     * @param networkType
     */
    private void notifyObservers(int networkType) {
        if (networkType == NetworkUtils.Constants.NETWORK_NO) { // 网络已断开
            for (NetStateChangeObserver observer:
                 mObservers) {
                observer.onNetDisconnected();
            }
        } else { // 网络已连接
            // 如果当前用户是游客，并且数据库中当前为站长或者普通用户，先做自动登录，然后通知个页面调接口更新UI

            // 如果当前用户不是有课，不做自动登录
            notifyNetConnected(networkType);
        }
    }


    private void notifyNetConnected(int networkType) {
        for (NetStateChangeObserver observer :
                mObservers) {
            observer.onNetConnected(networkType);
        }
    }








}
