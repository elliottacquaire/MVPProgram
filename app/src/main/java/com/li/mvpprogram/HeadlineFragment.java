package com.li.mvpprogram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.bean.BannerVo;
import com.li.mvpprogram.bean.HeadLineDataEvent;
import com.li.mvpprogram.bean.MapVo;
import com.li.mvpprogram.bean.RefreshFromChannelEvent;
import com.li.mvpprogram.discovery.Channel.ChannelManageActivity;
import com.li.mvpprogram.discovery.HeadlineContract;
import com.li.mvpprogram.discovery.HeadlinePresenter;
import com.li.mvpprogram.discovery.MyFragmentPagerAdapter;
import com.li.mvpprogram.discovery.RecommendFragment;
import com.li.mvpprogram.utils.ActivityUtils;
import com.li.mvpprogram.utils.StringUtils;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.behavior.CustomConvenientBanner;
import com.li.mvpprogram.widget.behavior.FoundHeaderPagerBehavior;
import com.li.mvpprogram.widget.view.HeadlineViewPager;
import com.li.mvpprogram.widget.view.NetworkImageHolderView;
import com.li.mvpprogram.widget.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.li.mvpprogram.discovery.Channel.ChannelManageFragment.CHANNELLIST;
import static com.li.mvpprogram.discovery.HeadlinePresenter.BANNER_ID;


/**
 */
public class HeadlineFragment extends BaseFragment implements HeadlineContract.View, FoundHeaderPagerBehavior.OnPagerStateListener {
    public static final String HOT_TAB = "hotTab";
    public static final String HEALTH_TAB = "healthTab";
    public static final String FASHIONE_TAB = "fashioneTab";
    public static final String RECREATION_TAB = "recreationTab";
    private static final String QUERY_NEWERREADCODE = "NOVICE_URL"; // 查询新手必看链接
    private static final String QUERY_ACTIVITYCODE = "WONDERFUL_ACTIVITY_URL"; // 查询精彩活动链接


    @BindView(R.id.vp_view_news)
    HeadlineViewPager mViewPager;
    @BindView(R.id.tabs_news)
    TabLayout mTabLayout;
    @BindView(R.id.net_tips_tv)
    TextView netTipsTv; // 网络不可用提示
    @BindView(R.id.id_found_header)
    FrameLayout mHeaderView;
    @BindView(R.id.xl_head_linear)
    LinearLayout xl_head_linear;
    @BindView(R.id.channel_relative)
    RelativeLayout channel_relative;
    @BindView(R.id.convenientBanner)
    CustomConvenientBanner mConvenientBanner;

    @BindView(R.id.newerRead_tv)
    TextView newerRead_tv;
    @BindView(R.id.activity_tv)
    TextView activity_tv;
    @BindView(R.id.study_tv)
    TextView study_tv;

    private MyFragmentPagerAdapter adapter;
    private List<MapVo> mTitleList = new ArrayList<>();//页卡标题集合
//    public static List<Fragment> fragmentList = new ArrayList<>();

    HeadlineContract.Presenter mPresenter;
    private boolean isUpdate = false;
    private int currentItem = 0;//当前展示页
    private String currentValue = "";

    private FoundHeaderPagerBehavior mHeaderPagerBehavior;

    private boolean isShow = true; //展开

    private List<BannerVo> bannerVos ;


    /**Banner*/
    NetworkImageHolderViewCBViewHolderCreator bannerItemHolder;

    private long currentMS;

    private float downX, downY;

    private int moveX, moveY;

    public static HeadlineFragment newInstance() {
        return new HeadlineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_headline;
    }

    @OnClick({R.id.img_manage_channel})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_manage_channel:
                if (mViewPager != null && mTitleList.size() > 0) {
                    currentValue = mTitleList.get(mViewPager.getCurrentItem()).getValue();
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable(CHANNELLIST, (Serializable) mTitleList);
                startActivity(new Intent(ChannelManageActivity.makeIntent(getActivity(), bundle)));
                break;

            default:
                break;
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new HeadlinePresenter(this);
        EventBus.getDefault().register(this);
        onInitBanner();
//        mPresenter.queryChannelList();
        bannerItemHolder = new NetworkImageHolderViewCBViewHolderCreator();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mHeaderView.getLayoutParams();
        mHeaderPagerBehavior = (FoundHeaderPagerBehavior) layoutParams.getBehavior();
        mHeaderPagerBehavior.setPagerStateListener(this);

        xl_head_linear.setVisibility(View.VISIBLE);
        channel_relative.setVisibility(View.GONE);

        onTouchListener();

        initDate();

    }

