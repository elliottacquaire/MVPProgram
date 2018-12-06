package com.li.mvpprogram.discovery;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.li.mvpprogram.HeadlineFragment;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.bean.HeadLineDataEvent;
import com.li.mvpprogram.bean.MsgVo;
import com.li.mvpprogram.discovery.recommend.MsgVoMuti;
import com.li.mvpprogram.discovery.recommend.RecommendAdapter;
import com.li.mvpprogram.discovery.recommend.RecommendNewsContract;
import com.li.mvpprogram.discovery.recommend.RecommendPresenter;
import com.li.mvpprogram.utils.StringUtils;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.view.MySwipeRefreshLayout;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.li.mvpprogram.discovery.recommend.MsgVoMuti.TEXTTIPS;


/**
 * 推荐新闻界面
 */
public class RecommendFragment extends BaseFragment implements RecommendNewsContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    private static final int SHOW_FAG_TO_TOP = 20;
    @BindView(R.id.fragment_headline_news_swipe_refresh_layout)
    MySwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.news_xrecycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.data_empty)
    RelativeLayout dataEmpty;

    @BindView(R.id.fab)
    FloatingActionButton iv_to_top;
    @BindView(R.id.tv_refresh_num)
    TextView videoRefreshNum;
    @BindView(R.id.earningdetail_network_error)
    LinearLayout networkEerror;
    //下拉刷新标识
    private boolean pullDownToRefresh = true;
    private int local = 0; //location  提醒位置

    public static String TAB = "tab";
    public static final int SHOW_TIME = 1000;
    private String mTab;
    private RecommendNewsContract.Presenter mPresenter;
    private RecommendAdapter mRecyclerViewAdapter;

    private List<MsgVoMuti> msgVosList = new ArrayList<>();

    //控件是否已经初始化
    private boolean isCreateView = false;

    //是否已经加载过数据
    private boolean isLoadData = false;

    //是否可见
    private boolean isVisiToUser = false;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisiToUser = true;
            isLoadData = true;
            lazyLoad();
        } else {
            isVisiToUser = false;
            isLoadData = false;
        }
    }

    private void lazyLoad() {
        //如果没有加载过就加载，否则就不再加载了
        if (isCreateView && isLoadData) {
//            if (mPresenter == null) return;
//            mPresenter.loadingDate(mTab, false);
            initDate();

            //数据加载完毕,恢复标记,防止重复加载
            isCreateView = false;
            isLoadData = false;
        }

    }

    private void initDate() {

        List<MsgVo> msgVoListL = new ArrayList<>();
        for (int i = 0; i< 10 ; i++){
            MsgVo msgVo = new MsgVo();
            msgVo.setId((long)i);
            if (i % 3 == 0){
                msgVo.setMsgTag("VIDEO");
            }else if (i % 3 == 1){
                msgVo.setMsgTag("VIDdEO");
            }else if (i % 3 == 2){
                msgVo.setMsgTag("VIDdEdO");
                msgVo.setTitleImgList("http://pic28.photophoto.cn/20130818/0020033143720852_b.jpg");
            }
            msgVoListL.add(msgVo);
        }
        pullDownToRefresh = false;
        showNewsList(msgVoListL, false);

    }

    public void updateArguments(String key) {
        this.mTab = key;
        Bundle args = getArguments();
        if (args != null) {
            args.putString(RecommendFragment.TAB, key);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        isCreateView = true;
        EventBus.getDefault().register(this);
        new RecommendPresenter(mActivity, this);
        if (getArguments() != null && getArguments().getString(TAB) != null && !TextUtils.isEmpty(getArguments().getString(TAB)))
            mTab = getArguments().getString(TAB);

        initSwipeRefreshLayout();
        initRecycleView();
//        mPresenter.loadingDate(mTab, false);
        pullDownToRefresh = true;
        lazyLoad();
        Logger.i("recommend", mTab);
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (baseQuickAdapter == null) {
                    return;
                }
                List<MsgVoMuti> messageBeanList = baseQuickAdapter.getData();
                if (messageBeanList == null || messageBeanList.size() <= position) {
                    return;
                }

                //提示列点击刷新
                if (messageBeanList.get(position).getItemType() == TEXTTIPS) {
                    mPresenter.loadingDate(mTab, false);
                    return;
                }

                //已读标志
                if (!messageBeanList.get(position).isRead()) {
                    messageBeanList.get(position).setRead(true);
                    mRecyclerViewAdapter.notifyItemChanged(position);
                }

                //乡邻新闻
                if (mTab != null && HeadlineFragment.HOT_TAB.equals(mTab)) {
                    //社会热点
                } else if (mTab != null && HeadlineFragment.RECREATION_TAB.equals(mTab)) {
                    //时尚潮流
                } else if (mTab != null && HeadlineFragment.FASHIONE_TAB.equals(mTab)) {
                    //健康咨询
                } else if (mTab != null && HeadlineFragment.HEALTH_TAB.equals(mTab)) {
                }
//                if (messageBeanList.get(position).getMsgVo() == null ||
//                        TextUtils.isEmpty(messageBeanList.get(position).getMsgVo().getUrl()) ||
////                        TextUtils.isEmpty(messageBeanList.get(position).getMsgVo().getTitleImg()) ||
//                        TextUtils.isEmpty(messageBeanList.get(position).getMsgVo().getMsgTitle()) ||
//                        TextUtils.isEmpty(messageBeanList.get(position).getMsgVo().getMessage()) ||
//                        TextUtils.isEmpty(messageBeanList.get(position).getMsgVo().getMsgTag()) ||
//                        messageBeanList.get(position).getMsgVo().getId() < 0) {
//                    return;
//                }

                // 通知服务器该新闻已读
//                mPresenter.operateVideoNews(Constant.MsgOperateType.READ.name(), messageBeanList.get(position).getMsgVo().getId());

//                if (messageBeanList.get(position).getMsgVo().getMsgTag().equals("VIDEO")) {
////                    intent = new Intent(mActivity, VideoPlayerActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(VideoPlayerActivity.VIDEO_OBJ, messageBeanList.get(position).getMsgVo());
//                    startActivity(VideoPlayerActivity.makeIntent(mActivity, bundle));
//                } else {
//                    Intent intent = new Intent(mActivity, NewsWebViewActivity.class);
//                    intent.putExtra(WebViewActivity.NEWS_ID, messageBeanList.get(position).getMsgVo().getId());
//                    startActivity(intent);
//                }

            }
        });

        //返回顶部
