/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密相关操作
 * Created by lison on 8/8/16.
 */
public class EncryptUtils {

    private EncryptUtils() {
        throw new UnsupportedOperationException("you can't create EncryptUtils object");
    }


    /**
     * MD5加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String getMD5(String data) {
        return TextUtils.isEmpty(data) ? "" : getMD5(data.getBytes());
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文
     */
    public static String getMD5(byte[] data) {
        if (data.length > 0) {
            return bytes2Hex(encryptMD5(data));
        }
        return "";
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(byte[] data) {
        if (data.length > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(data);
                return md.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static String getMD5File(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            FileInputStream in = null;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                in = new FileInputStream(filePath);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) != -1) {
                    md.update(buffer, 0, len);
                }
                return bytes2Hex(md.digest());
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
        return "";
    }


    /**
     * SHA加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String getSHA(String data) {
        return TextUtils.isEmpty(data) ? "" : getSHA(data.getBytes());
    }

    /**
     * SHA加密
     *
     * @param data 明文字节数组
     * @return 密文
     */
    public static String getSHA(byte[] data) {
        if (data.length > 0) {
            return bytes2Hex(encryptSHA(data));
        }
        return "";
    }

    /**
     * SHA加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA(byte[] data) {
        if (data.length > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(data);
                return md.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 一个byte转为2个hex字符
     */
    public static String bytes2Hex(byte[] src) {
        char[] res = new char[src.length * 2];
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }
        return new String(res);
    }


    /**
     * 16进制字符串转换为byte数组
     *
     * @param hexString
     * @return
     */
    private static byte[] hexString2Bytes(String hexString) {
        if (isSpace(hexString)) return null;
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Dec(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String sha512(String source) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        /*
        * 1.sha512加密后得到16进制字符串
        * 2.将16进制字符串转换为byte[]
        * 3.将byte[]进行base64加密，得到字符串结果
         */
        return base64Encode2String(hexString2Bytes(encryptSHA512ToString(source)));
    }


    public static String md5(Object object) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(toByteArray(object));
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException("Huh, MD5 should be supported?", var7);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        byte[] arr$ = hash;
        int len$ = hash.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            if ((b & 255) < 16) {
                hex.append("0");
            }

            hex.append(Integer.toHexString(b & 255));
        }

        return hex.toString();
    }

    private static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream ex = new ObjectOutputStream(bos);
            ex.writeObject(obj);
            ex.flush();
            bytes = bos.toByteArray();
            ex.close();
            bos.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return bytes;
    }


    /**
     * Base64编码
     *
     * @param input 要编码的字节数组
     * @return Base64编码后的字符串
     */
    public static String base64Encode2String(final byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }


    /**
     * SHA512加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(final String data) {
        return encryptSHA512ToString(data.getBytes());
    }


    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(final byte[] data) {
        return bytes2HexString(encryptSHA512(data));
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA512(final byte[] data) {
        return hashTemplate(data, "SHA512");
    }


    /**
     * hash加密模板
     *
     * @param data      数据
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
