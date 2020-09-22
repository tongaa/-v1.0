package com.mengxugu2.demo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

/**
 * @program: demo
 * @description: 生成随机盐值
 * @author: tgw
 * @create: 2020-09-21 21:17
 */
public class SaltUtils {
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk1mnopgrstuvwxyz01234567890!@#$&*".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash("1234",salt,1024);
        System.out.println(salt);
        System.out.println(md5Hash.toHex());
    }
}

