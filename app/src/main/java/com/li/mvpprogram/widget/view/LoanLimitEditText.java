package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import com.li.mvpprogram.utils.RegularUtils;


/**
 * 【类功能说明】
 * ...
 * File: LoanLimitEditText.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/3/6
 * Changes (from 2018/3/6)
 * -------------------------------------------------------
 * 2018/3/6:创建LoanLimitEditText.java(longfeng)
 * -------------------------------------------------------
 */

public class LoanLimitEditText extends EditText {
    public LoanLimitEditText(Context context) {
        super(context);
    }

    public LoanLimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoanLimitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 输入法
     *
     * @param outAttrs
     * @return
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        //避免空指针
        InputConnection inputConnection = super.onCreateInputConnection(outAttrs);
        if (inputConnection == null) {
            return null;
        }
        return new mlInputConnecttion(inputConnection,
                false);
    }
}

class mlInputConnecttion extends InputConnectionWrapper implements
        InputConnection {

    public mlInputConnecttion(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    /**
     * 对输入的内容进行拦截
     *
     * @param text
     * @param newCursorPosition
     * @return
     */
    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        // 只能输入汉字 英文 数字 -_
        if (!RegularUtils.isLoanEdit(text.toString())) {
            return false;
        }
        return super.commitText(text, newCursorPosition);
    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        return super.sendKeyEvent(event);
    }

    @Override
    public boolean setSelection(int start, int end) {
        return super.setSelection(start, end);
    }
}
