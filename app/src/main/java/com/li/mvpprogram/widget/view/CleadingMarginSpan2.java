package com.li.mvpprogram.widget.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/**
 * name patient-android
 * time 2017/7/25.
 * Created by Administrator
 * d escribe describe
 */

public class CleadingMarginSpan2 implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int margin;
    private int lines;

    public CleadingMarginSpan2(int margin, int lines) {
        this.margin = margin;
        this.lines = lines;
    }

    @Override
    public int getLeadingMarginLineCount() {
        return lines;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        if (first){
            return margin;
        }else {
            return 0;
        }

    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {

    }
}