    private void initDate() {
        for (int i = 0 ; i< 10 ; i++){
            MapVo mapVo = new MapVo();
            mapVo.setKey("key"+i);
            mapVo.setValue("value"+i);
            mTitleList.add(mapVo);
        }

        initPageView(mTitleList);

        bannerVos = new ArrayList<BannerVo>();
        for (int i = 0; i< 4; i++){
            BannerVo bannerVo = new BannerVo();
            bannerVo.setId(BANNER_ID);
            if (i % 2 == 0){
                bannerVo.setBannerImage("http://img.zcool.cn/community/014565554b3814000001bf7232251d.jpg@1280w_1l_2o_100sh.png");
            }else {
                bannerVo.setBannerImage("http://pic28.photophoto.cn/20130818/0020033143720852_b.jpg");
            }

            bannerVos.add(bannerVo);
        }

        showIndexBanner(bannerVos,false);
    }

    public boolean isShow() {
        return isShow;
    }

    private void onTouchListener() {

        study_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleToucher(event, 0);
                return true;
            }
        });

        activity_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleToucher(event, 1);
                return true;
            }
        });

        newerRead_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleToucher(event, 2);
                return true;
            }
        });

    }

    private void handleToucher(MotionEvent event, int type) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();//float DownX
                downY = event.getY();//float DownY
                moveX = 0;
                moveY = 0;
                currentMS = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX += Math.abs(event.getX() - downX);//X轴距离
                moveY += Math.abs(event.getY() - downY);//y轴距离
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                long moveTime = System.currentTimeMillis() - currentMS;//移动时间
                //判断是否继续传递信号
                if (moveTime < 200 && (moveX < 20 && moveY < 20)) {
                    if (!StringUtils.isFastClick()) {
                        switch (type) {
                            case 0: //学习专区
//                                startActivity(StudyAreaActivity.makeIntent(getActivity(), null));
                                break;
                            case 1: //精彩活动
                                if (mPresenter != null) {
                                    mPresenter.getFoundH5(QUERY_ACTIVITYCODE);
                                }

                                break;
                            case 2: //新手必读
                                if (mPresenter != null) {
                                    mPresenter.getFoundH5(QUERY_NEWERREADCODE);
                                }

                                break;
                            default:
                                break;
                        }
                    }
                }

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 查询频道列表
        if (isUpdate) {
//            mPresenter.queryChannelList();
            initPageView(mTitleList);
        }
        isUpdate = false;
    }

    private void initPageView(List<MapVo> mapVoList) {
        mTitleList = mapVoList;
        Fragment[] fragmentArray = new Fragment[mapVoList.size()];
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式

//        fragmentList.clear();
        for (int i = 0; i < mapVoList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mapVoList.get(i).getValue()));//添加tab选项卡
            if (!TextUtils.isEmpty(currentValue) && mapVoList.get(i).getValue().equals(currentValue)) {
                currentItem = mapVoList.indexOf(mapVoList.get(i));
            }

            RecommendFragment recommendFragment = new RecommendFragment();
            Bundle bundle = new Bundle();
            bundle.putString(RecommendFragment.TAB, mapVoList.get(i).getKey());
            recommendFragment.setArguments(bundle);
            fragmentArray[i] = recommendFragment;
