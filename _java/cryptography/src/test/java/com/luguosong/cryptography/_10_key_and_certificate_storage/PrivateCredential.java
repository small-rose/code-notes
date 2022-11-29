package com.luguosong.cryptography._10_key_and_certificate_storage;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * 私钥及其对应证书
 *
 * JDK还提供了：javax.security.auth.x500.X500PrivateCredential
 * BC也提供了稍微更通用的类：org.bouncycastle.pkix.PKIXIdentity
 *
 * 因为有些环境中没有上述类，因此自定义
 *
 * @author luguosong
 * @date 2022/11/28
 */
public class PrivateCredential {
    private final X509Certificate certificate;
    private final PrivateKey privateKey;

    /**
     * 基本构造
     *
     * @param certificate 与私钥匹配的公钥证书.
     * @param privateKey  与证书参数匹配的私钥.
     */
    public PrivateCredential(X509Certificate certificate, PrivateKey privateKey) {
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }
}
