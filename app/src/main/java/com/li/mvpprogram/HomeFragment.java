package com.li.mvpprogram;


import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.home.GrideBean;
import com.li.mvpprogram.home.holder.GrideHolder;
import com.li.mvpprogram.home.holder.HomeCarouselHolder;
import com.li.mvpprogram.home.holder.ItemHolder;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.allrecycleview.RefrushRecycleView;
import com.li.mvpprogram.widget.allrecycleview.adapter.RefrushAdapter;
import com.li.mvpprogram.widget.allrecycleview.holder.CustomHolder;
import com.li.mvpprogram.widget.allrecycleview.inter.DefaultAdapterViewLisenter;
import com.li.mvpprogram.widget.allrecycleview.inter.DefaultRefrushListener;
import com.li.mvpprogram.widget.allrecycleview.listener.OnToolsItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements OnToolsItemClickListener<GrideBean> {

    private RefrushRecycleView refrushRecycleView;
    private RefrushAdapter<String> adapter;
    private List<String> lists;
    private GrideHolder holder_up;
    private HomeCarouselHolder carouselHolder;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        refrushRecycleView = (RefrushRecycleView) view.findViewById(R.id.rc_home);
        lists=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lists.add("ITEM"+i);
        }

        adapter = new RefrushAdapter<>(getActivity(), lists, R.layout.item, new DefaultAdapterViewLisenter() {
            @Override
            public CustomHolder getBodyHolder(Context context, List lists, int itemID) {
                return new ItemHolder(  context,   lists,   itemID);
            }
        });


        //显示下拉刷新
        refrushRecycleView.setHasTop(true);
        refrushRecycleView.setRefrushListener(new DefaultRefrushListener() {
            @Override
            public void onLoading() {
                super.onLoading();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        refrushRecycleView.post(new Runnable() {
                            @Override
                            public void run() {
                                refrushRecycleView.loadSuccess();
                            }
                        });

                    }
                }).start();

            }
        });
        /**
         * 设置下拉刷新位置
         */
        adapter.setRefrushPosition(1);

        //上侧九宫格

        List<GrideBean> UP = new ArrayList<>();
        UP.add(new GrideBean(getString(R.string.phone), R.mipmap.phone_recharge));
        UP.add(new GrideBean(getString(R.string.life_recharge), R.mipmap.life_recharge));
        UP.add(new GrideBean(getString(R.string.traffic), R.mipmap.phone_recharge));
        UP.add(new GrideBean(getString(R.string.tick), R.mipmap.life_recharge));
        UP.add(new GrideBean(getString(R.string.game_recharge), R.mipmap.phone_recharge));
        UP.add(new GrideBean(getString(R.string.gas_recharge), R.mipmap.life_recharge));
        UP.add(new GrideBean(getString(R.string.lottery), R.mipmap.phone_recharge));
        UP.add(new GrideBean(getString(R.string.gongyi), R.mipmap.life_recharge));
        UP.add(new GrideBean(getString(R.string.share_media), R.mipmap.phone_recharge));
        UP.add(new GrideBean(getString(R.string.credit_payment), R.mipmap.life_recharge));
        UP.add(new GrideBean(getString(R.string.train), R.mipmap.phone_recharge));

        holder_up = new GrideHolder(getContext(), UP, R.layout.item);
        holder_up.setOnTOnToolsItemClickListener(this);
        adapter.addHead(holder_up);


        List<GrideBean> list = new ArrayList<>();
        list.add(new GrideBean("", R.mipmap.home_banner1));
        list.add(new GrideBean("", R.mipmap.home_banner2));
        list.add(new GrideBean("", R.mipmap.home_banner3));
        list.add(new GrideBean("", R.mipmap.home_banner4));
        list.add(new GrideBean("", R.mipmap.home_banner5));

        //中间广告条
        carouselHolder = new HomeCarouselHolder(getContext(), list, R.layout.item_home_carousel);
        adapter.addHead(carouselHolder);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        refrushRecycleView.setLayoutManager(manager);
        refrushRecycleView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position, GrideBean item) {
        ToastUtils.show(getContext(),item.title);

    }


}
