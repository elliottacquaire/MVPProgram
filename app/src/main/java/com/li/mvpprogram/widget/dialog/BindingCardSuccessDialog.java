/*
 * 乡邻小站
 *  Copyright (c) 2017 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.li.mvpprogram.R;

/**
 * [绑卡成功提示框]
 *

 */
public class BindingCardSuccessDialog extends Dialog implements View.OnClickListener {

    private String mContent;
    protected TextView titleTv;
    private int mGravity = Gravity.CENTER;

    /**
     * @param context
     * @param content        提示语
     * @param contentGravity
     */
    public BindingCardSuccessDialog(Context context, String content,
                                    int contentGravity) {
        super(context, R.style.common_alert_dialog);
        this.mContent = content;
        mGravity = contentGravity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_card_success_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
        titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText(mContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
