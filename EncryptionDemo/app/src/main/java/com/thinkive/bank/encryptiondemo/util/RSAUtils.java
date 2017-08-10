package com.thinkive.bank.encryptiondemo.util;


import android.util.Base64;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: RSA加解密工具类  加密key和解密key不同，非对称加密
 * 发信方使用收信方的公钥（public_key）加密并发送消息，收信方使用自己的私钥(private_key)解密得到明文
 * RSA算法原理：两个大素数的乘积极难被因式分解，因此这两个大素数可以分别被用来作为公钥和私钥
 */
public class RSAUtils {

    //两个大素数的乘积
    private static final String MODULUS = "100631058000714094813874361191853577129731636346" +
            "6842182066057798249316268307506230708031001897812113438517632753293640566406197" +
            "5533777992898527248609143138412802721336537200964823317189470833821316882486106" +
            "1809490615593530405056055952622249066180336803996949444124622212096805545953751" +
            "253607916170340397933039";
    //公钥
    private static final String PUB_KEY = "65537";
    //私钥
    private static final String PRI_KEY = "2690015571531364308778651652837454899882155938107" +
            "5740707715132776187148793016466508650068087107695523642202737697714709374658856" +
            "7337926144909438742059567276066746345636651546167589395765476637152346432730556" +
            "5882948281350395945965370806287562521000896123964377566135765559931285724941861" +
            "0810177817213648575161";

    private final static String encoding = "UTF-8";// 加解密统一使用的编码方式


    /**
     * 使用RSA进行加密
     *
     * @param originalString 需要进行加密的字符串
     * @return
     */
    public static String encode(String originalString) {
        try {
            //获取一个生成公钥和私钥的工厂方法
            KeyFactory keyFactory = KeyFactory.getInstance("rsa");

            //构造公钥的参数对象
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(
                    new BigInteger(MODULUS), new BigInteger(PUB_KEY));

            //获取一个密码生成器
            Cipher cipher = Cipher.getInstance("rsa");

            //生成公钥对象
            PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

            //初始化密码生成器
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //用公钥对象对文本内容进行加密
            byte[] bytes = cipher.doFinal(originalString.getBytes(encoding));

            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RSA进行解密
     *
     * @param encryptText 加密文本
     * @return
     */
    public static String decode(String encryptText) {

        try {
            //获取一个生成公钥和私钥的工厂方法
            KeyFactory keyFactory = KeyFactory.getInstance("rsa");

            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(
                    new BigInteger(MODULUS), new BigInteger(PRI_KEY));

            Cipher cipher = Cipher.getInstance("rsa");

            PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] bytes = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));

            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
