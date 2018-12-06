package com.li.mvpprogram.utils;

import android.text.method.ReplacementTransformationMethod;

/**
 * 【类功能说明】
 */

public class AllCapTransformationMethod extends ReplacementTransformationMethod {
    @Override
    protected char[] getOriginal() {
        char[] xx = {'x'};
        return xx;
    }

    @Override
    protected char[] getReplacement() {
        char[] XX = {'X'};
        return XX;
    }
}
