package com.li.mvpprogram.discovery;

import android.text.TextUtils;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.bean.BannerVo;
import com.li.mvpprogram.bean.MapVo;
import com.li.mvpprogram.config.ApiMethod;
import com.li.mvpprogram.exception.HttpThrowable;
import com.li.mvpprogram.http.HttpResponseSubscriber;
import com.li.mvpprogram.http.RetrofitUtil;
import com.li.mvpprogram.http.RpcHelper;
import com.li.mvpprogram.utils.AppUtils;
import com.li.mvpprogram.utils.ToastUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;



/**
 *
 */
public class HeadlinePresenter implements HeadlineContract.Presenter {
    public static final long BANNER_ID = -999L;//默认banner的id
    private final String DISCOVER = "DISCOVER ";
    private HeadlineContract.View mView;
    private CompositeDisposable compositeDisposable;
    private List<BannerVo> mBannerVoList;
    private boolean isNotifyDataSetAdd;

    public HeadlinePresenter(HeadlineContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
        isNotifyDataSetAdd = false;
        mBannerVoList = new ArrayList<BannerVo>();
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    // 查询频道列表
    @Override
    public void queryChannelList() {
        List<Object> list = new ArrayList<>();
        compositeDisposable.clear();
        RetrofitUtil.createService()
                .queryChannel(RpcHelper.getParamMap(ApiMethod.METHOD_QUERY_CHANNEL, list))
//                .compose(TransformUtils.defaultSchedulers(mView))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResponseSubscriber<List<MapVo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<MapVo> result) {
                        if (result == null) {
                            return;
                        }
                        mView.displayChannelList(result);
                    }

                    @Override
                    public void _onError(HttpThrowable e) {
                        mView.showMsgTip(e.getMessage());
                    }
                });
    }

    /*获取首页banner*/
    @Override
    public void getIndexBanners(boolean isDefault) {

        if (isDefault) {
            showIndexBanner(null);
            return;
        }

        List<Object> list = new ArrayList<>();
        list.add(AppUtils.getAppInfo(BaseApplication.getInstance()).getVersionName());
        list.add(DISCOVER);
//        mView.showLoadingIndicator();

        RetrofitUtil.createService()
                .getFoundBanners(RpcHelper.getParamMap(ApiMethod.METHOD_QUERY_INDEXBANNERS, list))
//                .compose(TransformUtils.defaultSchedulers(mView))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResponseSubscriber<List<BannerVo>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);

                    }

                    @Override
                    public void onSuccess(List<BannerVo> result) {

                        showIndexBanner(result);
                        mView.startIndexBannerTurning();
//                        mView.hideLoadningIndicator();
                    }

                    @Override
                    public void _onError(HttpThrowable e) {
//                        mView.hideLoadningIndicator();
                        ToastUtils.show(BaseApplication.getInstance(), e.getMessage());
                        showIndexBanner(null);
                    }
                });


    }

    @Override
    public void getFoundH5(String type) {
        if (TextUtils.isEmpty(type))return;
        ArrayList<String> list = new ArrayList<>();
        list.add(type);
//        RetrofitUtil.createService()
//                .queryPara(RpcHelper.getParamMap(ApiMethod.METHOD_QUERY_PARA, list))
//                .compose(TransformUtils.defaultSchedulers(mView))
//                .subscribe(new HttpResponseSubscriber<SysParaVo>() {
//                    @Override
//                    public void onSuccess(SysParaVo result) {
//                        if (result == null || TextUtils.isEmpty(result.getValue())) {
//                            return;
//                        }
//                        mView.showFoundH5(result.getValue());
//                    }
//
//                    @Override
//                    public void _onError(HttpThrowable e) {
//                        mView.showMsgTip(e.getMessage());
//                    }
//                });
    }

    private void showIndexBanner(List<BannerVo> result) {

        if (result == null || result.isEmpty()) {
            result = new ArrayList<BannerVo>();
            BannerVo bannerVo = new BannerVo();
            bannerVo.setId(BANNER_ID);
            result.add(new BannerVo());
        }

        mBannerVoList.clear();

        mBannerVoList.addAll(result);

        mView.showIndexBanner(mBannerVoList, isNotifyDataSetAdd);

        isNotifyDataSetAdd = true;

    }

}
