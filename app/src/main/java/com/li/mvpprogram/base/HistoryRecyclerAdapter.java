package com.li.mvpprogram.base;

import android.content.Context;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.li.mvpprogram.R;
import com.li.mvpprogram.bean.HistoryBean;

public class HistoryRecyclerAdapter extends BaseQuickAdapter<HistoryBean,BaseViewHolder> {

    private Context context;

    public HistoryRecyclerAdapter(Context context) {
        super(R.layout.search_history_item, null);
        this.context = context;

    }


    @Override
    protected void convert(BaseViewHolder helper, HistoryBean item) {
        if (item == null) {
            return;
        }
        //应用名
        if (!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title, item.getTitle());
        }
        helper.addOnClickListener(R.id.linear_histroy);
        helper.addOnClickListener(R.id.img_delete);
    }
}