//        iv_to_top.setOnClickListener(view -> {
//
//            if (mRecyclerView == null) return;
//            int lastVisiblePosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
//            mRecyclerView.smoothScrollToPosition(0);
//            if (lastVisiblePosition > SHOW_FAG_TO_TOP) {
//                mRecyclerView.scrollToPosition(10);
//                mRecyclerView.smoothScrollToPosition(0);
//            } else {
//                mRecyclerView.smoothScrollToPosition(0);
//            }
//
//        });

        //判定滑动到20条后显示浮动按钮
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mRecyclerView == null) return;
                int lastVisiblePosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();

                if (lastVisiblePosition >= SHOW_FAG_TO_TOP && dy < 0) {
                    showFloatBtnState(true);
                } else {
                    showFloatBtnState(false);
                }
            }
        });

        initAdapter();

    }

    private void initAdapter() {

        mRecyclerViewAdapter = new RecommendAdapter(getActivity(), this, msgVosList);
        mRecyclerViewAdapter.setEnableLoadMore(true);
        mRecyclerViewAdapter.setOnLoadMoreListener(this);
        if (mRecyclerView == null) return;
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.blue_btn_bg_color),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mPresenter.loadingDate(mTab, false);//下拉刷新
                pullDownToRefresh = false;
            }
        });
    }

    @Override
    public void showRefreshNewsNum(int num) {
        if (num == 0) {
            return;
        }
        if (pullDownToRefresh) {
            return;
        }
        if (videoRefreshNum == null) return;
        videoRefreshNum.setVisibility(View.VISIBLE);
//        videoRefreshNum.setText(getString(R.string.video_refresh_test, "" + num));
        if (mActivity == null) return;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (mActivity != null) {
                    videoRefreshNum.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.anim_slide_out_from_top));
                    videoRefreshNum.setVisibility(View.GONE);
                }

            }
        }, SHOW_TIME);
    }

    @Override
    public void showFloatBtnState(boolean state) {
        if (state) {
            if (iv_to_top != null && !iv_to_top.isShown()) {
                iv_to_top.show();
            }
        } else {
            if (iv_to_top != null && iv_to_top.isShown()) {
                iv_to_top.hide();
            }
        }
    }

    @Override
    public void showNewsList(List<MsgVo> list, boolean isLoadMore) {
        if (list == null || msgVosList == null) {
            return;
        }

        if (!pullDownToRefresh) {
            local = list.size();
            //下拉刷新
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMsgTag().equals("VIDEO")) {
                    msgVosList.add(i, new MsgVoMuti(MsgVoMuti.VIDEO, list.get(i)));
                } else {
                    if (TextUtils.isEmpty(list.get(i).getTitleImgList())) {
                        msgVosList.add(i, new MsgVoMuti(MsgVoMuti.SINGLEIMG, list.get(i)));
                    } else {
                        String[] imgUrl = list.get(i).getTitleImgList().split(",");
                        if (imgUrl.length > 2) {
                            msgVosList.add(i, new MsgVoMuti(MsgVoMuti.THREEIMG, list.get(i)));
                        } else {
                            msgVosList.add(i, new MsgVoMuti(MsgVoMuti.SINGLEIMG, list.get(i)));
                        }
                    }

                }
            }

            if (list.size() > 0) {
                local = list.size() + local;
                msgVosList.add(list.size(), new MsgVoMuti(TEXTTIPS, null));
                if (local != list.size() && (local + 1) < msgVosList.size()) {
                    msgVosList.remove(local + 1);
                }

            }
            if (mRecyclerView != null) {
                mRecyclerView.scrollToPosition(0);
            }

        } else {
            for (MsgVo msgVo : list) {
                if (msgVo.getMsgTag().equals("VIDEO")) {
                    msgVosList.add(new MsgVoMuti(MsgVoMuti.VIDEO, msgVo));
                } else {
                    if (TextUtils.isEmpty(msgVo.getTitleImgList())) {
                        msgVosList.add(new MsgVoMuti(MsgVoMuti.SINGLEIMG, msgVo));
                    } else {
                        String[] imgUrl = msgVo.getTitleImgList().split(",");
                        if (imgUrl.length > 2) {
                            msgVosList.add(new MsgVoMuti(MsgVoMuti.THREEIMG, msgVo));
                        } else {
                            msgVosList.add(new MsgVoMuti(MsgVoMuti.SINGLEIMG, msgVo));
                        }
                    }

                }
            }
        }
        if (mRecyclerViewAdapter == null && mRecyclerView != null) {
            mRecyclerViewAdapter = new RecommendAdapter(getActivity(), this, msgVosList);
            mRecyclerViewAdapter.setNewData(msgVosList);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
        } else {
            mRecyclerViewAdapter.setNewData(msgVosList);
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 显示或隐藏圆圈指示器
     */
    private void setLoadingIndicator(boolean active) {
        if (swipeRefreshLayout == null) {
            return;
        }
//        swipeRefreshLayout.post(() -> {
//            if (swipeRefreshLayout != null)
//                swipeRefreshLayout.setRefreshing(active);
//        });
    }

    /**
     * 网络异常点击刷新
     *
     * @param view
     */
    @OnClick({R.id.earningdetail_network_error})
    public void onClick(View view) {
        if (view.getId() == R.id.earningdetail_network_error) {
            if (!StringUtils.isFastClick()) {
//                mPresenter.loadingDate(mTab, false);
                pullDownToRefresh = true;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        setLoadingIndicator(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * offline：网络出错的view；
     * zerofans:联网成功，但关注人数为0的view(在serchview下面)；
     * manyfans;联网成功，关注人数大于0的view；
     */
    @Override
    public void showDateEmptyView(boolean offline, boolean zerofans, boolean manyfans) {
        if (dataEmpty != null) {
            dataEmpty.setVisibility(zerofans ? View.VISIBLE : View.GONE);
        }
        if (networkEerror != null) {
            networkEerror.setVisibility(offline ? View.VISIBLE : View.GONE);
        }
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setVisibility(manyfans ? View.VISIBLE : View.GONE);
        }

    }

    @Override
    public void loadMoreComplete() {
        mRecyclerViewAdapter.loadMoreComplete();
    }

    @Override
    public void loadMoreEnd() {
        mRecyclerViewAdapter.loadMoreEnd();
    }

    @Override
    public void loadMoreFail() {
        mRecyclerViewAdapter.loadMoreFail();
    }

    @Override
    public void showMsg(String msg) {
        if (mActivity == null) return;
        ToastUtils.show(mActivity, msg);
    }

    @Override
    public void showLoadingIndicator() {
        setLoadingIndicator(true);
    }

    @Override
    public void hideLoadningIndicator() {
        setLoadingIndicator(false);
    }

    @Override
    public void setPresenter(RecommendNewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoadMoreRequested() {
        ToastUtils.show(getActivity(),"onloadmaorrequest");
//        mPresenter.loadingDate(mTab, true);
//        pullDownToRefresh = true;
    }

    /*网络恢复后通知*/
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onNoticeTips(HeadLineDataEvent even) {
        if (even != null && isVisiToUser) {
            if (mPresenter == null) return;
            mPresenter.loadingDate(mTab, false);
            pullDownToRefresh = false;
        }

    }

    public void tooglePager(boolean isOpen) {
        if (isOpen) {
            setRefreshEnable(false);
            scrollToFirst(false);
        } else {
            setRefreshEnable(true);
        }
    }

    public void scrollToFirst(boolean isSmooth) {
        if (mRecyclerView == null) {
            return;
        }
        if (isSmooth) {
            mRecyclerView.smoothScrollToPosition(0);
        } else {
            mRecyclerView.scrollToPosition(0);
        }
    }

    public void setRefreshEnable(boolean enabled) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(enabled);
        }

    }

}
