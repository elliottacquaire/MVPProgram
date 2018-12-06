/**/

package com.li.mvpprogram.http;

/**
 */
public class TransformUtils {
    /**
     * 默认计划,发布在io线程,订阅在ui线程
     *
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }

    /**
     * IO计划,发布和订阅都在IO线程
     *
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<T, T> allIoSchedulers() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
//    }

    /**
     * RxLifecycle 自动管理
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<T, T> defaultSchedulers(BaseView baseView) {
//        if(null != baseView && baseView instanceof BaseFragment) {
//            return tObservable -> tObservable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .compose(((BaseFragment) baseView).bindToLifecycle());
//        }
//        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
}
