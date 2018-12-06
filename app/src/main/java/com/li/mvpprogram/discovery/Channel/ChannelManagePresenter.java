package com.li.mvpprogram.discovery.Channel;

import com.li.mvpprogram.bean.MapVo;
import io.reactivex.disposables.CompositeDisposable;

import java.util.ArrayList;
import java.util.List;


public class ChannelManagePresenter implements ChannelManageContract.Presenter {

    private ChannelManageContract.View mView;
    private CompositeDisposable compositeDisposable;

    public ChannelManagePresenter(ChannelManageContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
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

    //保存排序
    @Override
    public void saveChannelList(List<MapVo> channelList) {
        if (channelList == null || channelList.size()<=0)return;
        compositeDisposable.clear();
        List<Object> list = new ArrayList<>();
        list.add(channelList);
//        RetrofitUtil.createService()
//                .updateChannel(RpcHelper.getParamMap(ApiMethod.METHOD_UPDATE_CHANNEL, list))
//                .compose(TransformUtils.defaultSchedulers(mView))
//                .subscribe(new HttpResponseSubscriber<Boolean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        super.onSubscribe(d);
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onSuccess(Boolean result) {
//                        if (result == null) {
//                            return;
//                        }
//                        mView.displayChannelList(result);
//                    }
//
//                    @Override
//                    public void _onError(HttpThrowable e) {
//                        mView.showMsgTip(e.getMessage());
//                    }
//                });
    }
}
