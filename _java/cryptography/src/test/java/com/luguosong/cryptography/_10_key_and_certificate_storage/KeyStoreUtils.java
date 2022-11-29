package com.luguosong.cryptography._10_key_and_certificate_storage;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.OperatorCreationException;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import static com.luguosong.cryptography._6_signatures.EcDsaUtils.generateECKeyPair;
import static com.luguosong.cryptography._8_x509_certificates_and_attribute_certificates.JcaX509Certificate.createTrustAnchor;

public class KeyStoreUtils {
    /**
     * 使用关联的自签名证书创建私钥，将它们包装在 X500PrivateCredential 中返回
     *
     * @return an X500PrivateCredential包含密钥和对应证书
     */
    public static PrivateCredential createSelfSignedCredentials()
            throws GeneralSecurityException, OperatorCreationException {
        JcaX509CertificateConverter certConverter =
                new JcaX509CertificateConverter().setProvider("BC");

        KeyPair selfSignedKp = generateECKeyPair();

        X509CertificateHolder selfSignedHldr =
                createTrustAnchor(selfSignedKp, "SHA256withECDSA");

        X509Certificate selfSignedCert = certConverter.getCertificate(selfSignedHldr);

        return new PrivateCredential(selfSignedCert, selfSignedKp.getPrivate());
    }
}
