package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.li.mvpprogram.utils.imageloader.ImageLoader;


/**
 * [类功能说明]
 * 自定义图片
 *
 * @author ex-heguogui
 * @version v 2.0.0 2017/2/15 16:59 XLXZ Exp $
 * @email ex-heguoguo@xianglin.cn
 */


public class CustomImageView extends AppCompatImageView {
    private String url;
    private boolean isAttachedToWindow;
    private Context mContext;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomImageView(Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    public void onAttachedToWindow() {
        isAttachedToWindow = true;
        if (!TextUtils.isEmpty(url)) {
            setImageUrl(url, this);
        }
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        isAttachedToWindow = false;
        setImageBitmap(null);
        super.onDetachedFromWindow();
    }

    public void setImageUrl(String url, CustomImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
            if (isAttachedToWindow) {
                ImageLoader.getInstance().displayImage(mContext, url, imageView);
            }
//            ImageLoader.getInstance().displayImage(mContext,url,imageView);
        } else {
//            ImageLoader.getInstance().displayImage(mContext, R.drawable.mine_default_head_icon, imageView);
        }
    }
}
