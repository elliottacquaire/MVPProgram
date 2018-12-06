package com.li.mvpprogram.config;

/**
 * 【类功能说明】
 * App接口常量
 * File: AppContants.java
 */
public interface AppContants {
    /**
     * HTML/原生/RN 跳转类型
     */
    String ACTIVE_TYPE_HTML = "HTML";
    String ACTIVE_TYPE_ACTIVE = "ACTIVE";
    String ACTIVE_TYPE_RN = "RN";
    /**
     * 步步生金活动类型
     */
    String STEP_GOLD = "STEP_GOLD";
    String ACTIVE_XL_LOAN = "ACTIVE_XL_LOAN";

    //游戏链接正则表达式
    String GAME_URL_REGEX = "^http(s)?://game(-)?[a-zA-Z0-9]{0,}.xianglin.cn(/.*)+$";
    String URL_ADDRES = "http";

    /**
     * 短视频
     */
    String SHORT_VIDEO = "SHORT_VIDEO";

    //配置参数接口类型
    String TRUE = "true";
    String FALSE = "false";
}
