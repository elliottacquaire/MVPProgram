package com.li.mvpprogram.discovery.recommend;

import android.content.Context;
import com.li.mvpprogram.bean.MsgQuery;
import com.li.mvpprogram.bean.MsgVo;
import com.li.mvpprogram.config.ApiMethod;
import com.li.mvpprogram.exception.HttpThrowable;
import com.li.mvpprogram.http.HttpResponseSubscriber;
import com.li.mvpprogram.http.RetrofitUtil;
import com.li.mvpprogram.http.RpcHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendPresenter implements RecommendNewsContract.Presenter {

    private RecommendNewsContract.View mView;
    Context mContext;

    private int page = 1;
    private final static int pageSize = 10;
    private String mTab;

    private List<MsgVo> mList = new ArrayList<>();

    public RecommendPresenter(Context context, RecommendNewsContract.View view) {
        this.mContext = context;
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadingDate(String title, final boolean isLoadMore) {

        mView.showLoadingIndicator();
        if (!isLoadMore) {
            page = 1;
            mList.clear();
//            mView.showNewsList(mList, isLoadMore);
        } else {
            page++;
        }
        MsgQuery msgQuery = new MsgQuery();
        msgQuery.setMsgType(title);
        msgQuery.setPageSize(pageSize);
        msgQuery.setStartPage(page);
        List<MsgQuery> list = new ArrayList<>();
        list.add(msgQuery);
        RetrofitUtil.createService()
                .recommendNews(RpcHelper.getParamMap(ApiMethod.METHOD_RECOMMEND_NEWS, list))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResponseSubscriber<List<MsgVo>>() {

                    @Override
                    public void onSuccess(List<MsgVo> result) {

                        if (result == null) {
                            return;
                        }
                        if (!isLoadMore) {
                            mList.clear();
                        }
                        if (result.isEmpty()) {
                            if (page == 1) {
                                mView.showDateEmptyView(false, true, false);
                            } else {
                                mView.loadMoreEnd();
                            }
                            return;
                        }
                        mView.showDateEmptyView(false, false, true);
                        mList.addAll(result);
                        mView.showNewsList(result, isLoadMore);
                        if (result.size() < pageSize) {
                            mView.loadMoreEnd();
                        } else {
                            mView.loadMoreComplete();
                            mView.showRefreshNewsNum(mList.size());
                        }
                    }

                    @Override
                    public void _onError(HttpThrowable e) {
                        mView.showDateEmptyView(true, false, false);
                        mView.loadMoreFail();
                        mView.showMsg(e.getMessage());
                    }
                });
        mView.hideLoadningIndicator();
    }

    @Override
    public void operateVideoNews(String operationType, Long msgId) {
        MsgQuery msgQuery = new MsgQuery();
        msgQuery.setOperateType(operationType);
        msgQuery.setMsgId(msgId);
        RetrofitUtil.createService()
                .operateNews(RpcHelper.getParamMap(ApiMethod.METHOD_OPERATE_NEWS, Collections.singletonList(msgQuery)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResponseSubscriber<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {

                    }

                    @Override
                    public void _onError(HttpThrowable e) {
                        mView.showMsg(e.getMessage());
                    }
                });
    }


    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
