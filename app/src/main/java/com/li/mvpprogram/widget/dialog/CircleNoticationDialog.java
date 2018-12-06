package com.li.mvpprogram.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.li.mvpprogram.R;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.utils.PreferencesUtils;

/**
 * [类功能说明]
 * 广场引导
 */


public class CircleNoticationDialog extends Dialog {

    private Context mContext;
    private OnRightViewClickListener clickListener;

    public CircleNoticationDialog(Context context, OnRightViewClickListener onClickListener) {
        super(context, R.style.common_alert_dialog);
        this.mContext = context;
        this.clickListener = onClickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_circle_nocation_layout);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        Button mKonw = (Button) findViewById(R.id.circle_notication_know_btn);
        mKonw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.putBoolean(Constants.CIRCLE_GUIDE_PREFERENCE, mContext, Constants.CIRCLE_NOTICATION, true);
                dismiss();
            }
        });

        ImageView right_iv = (ImageView) findViewById(R.id.notication_right_iv);
        right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtils.putBoolean(Constants.CIRCLE_GUIDE_PREFERENCE, mContext, Constants.CIRCLE_NOTICATION, true);
                clickListener.callBack(true);
                dismiss();
            }
        });


    }


    public interface OnRightViewClickListener {
        public void callBack(boolean state);
    }

}
