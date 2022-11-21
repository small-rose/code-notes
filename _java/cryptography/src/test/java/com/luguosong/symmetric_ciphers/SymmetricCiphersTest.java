package com.luguosong.symmetric_ciphers;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author luguosong
 * @date 2022/11/14
 */
public class SymmetricCiphersTest {

    /**
     * 初始化密钥
     */
    @Test
    public void testKeyGenerator() throws NoSuchAlgorithmException {
        Security.insertProviderAt(new BouncyCastleProvider(),1);
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecretKey key = generator.generateKey();
        System.out.println("算法："+key.getAlgorithm());
        System.out.println("编码格式："+key.getFormat());
        System.out.println(Hex.toHexString(key.getEncoded()));
    }

    /**
     *
     */
    @Test
    public void testSecretKeySpec(){
        byte[] keyBytes="123456".getBytes();
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        System.out.println("算法："+key.getAlgorithm());
        System.out.println("编码格式："+key.getFormat());
        System.out.println(new String(key.getEncoded()));
    }


}
