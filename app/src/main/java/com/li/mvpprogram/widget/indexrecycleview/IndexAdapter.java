/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.indexrecycleview;

/**
 * 数据源接口
 *
 * @author lary.huang
 * @version v 1.0.0 2017/1/5 下午4:01 XLXZ Exp $
 * @email huangyang@xianglin.cn
 */
public interface IndexAdapter {
    Indexable getItem(int position);
}
