package com.li.mvpprogram.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import com.li.mvpprogram.BaseApplication;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 限制金额输入的格式
 *
 * @author huang yang
 * @version $Id: ChargeTextWatcher.java, v 1.0.0 2015年12月1日 下午4:01:26 oumind Exp
 *          $
 */
public class ChargeTextWatcher implements TextWatcher {
    private int maxLen;
    private int beforeLen;
    private EditText mEditText;
    private boolean isTextChange = false;
    private BigDecimal maxMoney;

    public ChargeTextWatcher(int aMaxLen, EditText aEditText) {
        this.maxLen = aMaxLen;
        this.mEditText = aEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeLen = s.length();
        if (beforeLen >= maxLen)
            return;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() <= maxLen) {
            isTextChange = true;
        } else {
            isTextChange = false;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == null) {
            return;
        }
        String value = s.toString().trim();
        if (isTextChange) {
            if (StringUtils.isEmpty(value)) {
                value = "0";
            }
            if (value.startsWith("0") && value.length() == 2) {
                String next = value.substring(1, 2);
                if (!next.equals(".")) {
                    value = next;
                }
            }
            if (!value.contains(".") && value.length() > maxLen - 3) {
                value = value.substring(0, value.length() - 1);
            }
            Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
            Matcher match = pattern.matcher(value);
            if (!match.matches()) {
                value = value.substring(0, value.length() - 1);
            }
            BigDecimal inputMoney = new BigDecimal(value);
            if (maxMoney != null && inputMoney.compareTo(maxMoney) == 1) {
                value = AmountUtils.parseMoney("##0.00", maxMoney);
                ToastUtils.show(BaseApplication.getInstance(), "还款金额不能超过应还金额");
            }
        } else {
            value = value.substring(0, value.length() - 1);
        }
        setText(mEditText, value);
    }

    private void setText(EditText editText, String value) {
        editText.removeTextChangedListener(this);
        editText.setText(value);
        editText.addTextChangedListener(this);
        Selection.setSelection(mEditText.getText(), value.length());
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        if (maxMoney.compareTo(new BigDecimal(0)) == -1) {
            return;
        }
        this.maxMoney = maxMoney;
    }
}
