package com.edu.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 10:32 上午
 * @Description:
 */
public class Md5 {

    public final static String md5key = "ssm_edu_system";

    /**
     * MD5方法
     * @param text 明文
     * @param key 密钥
     * @return
     */
    public static String md5(String text, String key) {
        // 加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text+key);
        System.out.println("MD5加密后的字符串为：encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法
     * @param text
     * @param key
     * @param md5
     * @return
     */
    public static boolean verify(String text, String key, String md5) {
        // 根据 传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }



}
