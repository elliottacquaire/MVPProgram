package com.li.mvpprogram.utils;

import android.widget.EditText;
import android.widget.TextView;

/**
 * 【类功能说明】
 * 实名认证模块工具类
 * File: RealMessageUtils.java
 * @author : longfeng
 * Vesion: 3.2.0
 * Create: 2018/1/22
 * Changes (from 2018/1/22)
 * -------------------------------------------------------
 * 2018/1/22:创建RealMessageUtils.java(longfeng)
 * -------------------------------------------------------
 */

public class RealMessageUtils {
    /**
     * 信息回显
     *
     * @param str1 枚举类型
     * @param tv   textView
     * @param str2 回显文本
     */
    public static void messageEchoed(String str1, TextView tv, String str2) {
        if (!StringUtils.isEmpty(str1)) {
            tv.setText(str2);
        }
    }

    /**
     * @param str1 枚举类型
     * @param et   EditText
     * @param str2 回显文本
     */
    public static void messageEchoed(String str1, EditText et, String str2) {
        if (!StringUtils.isEmpty(str1)) {
            et.setText(str2);
        }
    }

    /**
     * 根据code查询desc
     *
     * @param str1 枚举类型
     * @param str2 数据code
     * @return desc
     */
//    public static String queryDesc(Map<String, List<ConstantDTO>> mData, String str1, String str2) {
//        String desc = "";
//        List<ConstantDTO> constantList = queryConstantDtoList(mData, str1);
//        for (ConstantDTO dto : constantList) {
//            if (dto.getCode().equals(str2)) {
//                desc = dto.getDesc();
//                break;
//            }
//        }
//        return desc;
//    }

    /**
     * 根据desc查询code
     *
     * @param str1 枚举类型
     * @param str2 数据desc
     * @return code
     */
//    public static String queryCode(Map<String, List<ConstantDTO>> mData, String str1, String str2) {
//        String code = "";
//        List<ConstantDTO> constantList = queryConstantDtoList(mData, str1);
//        for (ConstantDTO dto : constantList) {
//            if (dto.getDesc().equals(str2)) {
//                code = dto.getCode();
//                break;
//            }
//        }
//        return code;
//    }

    /**
     * 根据枚举类型获取对象集合
     *
     * @param str1 枚举类型
     * @return constantList
     */
//    public static List<ConstantDTO> queryConstantDtoList(Map<String, List<ConstantDTO>> mData, String str1) {
//        List<ConstantDTO> constantList = new ArrayList<>();
//        if (mData != null) {
//            for (Map.Entry<String, List<ConstantDTO>> entry : mData.entrySet()) {
//                if (entry.getKey().equals(str1)) {
//                    constantList = entry.getValue();
//                    break;
//                }
//            }
//        }
//        return constantList;
//    }
}
