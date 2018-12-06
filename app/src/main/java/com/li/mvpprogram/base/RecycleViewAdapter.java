/**/
package com.li.mvpprogram.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleViewAdapter基类
 */

public abstract class RecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    //上下文
    protected Context mContext;
    //数据源
    protected List<T> mListDatas;
    //布局id
    protected int mLayoutId;
    //布局管理器
    protected LayoutInflater mInflater;

    //对外提供item监听
    protected OnItemClickListener onItemClickListener;

    public RecycleViewAdapter(Context context, List<T> datas, int layoutId) {
        mListDatas = new ArrayList<T>();
        mContext = context;
        mListDatas.addAll(datas);
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 创建ViewHolder,RecycleView内部已经处理了复用问题，此处只需要创建对象即可
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        return setRecycleViewHolder(view);
    }

    /**
     * View和holder绑定时调用
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, int position) {
        //设置item点击时的背景
        setItemBackgroundOnClick(holder);
        //item点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //避免滑动过程中出现位置错乱使用 holder.getLayoutPosition()
                    int _layoutPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemClickListener(holder.itemView, mListDatas.get(_layoutPosition), _layoutPosition);
                }
            });
        }
        //对外提供扩展接口
        convertOnBindViewHolder(holder, mListDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    /**
     * 对外item点击监听
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    /**
     * 设置数据源
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        if (datas != null) {
            this.mListDatas.clear();
            this.mListDatas.addAll(datas);
        }
    }

    //对外提供item点击时的背景色,默认不实现
    public void setItemBackgroundOnClick(RecycleViewHolder holder) {

    }

    //设置RecycleViewHolder
    public abstract RecycleViewHolder setRecycleViewHolder(View itemView);

    //转换View 和 holder绑定事件
    public abstract void convertOnBindViewHolder(RecycleViewHolder holder, T data, int position);

    /**
     * 自定义RecycleView item的点击事件回调监听
     */
    public interface OnItemClickListener<T> {
        void onItemClickListener(View view, T vo, int position);
    }
}
