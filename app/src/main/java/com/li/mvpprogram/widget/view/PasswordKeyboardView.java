package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.R;
import com.li.mvpprogram.utils.ToastUtils;

import java.util.List;

/**
 * 密码键盘
 *
 * @author lary.huang
 * @version v 1.4.8 2017/11/20 XLXZ Exp $
 * @email huangyang@xianglin.cn
 */
public class PasswordKeyboardView extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    private static final int KEYCODE_CLEAR = -3;
    private static final int KEYCODE_DELETE = -5;
    private int mDeleteWidth;
    private int mDeleteHeight;
    private int mDeleteBackgroundColor;
    private Drawable mDeleteDrawable;
    private Rect mDeleteRect;
    private String clearText = "清空";
    private Paint mPaint;
    private int mCurrentPwdLen = 0;
    //是否开启密码长度校验
    private boolean mCheckEnable;
    //密码长度
    private int mPasswordLength = 6;
    //操作对象
    private EditText mTarget;

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int i) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.PasswordKeyboardStyle);
        mDeleteWidth = t.getDimensionPixelOffset(R.styleable.PasswordKeyboardStyle_delete_width, -1);
        mDeleteHeight = t.getDimensionPixelOffset(R.styleable.PasswordKeyboardStyle_delete_height, -1);
        mDeleteDrawable = t.getDrawable(R.styleable.PasswordKeyboardStyle_delete_drawable);
        mDeleteBackgroundColor = t.getColor(R.styleable.PasswordKeyboardStyle_delete_backgroudcolor, Color.TRANSPARENT);
        t.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#333333"));
        mPaint.setTextSize(50);

        //默认开启密码长度检验
        mCheckEnable = true;
        //密码长度默认6位
        mPasswordLength = 6;

        initKeyboard(context);
    }

    private void initKeyboard(Context context) {
        Keyboard keyboard = new Keyboard(context, R.xml.keyboard_number);
        setKeyboard(keyboard);
        setEnabled(true);
        //设置按键没有点击放大镜显示效果
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            //重画按键布局
            if (key.codes[0] == KEYCODE_CLEAR) {
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
                drawClearText(key, canvas);
            } else if (key.codes[0] == KEYCODE_DELETE) {
                //如果是右下角的删除按键，重画按键的背景，并且绘制删除图标
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
                drawDeleteButton(key, canvas);
            }
        }
    }

    //绘制左下角文字
    private void drawClearText(Keyboard.Key key, Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        float textBaseY = key.y + key.height - (key.height - fontHeight) / 2 - fontMetrics.bottom;

        float fontWidth = mPaint.measureText(clearText);
        float textBaseX = (key.x + key.width / 2) - fontWidth / 2;

        canvas.drawText(clearText, textBaseX, textBaseY, mPaint);
    }

    //绘制按键背景
    private void drawKeyBackground(Keyboard.Key key, Canvas canvas, int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    //绘制删除按键
    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        if (mDeleteDrawable == null) {
            return;
        }
        //计算删除图标绘制的坐标
        if (mDeleteRect == null || mDeleteRect.isEmpty()) {
            int drawWidth, drawHeight;
            int intrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = mDeleteDrawable.getIntrinsicHeight();
            if (mDeleteWidth > 0 && mDeleteHeight > 0) {
                drawWidth = intrinsicWidth;
                drawHeight = intrinsicHeight;
            } else if (mDeleteWidth > 0 && mDeleteHeight <= 0) {
                drawWidth = intrinsicWidth;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            } else if (mDeleteWidth <= 0 && mDeleteHeight > 0) {
                drawHeight = intrinsicHeight;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            } else {
                drawWidth = intrinsicWidth;
                drawHeight = intrinsicHeight;
            }

            //限制图标的大小，防止图标超出按键
            if (drawWidth > key.width) {
                drawWidth = key.width;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            }
            if (drawHeight > key.height) {
                drawHeight = key.height;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            }
            //获取删除图标绘制的坐标
            int left = key.x + (key.width - drawWidth) / 2;
            int top = key.y + (key.height - drawHeight) / 2;
            mDeleteRect = new Rect(left, top, left + drawWidth, top + drawHeight);
        }
        //绘制删除图标
        if (mDeleteRect != null && !mDeleteRect.isEmpty()) {
            mDeleteDrawable.setBounds(mDeleteRect.left, mDeleteRect.top, mDeleteRect.right, mDeleteRect.bottom);
            mDeleteDrawable.draw(canvas);
        }
    }

    /**
     * called when the user presses a key
     *
     * @param primaryCode
     */
    @Override
    public void onPress(int primaryCode) {

    }

    /**
     * called when the user releases a key
     *
     * @param primaryCode
     */
    @Override
    public void onRelease(int primaryCode) {

    }

    /**
     * send a key press to the listener
     *
     * @param primaryCode
     * @param keyCodes
     */
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if (primaryCode == KEYCODE_CLEAR) {
            //清空数据
            if (mTarget == null) return;
            mTarget.setText("");
            mCurrentPwdLen = 0;
        } else if (primaryCode == KEYCODE_DELETE) {
            //删除数据
            if (mTarget == null) return;
            if (mCurrentPwdLen > mPasswordLength) {
                mCurrentPwdLen = mPasswordLength;
            }
            String value = mTarget.getText().toString();
            if (!TextUtils.isEmpty(value)) {
                value = value.substring(0, value.length() - 1);
                mTarget.setText(value);
                mTarget.setSelection(value.length());
                mCurrentPwdLen = mCurrentPwdLen > 0 ? mCurrentPwdLen - 1 : 0;
            } else {
                mCurrentPwdLen = 0;
            }
        } else {
            //点击数字
            //只有在关闭长度校验或者当前密码长度比设定长度小时会执行
            if (mTarget == null) return;
            if (!mCheckEnable || mCurrentPwdLen < mPasswordLength) {
                String key = Character.toString((char) primaryCode);
                String value = mTarget.getText().toString();
                value += key;
                mTarget.setText(value);
                //设置光标位置
                mTarget.setSelection(value.length());
            } else {
                //校验不通过
                ToastUtils.show(BaseApplication.getInstance(), "密码长度超限了....");
            }
            mCurrentPwdLen++;
        }
        //密码达到指定长度后回调此方法
        if (mCheckEnable && mCurrentPwdLen == mPasswordLength && mOnKeyboardEventListener != null) {
            mOnKeyboardEventListener.onInputPasswordComplete(mCurrentPwdLen, mTarget.getText().toString());
        }
    }

    /**
     * sends a sequence of characters to the listener
     *
     * @param text
     */
    @Override
    public void onText(CharSequence text) {

    }

    /**
     * called when the user quickly moves the finger from right to left
     */
    @Override
    public void swipeLeft() {

    }

    /**
     * called when the user quickly moves the finger from left to right
     */
    @Override
    public void swipeRight() {

    }

    /**
     * called when the user quickly moves the finger from up to down
     */
    @Override
    public void swipeDown() {

    }

    /**
     * called when the user quickly moves the finger from down to up
     */
    @Override
    public void swipeUp() {

    }

    /**
     * 设置密码长度
     *
     * @param len
     */
    public void setPasswordLength(int len) {
        if (len > 0) {
            this.mPasswordLength = len;
        }
    }

    /**
     * 是否开启密码长度校验
     *
     * @param enable
     */
    public void setCheckLenEnable(boolean enable) {
        this.mCheckEnable = enable;
    }

    /**
     * reset
     */
    public void clearKeybaord() {
        this.mCurrentPwdLen = 0;
    }

    /**
     * 设置target
     *
     * @param editText
     */
    public void setTarget(EditText editText) {
        this.mTarget = editText;
    }

    /**
     * 键盘点击事件监听
     */
    private OnKeyboardEventListener mOnKeyboardEventListener;

    /**
     * set listener
     *
     * @param eventListener
     */
    public void setOnKeyboardEventListener(OnKeyboardEventListener eventListener) {
        this.mOnKeyboardEventListener = eventListener;
    }

    /**
     * 键盘点击事件监听
     */
    public interface OnKeyboardEventListener {
        /**
         * 密码达到指定长度时回调
         *
         * @param pwdLength
         */
        void onInputPasswordComplete(int pwdLength, String value);
    }
}
