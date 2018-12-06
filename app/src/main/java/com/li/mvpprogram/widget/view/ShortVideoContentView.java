package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;

/**
 */

public class ShortVideoContentView extends android.support.v7.widget.AppCompatTextView {

    public ShortVideoContentView(Context context) {
        super(context);
    }

    public ShortVideoContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getLineCount() > 2) {
            CharSequence charSequence = getText() ;
            int lastCharDown = getLayout().getLineVisibleEnd(1) ;
            if (charSequence.length() > lastCharDown){
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder() ;
                spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown - 3)).append("...") ;
                setText(spannableStringBuilder);
            }
        }
        super.onDraw(canvas);
    }
}
