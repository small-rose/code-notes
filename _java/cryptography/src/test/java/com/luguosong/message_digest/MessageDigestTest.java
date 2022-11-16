package com.luguosong.message_digest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * 信息摘要
 *
 * @author luguosong
 * @date 2022/11/4
 */
public class MessageDigestTest {

    /**
     * 摘要计算测试
     *
     * @param md
     */
    private void testMessageDigest(MessageDigest md) {
        System.out.println("密码服务提供者为：" + md.getProvider());
        md.update("abc".getBytes());
        byte[] digest = md.digest();
        System.out.println(Hex.toHexString(digest).toUpperCase());
    }

    /**
     * JDK自带的摘要计算
     */
    @Test
    public void testSUN() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            testMessageDigest(md);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试BC库的摘要计算
     */
    @Test
    public void testBC() {
        try {
            //将BC库添加为首选服务提供者
            Security.insertProviderAt(new BouncyCastleProvider(), 1);
            MessageDigest md = MessageDigest.getInstance("SM3");
            testMessageDigest(md);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }
}
