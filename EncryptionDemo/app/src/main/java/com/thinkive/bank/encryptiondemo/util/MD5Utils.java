package com.thinkive.bank.encryptiondemo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: sq
 * @date: 2017/8/8
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: MD5加密工具类(32位小)
 * MD5一般可以用在密码加密处理上,数字签名也是MD5的另一个使用场景
 * 不可逆，不可以由消息摘要推出数据正文
 */
public class MD5Utils {
    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
            "b", "c", "d", "e", "f"};

    public MD5Utils() {
    }

    /**
     * 使用MD5对字符串进行加密工作，并返回加密后的字符串
     *
     * @param originalString
     * @return
     */
    public static String GetMD5Code(String originalString) {
        String resultString = null;
        try {
            resultString = new String(originalString);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(originalString.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 使用MD5对字符串进行加密工作，并返回加密后的字符串
     *
     * @param originalString
     * @return
     */
    public static String md5Encode(String originalString) {
        try {
            //创建消息摘要实例， MD5表示生成消息再要的算法名称
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            //通过消息摘要将原字符串生成字节数组数组
            byte[] digest = messageDigest.digest(originalString.getBytes());

            //对字节数组进行遍历，并将每一个字节以16进制的方式添加到StringBuffer中
            StringBuffer sf = new StringBuffer();

            for (byte b : digest) {
                sf.append(String.format("%02x", b));
            }

            return sf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

}
