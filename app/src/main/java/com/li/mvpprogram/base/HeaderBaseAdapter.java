/**/

package com.li.mvpprogram.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 */
public abstract class HeaderBaseAdapter<T> extends RecyclerView.Adapter {


    public LayoutInflater getmLayoutInflater() {
        return mLayoutInflater;
    }

    public Context getmContext() {
        return mContext;
    }

    public List<T> getmList() {
        return mList;
    }

    protected final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private final List<T> mList;

    public void setmOnHeaderViewClickListener(OnHeaderViewClickListener mOnHeaderViewClickListener) {
        this.mOnHeaderViewClickListener = mOnHeaderViewClickListener;
    }

    public void setmOnItemViewClickListener(OnItemViewClickListener mOnItemViewClickListener) {
        this.mOnItemViewClickListener = mOnItemViewClickListener;
    }

    public OnHeaderViewClickListener getmOnHeaderViewClickListener() {
        return mOnHeaderViewClickListener;
    }

    public OnItemViewClickListener getmOnItemViewClickListener() {
        return mOnItemViewClickListener;
    }

    private OnHeaderViewClickListener mOnHeaderViewClickListener;
    private OnItemViewClickListener mOnItemViewClickListener;


    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_CONTENT
    }

    @Override
    public long getItemId(int position) {
        return position - 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM_TYPE.ITEM_HEAD.ordinal();
        } else {
            return ITEM_TYPE.ITEM_CONTENT.ordinal();
        }
    }

    public HeaderBaseAdapter(Context mContext, List<T> mList, OnHeaderViewClickListener mOnHeaderViewClickListener, OnItemViewClickListener mOnItemViewClickListener) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mList = mList;
        this.mOnHeaderViewClickListener = mOnHeaderViewClickListener;
        this.mOnItemViewClickListener = mOnItemViewClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return onCreateHeaderViewHolder(parent);
        } else if (viewType == ITEM_TYPE.ITEM_CONTENT.ordinal()) {
            return onCreateItemViewHolder(parent);
        }
        return null;

    }

    public abstract BaseViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    public abstract BaseViewHolder onCreateItemViewHolder(ViewGroup parent);

    public abstract void onBindHeaderViewHolder(BaseViewHolder holder, int position);

    public abstract void onBindItemViewHolder(BaseViewHolder holder, int position);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BaseViewHolder && ((BaseViewHolder) holder).isHeader()) {

            onBindHeaderViewHolder((BaseViewHolder) holder, position);

        } else if (holder instanceof BaseViewHolder) {
            onBindItemViewHolder((BaseViewHolder) holder, position);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public T getRealItem(int position) {

        if (position == 0) return null;

        if (getItemId(position) > mList.size()) return null;

        return mList.get((int) getItemId(position));
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        private OnItemViewClickListener onItemViewClickListener;
        private OnHeaderViewClickListener onHeaderViewClickListener;

        public void setAdapter(HeaderBaseAdapter adapter) {
            this.adapter = adapter;
        }

        private HeaderBaseAdapter adapter;

        private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemViewClickListener != null) {
                    onItemViewClickListener.onItemViewClick(adapter, view, getAdapterPosition());
                }
            }
        };
        private final View.OnClickListener onHeaderClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHeaderViewClickListener != null) {
                    onHeaderViewClickListener.onHeaderViewClick(adapter, view, getAdapterPosition());
                }
            }
        };

        public boolean isHeader() {
            return isHeader;
        }

        public void setHeader(boolean header) {
            isHeader = header;
        }

        private boolean isHeader;

        public BaseViewHolder(HeaderBaseAdapter adapter, View itemView, boolean isHeader, OnItemViewClickListener onItemViewClickListener) {
            super(itemView);
            this.isHeader = isHeader;
            this.adapter = adapter;
            this.onItemViewClickListener = onItemViewClickListener;
            if (!isHeader)
                itemView.setOnClickListener(onItemClickListener);
        }

        public BaseViewHolder(View itemView,
                              boolean isHeader, OnItemViewClickListener onItemViewClickListener
                , OnHeaderViewClickListener onHeaderViewClickListener) {
            super(itemView);
            this.isHeader = isHeader;
            this.onItemViewClickListener = onItemViewClickListener;
            this.onHeaderViewClickListener = onHeaderViewClickListener;
            if (!isHeader) {
                itemView.setOnClickListener(onItemClickListener);
            } else {
                itemView.setOnClickListener(onHeaderClickListener);
            }
        }


    }

    public interface OnHeaderViewClickListener {
        void onHeaderViewClick(HeaderBaseAdapter adapter, View v, int adapterPosition);
    }

    public interface OnItemViewClickListener {
        void onItemViewClick(HeaderBaseAdapter adapter, View v, int adapterPosition);
    }

}
