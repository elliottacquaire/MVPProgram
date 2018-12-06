/**/

package com.li.mvpprogram.config;

/**
 * 程序发布 各开关控制接口类
 */
public interface ReleaseSwitch {


    /**
     * 在debug 情况下的环境
     * <p/>
     * test：
     * ENVController.ENV_DEV 开发
     * ENVController.ENV_TEST 测试
     * ENVController.ENV_TEST_2 测试2
     * ENVController.ENV_LT 联调
     * ENVController.ENV_LOCAL 本地服务联调
     * <p/>
     * product:
     * ENVController.ENV_PP_PRODUCT
     * ENVController.ENV_PRODUCT
     */

    String XL_ENV_DEBUG_VALUE = ENVController.ENV_DEV;


    /**
     * 在debug 情况下的db 版本号
     */
    int DB_DEBUG_VER = 1;


    /**
     * 在debug 情况下的渠道名
     */
    String UM_CHANNEL_NAME = "XiangLinXiaoZhan";
    /**
     * 在release情况下默认渠道名
     */
    String UM_CHANNEL_NAME_RELEASE = "xianglin_official";
}