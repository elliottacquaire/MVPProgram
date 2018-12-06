package com.li.mvpprogram.widget.editview;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.li.mvpprogram.R;
import org.xml.sax.XMLReader;

/**
 * 【类功能说明】
 * ...
 * File: TopicTagHandler.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/9/11
 * Changes (from 2018/9/11)
 * -------------------------------------------------------
 * 2018/9/11:创建TopicTagHandler.java(longfeng)
 * -------------------------------------------------------
 */
public class TopicTagHandler implements Html.TagHandler {
    private Context mContext;
    private int startIndex = 0;
    private int stopIndex = 0;

    public TopicTagHandler(Context context, OnItemClick onItemClick){
        mContext = context;
        mOnItemClick = onItemClick;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.toLowerCase().equals("span")) {
            if (opening) {
                startTopic(mContext, tag, output, xmlReader);
            } else {
                endTopic(mContext, tag, output, xmlReader);
            }
        }
    }

    public void startTopic(Context context, String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    public void endTopic(Context context, String tag, Editable output, XMLReader xmlReader) {
        stopIndex = output.length();
        CharSequence s = output.subSequence(startIndex, stopIndex);
        output.setSpan(new TopicSpan(context, s.toString()), startIndex, stopIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private class TopicSpan extends ClickableSpan implements View.OnClickListener {
        Context cont;
        String mString;

        public TopicSpan(Context context, String str){
            cont = context;
            mString = str;
        }

        @Override
        public void onClick(View v) {
            v.setTag("false");
            // 跳转某页面
            if (mOnItemClick != null){
                mOnItemClick.onItemClick(v, mString);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(cont.getResources().getColor(R.color.textColorPrimary));
            //设置下划线
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    }

    private OnItemClick mOnItemClick;
    public interface OnItemClick {
        /**
         * 自定义点击事件
         *
         * @param view
         */
        void onItemClick(View view, String str);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }
}
