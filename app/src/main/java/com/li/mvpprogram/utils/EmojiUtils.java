/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lison on 8/8/16.
 */
public class EmojiUtils {

    public static String replaceStrToEmoji(String input) {
        String temp = input;
        try {

            Pattern pattern = Pattern.compile("\\[u(.*?)\\]");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String str = matcher.group().trim();

                String codeStr = str.replace("[u", "").replace("]", "");

                //
                int code = Integer.parseInt(codeStr, 16);
                ;
//                    //在表情范围内
//                    if (sEmojiMap.containsKey(Integer.valueOf(code))) {
//
//                        char[] chars = Character.toChars(code);
//
//                        String s = new String(chars);
//                        temp = temp.replace(str, s);
//
//                    }

                if (Character.isValidCodePoint(code)) {
                    char[] chars = Character.toChars(code);
                    String s = new String(chars);
                    temp = temp.replace(str, s);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return temp;
    }


    public static String replaceEmojiToStr(String input) {

        if (TextUtils.isEmpty(input)) {
            return "";
        } else {
            String temp = input;
            char[] chars = input.toCharArray();
            boolean codePoint = false;
            int length = chars.length;

            for (int i = 0; i < length; ++i) {
                if (!Character.isHighSurrogate(chars[i])) {
                    int var5;
                    if (Character.isLowSurrogate(chars[i])) {
                        if (i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                            continue;
                        }

                        var5 = Character.toCodePoint(chars[i - 1], chars[i]);

                        String s = new String(Character.toChars(Integer.valueOf(var5)));
                        temp.indexOf(s);
                        temp = temp.replace(s, "[u" + Integer.toHexString(var5) + "]");

                    } else {
                        var5 = chars[i];
                    }

                    //  if (sEmojiMap.containsKey(Integer.valueOf(var5))) {

//                        String s = new String(Character.toChars(Integer.valueOf(var5)));
//                        temp.indexOf(s);
//                        temp = temp.replace(s, "[" + Integer.valueOf(var5) + "]");

                    //  }
                }
            }


            return temp;
        }
    }

    public static CharSequence getCharSequence(String s1) {
        try {
//            return io.rong.imkit.emoticon.AndroidEmoji.ensure(s1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 过滤字符串中的表情
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
//        if (!AndroidEmoji.isEmoji(source)) {
//            return source;// 如果不包含，直接返回
//        }
        String string;
        StringBuilder buf = null;
        char[] chars = source.toCharArray();
        int length = chars.length;
        for(int i = 0; i < length; ++i) {
            if(!Character.isHighSurrogate(chars[i])) {
                char[] es;
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                if(Character.isLowSurrogate(chars[i])) {
                    if(i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                        continue;
                    }
                    es = new char[2];
                    es[0] = chars[i - 1];
                    es[1] = chars[i];
                } else {
                    es = new char[] {chars[i]};
                }
                string = String.valueOf(es);
//                if(!AndroidEmoji.isEmoji(string)) {
//                    buf.append(string);
//                }
            }
        }
        if (buf == null) {
            return "";
        }
        return buf.toString();
    }

    // 判别是否包含Emoji表情
    public static boolean containsEmoji(String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (isEmojiCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

}
