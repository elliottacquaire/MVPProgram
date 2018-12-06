package com.li.mvpprogram.utils.video;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 短视频话题副文本点击效果、点击事件冲突
 * Created by xuzhou on 2018/8/30.
 */
public class ShortVideoLinkMovementMethod extends LinkMovementMethod {

    private ClickableSpan mPressedSpan;

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mPressedSpan = getPressedSpan(textView, spannable, event);
            /*
            if (null != mPressedSpan) {
                Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan),
                        spannable.getSpanEnd(mPressedSpan));
            }
            */
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            ClickableSpan touchedSpan = getPressedSpan(textView, spannable, event);
            if (null != mPressedSpan && touchedSpan != mPressedSpan) {
                mPressedSpan = null;
               // Selection.removeSelection(spannable);
            }
        } else {
            if (null != mPressedSpan) {
                mPressedSpan.onClick(textView);
            } else {
                super.onTouchEvent(textView, spannable, event);
            }
            mPressedSpan = null;
            //Selection.removeSelection(spannable);
        }
        return null != mPressedSpan;
    }

    /**
     * Copy from:
     *  http://stackoverflow.com/questions/20856105/change-the-text-color-of-a-single-clickablespan-when-pressed-without-affecting-o
     * By:
     *  Steven Meliopoulos
     */
    private ClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpan[] link = spannable.getSpans(off, off, ClickableSpan.class);
        ClickableSpan touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }

    public boolean isPressedSpan() {
        return null != mPressedSpan;
    }

}