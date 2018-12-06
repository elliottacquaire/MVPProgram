package com.li.mvpprogram.config;

/**
 * 【类功能说明】
 * ...--
 */
public class AppConstant {
    public static enum NativeActivity {
        ACTIVE_ZHONGBANG("native:ACTIVE_ZHONGBANG", "cvcx宝");

        public String code;
        String desc;

        private NativeActivity(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
