package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;


public class SearchEditText extends EditText {

    public SearchEditText(Context context) {
        super(context);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
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
        return new mInputConnecttionSearch(inputConnection, false);
    }
}

class mInputConnecttionSearch extends InputConnectionWrapper implements InputConnection {

    public mInputConnecttionSearch(InputConnection target, boolean mutable) {
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
        if (!text.toString().matches("[\u4e00-\u9fa5]+") &&
                !text.toString().matches("[a-zA-Z]+") && !text.toString().matches("[0-9]")
                && !text.toString().matches("[\\\\]")
                && !text.toString().matches("[、\\-=_+<>\\[\\]]")
                && !text.toString().matches("[：:,，《》＜＞。.／/？?;；〝〞＂‘’“”【】［］｛｝{}＼|｜～~！!@＠＃#＄$％%＾^＆&＊*（）()¯―－＝＿＋]")) {
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
