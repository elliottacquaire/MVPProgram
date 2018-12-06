package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class LoanLimitFloatEditText extends EditText {
    public LoanLimitFloatEditText(Context context) {
        super(context);
    }

    public LoanLimitFloatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoanLimitFloatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.toString().contains(".")) {
            if (text.length() - 1 - text.toString().indexOf(".") > 2) {
                text = text.toString().subSequence(0,
                        text.toString().indexOf(".") + 3);
                setText(text);
                setSelection(text.length());
            }
        }
        if (text.toString().trim().substring(0).equals(".")) {
            text = "0" + text;
            setText(text);
            setSelection(2);
        }

        if (text.toString().startsWith("0")
                && text.toString().trim().length() > 1) {
            if (!text.toString().substring(1, 2).equals(".")) {
                setText(text.subSequence(0, 1));
                setSelection(1);
                return;
            }
        }

    }
}
