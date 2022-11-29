package com.luguosong.cryptography._6_signatures;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class EcDsaUtils {
    /**
     * 在指定名称的椭圆曲线上生成密钥对
     *
     * @param curveName 椭圆曲线名称
     * @return EC密钥对
     */
    public static KeyPair generateECKeyPair(String curveName)
            throws GeneralSecurityException {
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("EC", "BC");

        keyPair.initialize(new ECGenParameterSpec(curveName));

        return keyPair.generateKeyPair();
    }

    /**
     * 创建在P-256椭圆曲线上的密钥对
     *
     * @return 密钥对
     */
    public static KeyPair generateECKeyPair()
            throws GeneralSecurityException {
        return generateECKeyPair("P-256");
    }

    /**
     * 计算ECDSA签名
     *
     * @param ecPrivate the private key for generating the signature with.
     * @param input     the input to be signed.
     * @return the encoded signature.
     */
    public static byte[] generateECDSASignature(
            PrivateKey ecPrivate, byte[] input)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");

        signature.initSign(ecPrivate);

        signature.update(input);

        return signature.sign();
    }

    /**
     * 签名验证
     *
     * @param ecPublic     签名创建者的公钥
     * @param input        输入
     * @param encSignature 签名
     * @return 验签结果
     */
    public static boolean verifyECDSASignature(
            PublicKey ecPublic, byte[] input, byte[] encSignature)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");

        signature.initVerify(ecPublic);

        signature.update(input);

        return signature.verify(encSignature);
    }

    /**
     * 计算ECDSA (ECDDSA)签名。
     *
     * @param ecPrivate 用于生成签名的私钥。
     * @param input     要签名的输入
     * @return 签名.
     */
    public static byte[] generateECDDSASignature(
            PrivateKey ecPrivate, byte[] input)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDDSA", "BC");

        signature.initSign(ecPrivate);

        signature.update(input);

        return signature.sign();
    }
}
