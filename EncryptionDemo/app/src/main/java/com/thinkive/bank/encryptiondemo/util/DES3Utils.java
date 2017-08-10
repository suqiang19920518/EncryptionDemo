package com.thinkive.bank.encryptiondemo.util;


import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 3DES加解密工具类
 * 对称加密，对应的密钥长度为24位
 */
public class DES3Utils {

    /*
    * 加密用的secretKey 可以用26个字母、特殊符号和数字组成，secretKey需要大于等于24位。
    */
    private final static String secretKey = "tailongct@lx100$#365#$3464";// key,可自行修改
    private final static String iv = "012r4a67";//偏移量,可自行修改，必须为8位
    private final static String encoding = "UTF-8";// 加解密统一使用的编码方式

    /**
     * 3DES加密
     *
     * @param originalString 需要进行加密的字符串
     * @param secretKey      密钥
     * @param vector         偏移量
     * @return
     */
    public static String encode(String originalString, String secretKey, String vector) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            /*=============使用CBC模式=============*/
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);

            /*=============使用非CBC模式=============*/
//            Cipher cipher = Cipher.getInstance("desede");
//            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] encryptData = cipher.doFinal(originalString.getBytes(encoding));
            return Base64Utils.encode(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES加密
     *
     * @param originalString 需要进行加密的字符串
     * @return
     */
    public static String encode(String originalString) {
        return encode(originalString, secretKey, iv);
    }

    /**
     * 3DES加密
     *
     * @param originalString 需要进行加密的字符串
     * @param secretKey      密钥
     * @param vector         偏移量
     * @return
     */
    public static String encode1(String originalString, String secretKey, String vector) {
        try {
            //通过密钥字符串获字节数组对象
            byte[] keyBytes = secretKey.getBytes();

            //构造24位的空字节数组
            byte[] temp = new byte[24];  //3des加密使用的是24位密钥

            //将密钥中的前24位字节拷贝到temp中
            System.arraycopy(keyBytes, 0, temp, 0, Math.min(keyBytes.length, temp.length));

            /**
             * 以下获取密钥过程
             * 1 密钥
             * 2 机密算法的名称
             */
            Key deskey = new SecretKeySpec(temp, "desede");

             /*=============使用CBC模式=============*/

            //获取一个密码生成器, 参数表示生成密码的算法名称
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");

            //初始化密码生成器
            IvParameterSpec ips = new IvParameterSpec(vector.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);

            /*=============使用非CBC模式=============*/

            //获取一个密码生成器, 参数表示生成密码的算法名称
//            Cipher cipher = Cipher.getInstance("desede");

            //初始化密码生成器
//            cipher.init(Cipher.ENCRYPT_MODE, deskey);

            byte[] bytes = cipher.doFinal(originalString.getBytes(encoding));

            //因为对称加密之后获取的是乱码格式字符串，需要使用Base64同一编码
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES加密
     *
     * @param originalString 需要进行加密的字符串
     * @return
     */
    public static String encode1(String originalString) {
        return encode1(originalString, secretKey, iv);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @param secretKey   密钥
     * @param vector      偏移量
     * @return
     */
    public static String decode(String encryptText, String secretKey, String vector) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

             /*=============使用CBC模式=============*/
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            /*=============使用非CBC模式=============*/
//            Cipher cipher = Cipher.getInstance("desede");
//            cipher.init(Cipher.DECRYPT_MODE, deskey);
            byte[] decryptData = cipher.doFinal(Base64Utils.decode(encryptText));
            return new String(decryptData, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     */
    public static String decode(String encryptText) {
        return decode(encryptText, secretKey, iv);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @param secretKey   密钥
     * @param vector      偏移量
     * @return
     */
    public static String decode1(String encryptText, String secretKey, String vector) {
        try {
            //通过密钥字符串获字节数组对象
            byte[] keyBytes = secretKey.getBytes();

            //构造24位的空字节数组
            byte[] temp = new byte[24];  //3des加密使用的是24位密钥

            //将密钥中的前24位字节拷贝到temp中
            System.arraycopy(keyBytes, 0, temp, 0, Math.min(keyBytes.length, temp.length));

            /**
             * 以下获取密钥过程
             * 1 密钥
             * 2 机密算法的名称
             */
            Key deskey = new SecretKeySpec(temp, "desede");

            /*=============使用CBC模式=============*/

            //获取一个密码生成器, 参数表示生成密码的算法名称
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");

            //初始化密码生成器
            IvParameterSpec ips = new IvParameterSpec(vector.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

             /*=============使用非CBC模式=============*/

            //获取一个密码生成器, 参数表示生成密码的算法名称
//            Cipher cipher = Cipher.getInstance("desede");

            //初始化密码生成器
//            cipher.init(Cipher.DECRYPT_MODE, deskey);

            byte[] bytes = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
            return new String(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     */
    public static String decode1(String encryptText) {
        return decode1(encryptText, secretKey, iv);
    }
}
