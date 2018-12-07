package com.li.mvpprogram.widget.allrecycleview.inter;

import android.content.Context;
import com.li.mvpprogram.widget.allrecycleview.holder.CustomHolder;

import java.util.List;


public interface BodyInitListener<T> {

    public CustomHolder getHolderByViewType(Context context, List<T> lists, int itemID);
}
