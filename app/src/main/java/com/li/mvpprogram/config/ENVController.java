/**/

package com.li.mvpprogram.config;

import android.content.Context;
import com.li.mvpprogram.utils.NativeEncrypt;

/**
 * URL控制
 */
public class ENVController {

    /**
     * 本地
     */
    public static final String ENV_LOCAL = "ENV_LOCAL";
    /**
     * 开发
     */
    public static final String ENV_DEV = "ENV_DEV";
    /**
     * 测试
     */
    public static final String ENV_TEST = "ENV_TEST";
    /**
     * 测试2
     */
    public static final String ENV_TEST_2 = "ENV_TEST_2";
    /**
     * 联调
     */
    public static final String ENV_LT = "ENV_LT";
    /**
     * 生产
     */
    public static final String ENV_PRODUCT = "ENV_PRODUCT";
    /**
     * 预生产
     */
    public static final String ENV_PP_PRODUCT = "ENV_PP_PRODUCT";

    /**
     * 接口服务URL地址
     */
    public static String URL = "";

    /**
     * 链接服务URL地址
     */
    public static String H5_URL = "";

    /**
     * 链接账户信息服务URL地址
     */
    public static String H5CAU_URL = "";

    /**
     * 文件服务器URL地址
     */
    public static String FILE_URL = "";


    /**
     * 二维码MD5加密key
     */
    public static String MMGWSECRET_FOR_MD5 = "";

    /**
     * 二维码AES加密key
     */
    public static String MMGWSECRET_FOR_AES = "";


    public static String ENV = "";

    /**
     * @param context
     * @param env
     */
    public static void initEnv(Context context, String env) {
        ENV = env;
        MMGWSECRET_FOR_MD5 = NativeEncrypt.nativeEncryptInstance(context).getRsaByKey("MMGWSECRET_FOR_MD5_KEY");
        MMGWSECRET_FOR_AES = NativeEncrypt.nativeEncryptInstance(context).getRsaByKey("MMGWSECRET_FOR_AES_KEY");
        if (ENV_PRODUCT.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_P_URL"); // 生产

            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_P_H5_URL");

            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_P_H5CAU_URL");

            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_P_FILE_URL");

        } else if (ENV_PP_PRODUCT.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_PP_URL"); // 预生产
            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_PP_H5_URL");
            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_PP_H5CAU_URL");

            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_PP_FILE_URL");

        } else if (ENV_LOCAL.equals(env)) {
            URL = "http://127.0.0.1:8080/ggw/mgw.htm";// 本地


        } else if (ENV_DEV.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_DEV_URL"); // 开发

            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_DEV_H5_URL");

            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_DEV_H5CAU_URL");

            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_DEV_FILE_URL");
        } else if (ENV_TEST.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_URL"); // 测试

            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_H5_URL");

            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_H5CAU_URL");

            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_FILE_URL");

        } else if (ENV_TEST_2.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_2_URL"); // 测试2
            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_2_H5_URL");

            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_2_H5CAU_URL");
            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_TEST_2_FILE_URL");

        } else if (ENV_LT.equals(env)) {
            URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_LT_URL"); // 联调
            H5_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_LT_H5_URL");

            H5CAU_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_LT_H5CAU_URL");

            FILE_URL = NativeEncrypt.nativeEncryptInstance(context).getUrlByKey(
                    "ENV_LT_FILE_URL");

        }

    }

}
