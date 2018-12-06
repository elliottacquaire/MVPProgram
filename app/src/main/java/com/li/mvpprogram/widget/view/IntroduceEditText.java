package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * [类功能说明]
 *
 * @author ex-heguogui
 * @version v 1.3.0 2016/12/12 17:19 XLXZ Exp $
 * @email ex-heguoguo@xianglin.cn
 */


public class IntroduceEditText extends EditText {
    public IntroduceEditText(Context context) {
        super(context);
    }

    public IntroduceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IntroduceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return new mInputConnecttion(super.onCreateInputConnection(outAttrs),
                false);
    }


    class mInputConnecttion extends InputConnectionWrapper implements
            InputConnection {

        public mInputConnecttion(InputConnection target, boolean mutable) {
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
            if (!text.toString().matches("[\u4e00-\u9fa5]+") && !text.toString().matches("[a-zA-Z]+") && !text.toString().matches("[0-9]") && !text.toString().matches("[-]") && !text.toString().matches("[_]") && !text.toString().matches("[.]") && !text.toString().matches("[,]") && !text.toString().matches("[，]") && !text.toString().matches("[。]") && !text.toString().matches("[!]") && !text.toString().matches("[！]") && !text.toString().matches("[;]") && !text.toString().matches("[；]") && !text.toString().matches("[?]") && !text.toString().matches("[？]")) {
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

}
