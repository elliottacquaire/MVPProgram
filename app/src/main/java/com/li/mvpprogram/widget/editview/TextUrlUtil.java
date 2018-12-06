package com.li.mvpprogram.widget.editview;

import android.content.Context;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.li.mvpprogram.R;

import java.util.regex.Pattern;

/**
 * 【类功能说明】
 * ...
 * File: TextUrlUtil.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/9/4
 * Changes (from 2018/9/4)
 * -------------------------------------------------------
 * 2018/9/4:创建TextUrlUtil.java(longfeng)
 * -------------------------------------------------------
 */
public class TextUrlUtil {

    public interface OnClickString {
        void OnClick(String s);
    }

    public static void dealContent(Spanned content, TextView textView, int color, OnClickString mOnClickString) {

        textView.setText(getClickableSpan(textView.getContext(), content, color, mOnClickString));
        textView.setMovementMethod(new LinkTouchMovementMethod());
        textView.setFocusable(false);
        textView.setClickable(false);
        textView.setLongClickable(false);
    }


    private static SpannableStringBuilder getClickableSpan(Context c, Spanned content, int color, OnClickString mOnClickString) {

        SpannableStringBuilder spanableInfo = new SpannableStringBuilder(content);

        SpannableString spanableInfo_temp = new SpannableString(content);

        // 网页链接
        String scheme = "http://";
        String regexHttp = "http://[a-zA-Z0-9+&@#/%?=~_\\-|!:,\\.;]*[a-zA-Z0-9+&@#/%=~_|]";
        //Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexHttp), scheme);

        // 网页链接
        String scheme2 = "https://";
        String regexHttps = "https://[a-zA-Z0-9+&@#/%?=~_\\-|!:,\\.;]*[a-zA-Z0-9+&@#/%=~_|]";
        //Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexHttps), scheme2);

        String schemephone = "phone";
        String regexPhone = "\\d{11}";
        //Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexPhone), schemephone);

        String schemeuser = "user";
        String regexUser = "@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}";
        Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexUser), schemeuser);

        String subject = "subject";
        String regexSubject = "#[^#]+#";
        Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexSubject), subject);

        //国内号码
        String schemephone2 = "fixphone";
        String regexFixPhone = "\\d{3}-\\d{8}|\\d{4}-\\d{8}";
        //Linkify.addLinks(spanableInfo_temp, Pattern.compile(regexFixPhone), schemephone2);

        //文本
        String text = "context";
        String regexText = "";
        //Linkify.addLinks(spanableInfo_temp, Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\d{8}"), text);

        URLSpan[] urlSpans = spanableInfo_temp.getSpans(0, spanableInfo_temp.length(),
                URLSpan.class);
        for (URLSpan urlSpan : urlSpans) {
            int start = spanableInfo_temp.getSpanStart(urlSpan);
            int end = spanableInfo_temp.getSpanEnd(urlSpan);

            spanableInfo.setSpan(new Clickable(c, urlSpan.getURL(), color, mOnClickString), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spanableInfo;
    }

}

class Clickable extends ClickableSpan implements View.OnClickListener {
    Context cont;
    String s;

    public int textcolor = 0xffeeeeee;

    private boolean mIsPressed;
    TextUrlUtil.OnClickString mOnClickString;

    public Clickable(Context c, String string, int color, TextUrlUtil.OnClickString mOnClickString) {
        cont = c;
        s = string;
        this.mOnClickString = mOnClickString;
        if (color != -100)
            textcolor = cont.getResources().getColor(color);
        else
            textcolor = cont.getResources().getColor(R.color.colorPrimary);
    }

    public void setPressed(boolean isSelected) {
        mIsPressed = isSelected;
    }

    @Override
    public void onClick(View v) {
        v.setTag("false");
        mOnClickString.OnClick(s);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        // TODO Auto-generated method stub
        tp.setColor(mIsPressed ? cont.getResources().getColor(android.R.color.black) : textcolor);
        tp.bgColor = mIsPressed ? 0xffeeeeee : cont.getResources().getColor(android.R.color.transparent);
        tp.setUnderlineText(false);//设置下划线
        tp.clearShadowLayer();
    }
}

class LinkTouchMovementMethod extends LinkMovementMethod {
    private Clickable mPressedSpan;


    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN
                || event.getActionMasked() == MotionEvent.ACTION_UP
                || event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            mPressedSpan = getPressedSpan(textView, spannable, event);
            if (mPressedSpan != null) {
                if(event.getActionMasked() == MotionEvent.ACTION_UP
                        || event.getActionMasked() == MotionEvent.ACTION_MOVE){
                    mPressedSpan.setPressed(false);
                    mPressedSpan.onClick(textView);
                }else {
                    mPressedSpan.setPressed(true);
                    Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan),
                            spannable.getSpanEnd(mPressedSpan));
                }
                return true;
            }else {
                Selection.removeSelection(spannable);
            }
        }
        return false;
    }

    private Clickable getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        Clickable[] link = spannable.getSpans(off, off, Clickable.class);
        Clickable touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;

    }


}
