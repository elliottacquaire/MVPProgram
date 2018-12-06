

package com.li.mvpprogram.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.li.mvpprogram.utils.net.NetStateChangeObserver;
import com.li.mvpprogram.utils.net.NetStateChangeReceiver;
import com.trello.rxlifecycle2.components.support.RxFragment;
import io.reactivex.disposables.Disposable;


/**
 * fragment基类
 */
public abstract class BaseFragment extends RxFragment implements NetStateChangeObserver {

    protected BaseNativeActivity mActivity;
    /**
     * butterknife 解绑对象
     */
    private Unbinder unbinder;

    /*解绑 rxjava disposable  */
    protected Disposable disposable;

    /**
     * 获取fragment布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseNativeActivity) {
            this.mActivity = (BaseNativeActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        // 设置butterknife
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 注册网络连接监听
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        mActivity = null;
        //解决APP退到后台接收不到网络监听的问题。
        // 解绑网络连接监听
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind(); // 解除绑定
        }
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    public void onNetDisconnected() {

    }

    @Override
    public void onNetConnected(int networkType) {

    }

    /**
     * 是否需要注册网络变化的Observer，如果不需要监听网络变化，则返回false，否则返回true,默认返回false
     * @return
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return false;
    }


}
