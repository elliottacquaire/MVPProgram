package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import com.li.mvpprogram.R;


/**
 * [类功能说明]
 *
 * @author ex-heguogui
 * @version v 1.0.0 2016/11/2 19:24 XLXZ Exp $
 * @email ex-heguoguo@xianglin.cn
 */


public class MySwipeRefresh extends SwipeRefreshLayout implements AbsListView.OnScrollListener {


    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;
    /**
     * RecyclerView 实例
     */
    private RecyclerView recyclerView;
    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;
    /**
     * ListView的加载中footer
     */
    private View mFooterView;
    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
//    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;
    private float mLastY = -1;
    /**
     * 最后一行
     */
    boolean isLastRow = false;
    /**
     * 是否还有数据可以加载
     */
    private boolean moreData = true;

    public MySwipeRefresh(Context context) {
        super(context);
    }

    public MySwipeRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mFooterView = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    /**
     * 设置默认的childview-必须要设置
     *
     * @param context
     * @param childView
     */
    public void setView(Context context, View childView) {
        if (childView instanceof ListView) {
            mListView = (ListView) childView;
            // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
            mListView.setOnScrollListener(this);
            mListView.setFooterDividersEnabled(false);
        } else if (childView instanceof RecyclerView) {
            recyclerView = (RecyclerView) childView;
            // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                }
            });
        }
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        if (mListView != null)
            mListView.addFooterView(mFooterView, null, false);//设置footview不可点击
//        if (recyclerView!=null){
//            Log.d("VRefresh","快去自定义recyclerView 加footview吧");
//            return;
//        }
        mFooterView.setVisibility(View.GONE);//默认先隐藏
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 是否可以加载更多, listview不在加载中, 且为上拉操作.
     *
     * @param canload 是否还有可以加载的数据
     * @return
     */
    private boolean canLoad(boolean canload) {
        return canload && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.onLoadMore
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            // 设置状态
            setLoading(true);
            mOnLoadListener.onLoadMore();
        }
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
//            mListView.addFooterView(mFooterView);
            mFooterView.setVisibility(View.VISIBLE);
        } else {
//            mListView.removeFooterView(mFooterView);
            mFooterView.setVisibility(View.GONE);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /*
       * (non-Javadoc)
       * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
       */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (mLastY == -1) {
            mLastY = (int) event.getRawY();
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                final float deltaY = event.getRawY() - mLastY;
//                if (null != mListView && isPullUp()) {
//                    if (mListView.getLastVisiblePosition() == mTotalItemCount - 1 || deltaY < 0) {
//                        mListView.setSelection(mTotalItemCount - 1);
//                    }
//                }
                break;
            default:
//                if (isLastRow && canLoad(moreData)) {
//                    Log.d("VRefresh", "能加载...");
//                    loadData();
//                } else {
//                    Log.d("VRefresh", "不能加载...");
//                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断是否滚到最后一行
        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            //双重判断，应该没有必要
            if (absListView.getLastVisiblePosition() == (absListView.getAdapter().getCount() - 1)) {
                isLastRow = true;
//                mFooterView.setVisibility(View.VISIBLE);
                if (canLoad(moreData)) {
                    loadData();
                }
            }
        } else {
            isLastRow = false;
        }
    }

    public static interface OnLoadListener {
        public void onLoadMore();
    }

    public boolean isMoreData() {
        return moreData;
    }

    public void setMoreData(boolean moreData) {
        this.moreData = moreData;
    }


}
