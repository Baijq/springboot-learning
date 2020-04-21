package com.biubiu.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncryptUtil
 *
 * @author baijq
 */
public class EncryptUtil {

    private static final String DEFAULT_SALT = "JA.#{-5H8-}#.VA//avaJ";

    /**
     * MD5加密
     *
     * @param inputText 要加密的字符串
     * @return 密文
     */
    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }

    /**
     * MD5加密
     *
     * @param inputText 要加密的字符串
     * @param salt      盐
     * @return 密文
     */
    public static String md5(String inputText, String salt) {
        salt = salt.length() == 0 ? DEFAULT_SALT : salt;
        return encrypt(inputText + salt, "md5");
    }

    /**
     * SHA加密
     *
     * @param inputText 要加密的字符串
     * @return 密文
     */
    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return 加密后的字符串
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }

        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte[] s = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回十六进制字符串
     *
     * @param arr 入参
     * @return String
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

}