//            fragmentList.add(recommendFragment);
        }

        if (adapter == null) {
            adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
//            adapter.setFragmentPages(fragmentArray);
        }

        adapter.setPageTitles(mapVoList);
        adapter.setFragmentPages(fragmentArray);
        adapter.notifyDataSetChanged();

        if (mViewPager.getAdapter() == null)
            mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        mViewPager.setScanScroll(!isShow);
        mViewPager.setCurrentItem(currentItem);
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                } else if (position == 1) {
                } else if (position == 2) {
                } else if (position == 3) {
                } else if (position == 4) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void setPresenter(HeadlineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMsgTip(String msg) {
        ToastUtils.show(mActivity, msg);
    }

    @Override
    public void displayChannelList(List<MapVo> mapVoList) {
        if (mapVoList == null || mapVoList.size() < 1) return;
//        initPageView(mapVoList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /*查看返回更新频道 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateChannel(RefreshFromChannelEvent even) {
        if (even != null) {
            isUpdate = true;
            if (even.getMapVoList() == null || even.getMapVoList().size() < 1) return;
//            initPageView(even.getMapVoList());
            mTitleList = even.getMapVoList();
//            fragmentList = even.getFragmentList();
        }
    }


    @Override
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    @Override
    public void onNetDisconnected() {
        netTipsTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetConnected(int networkType) {
        netTipsTv.setVisibility(View.GONE);
        if (mPresenter == null) return;
        if (mTitleList != null && mTitleList.size() == 0) {
            mPresenter.queryChannelList();
        } else {
            //网络变化重新请求
            EventBus.getDefault().post(new HeadLineDataEvent());
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isShow && mViewPager != null) {
            mViewPager.setCurrentItem(0);
            if (adapter == null || mViewPager == null) return;
            ((RecommendFragment) adapter.getItem(mViewPager.getCurrentItem())).scrollToFirst(isShow);
            EventBus.getDefault().post(new HeadLineDataEvent());
        }
        if (!hidden && !isShow) {
            showBackShowHide();
        }
    }

    @Override
    public void onPagerClosed() {
        isShow = false;
        showBackShowHide();
        xl_head_linear.setVisibility(View.GONE);
        channel_relative.setVisibility(View.VISIBLE);
        mViewPager.setScanScroll(!isShow);
    }

    @Override
    public void onPagerOpened() {
        isShow = true;
        showBackShowHide();
        if (xl_head_linear == null || channel_relative == null || mViewPager == null) return;
        xl_head_linear.setVisibility(View.VISIBLE);
        channel_relative.setVisibility(View.GONE);
        mViewPager.setScanScroll(!isShow);
        if (adapter == null || mViewPager == null) return;
        ((RecommendFragment) adapter.getItem(mViewPager.getCurrentItem())).scrollToFirst(isShow);
        if (mHeaderPagerBehavior != null) {
            mHeaderPagerBehavior.openPager();
        }

    }

    private void showBackShowHide() {
        /**  发现页面 滑动顶部返回按键显示隐藏**/
        if (getActivity() instanceof MainActivity && ((MainActivity) getActivity()) != null) {
            ((MainActivity) getActivity()).setLeftIv(R.mipmap.back);
            ((MainActivity) getActivity()).showLeftIv(!isShow);
        }
    }

    public void startWebActivity(String url) {
        if (TextUtils.isEmpty(url)) return;
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.URL, url);
        //startActivity(WebViewActivity.makeIntent(mActivity, bundle));
        ActivityUtils.startWebActivity(mActivity, bundle, url);
    }

    /*初始化banner*/
    private void onInitBanner() {
        mConvenientBanner
                .setPageIndicatorAlign(CustomConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setPointViewVisible(true)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});

        //mPresenter.getIndexBanners(true);//显示默认数据
//        mPresenter.getIndexBanners(false);

        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bannerVos == null && bannerVos.size() - 1 < position) return;
                startWebActivity(bannerVos.get(position).getHrefUrl());
            }
        });
    }

    /*循环首页banner*/
    @Override
    public void startIndexBannerTurning() {
        if (mConvenientBanner != null) {
            mConvenientBanner.stopTurning();
            indexLoopHandler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    public void showFoundH5(String url) {
        startWebActivity(url);
    }

    /**/
    private Handler indexLoopHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != mConvenientBanner) {
                mConvenientBanner.startTurning(6000);
            }
        }
    };

    /*显示banner*/
    @Override
    public void showIndexBanner(List<BannerVo> result, boolean isNotifyDataSetAdd) {

//        bannerVos = result;

        if (mConvenientBanner == null) return;

        if (!isNotifyDataSetAdd ||
                mConvenientBanner.getViewPager() == null ||
                mConvenientBanner.getViewPager().getAdapter() == null
                ) {
            mConvenientBanner.setPages(bannerItemHolder, result);
        } else {
            mConvenientBanner.notifyDataSetChanged();
        }
        if (result.size() == 1) {
            mConvenientBanner.stopTurning();
        } else {
            startIndexBannerTurning();
        }

    }

    /*包装NetworkImageHolderView*/
    private class NetworkImageHolderViewCBViewHolderCreator implements CBViewHolderCreator<NetworkImageHolderView> {
        @Override
        public NetworkImageHolderView createHolder() {
            return new NetworkImageHolderView(HeadlineFragment.this);
        }
    }

}
