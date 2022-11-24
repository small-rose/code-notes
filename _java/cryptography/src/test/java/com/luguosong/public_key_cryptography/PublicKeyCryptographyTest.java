package com.luguosong.public_key_cryptography;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.*;
import java.util.Date;

/**
 * 公钥密码学测试类
 *
 * @author luguosong
 */
public class PublicKeyCryptographyTest {

    /**
     * 初始化
     */
    @BeforeAll
    public static void init() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    /**
     * 生成密钥对，并编码
     */
    @Test
    public void testKeyPairGenerator() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        //指定为椭圆曲线算法
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ec");

        //初始化为国密椭圆曲线
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        keyPairGenerator.initialize(sm2Spec);

        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //信息展示
        PublicKey publicKey = keyPair.getPublic();
        //获取公钥编码
        System.out.println("公钥：" + Hex.toHexString(publicKey.getEncoded()));
        System.out.println("公钥编码格式：" + publicKey.getFormat());
        System.out.println("公钥算法名称：" + publicKey.getAlgorithm());

        if (publicKey instanceof BCECPublicKey) {
            BCECPublicKey bcecPublicKey = (BCECPublicKey) publicKey;
            System.out.println(bcecPublicKey);
        }

        PrivateKey privateKey = keyPair.getPrivate();
        //获取私钥编码
        System.out.println("私钥：" + Hex.toHexString(privateKey.getEncoded()));
        System.out.println("私钥编码格式：" + privateKey.getFormat());
        System.out.println("私钥算法名称：" + privateKey.getAlgorithm());

        if (privateKey instanceof BCECPrivateKey) {
            BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) privateKey;
            System.out.println(bcecPrivateKey);
        }
    }

    /**
     * 密钥对解码
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @Test
    public void testKeyFactory() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyHex = "3059301306072a8648ce3d020106082a811ccf5501822d0342000437a1e37ed862c2a9c08c84fa9e6ce9abb04b72c1fba088c85f5ba59bc86363be9549a2da40034f5273ea5e491f49a6a03f19b5156b8575b660d7a312c61ba946";
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Hex.decode(publicKeyHex));

        String privateKeyHex = "308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104208888b9ff2b0182621205ce8ad52bee50875b9e075b682653a4506abecc80d0d6a00a06082a811ccf5501822da144034200044f17f8b19d678f7bd3559e69fac55cb1ae41d2b54de5cc67bab3237f07ba600d32d5168ab20c2655e1999545eb6a0044ae91f02bf0b8219c7d8bec2ea9664a48";
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Hex.decode(privateKeyHex));

        KeyFactory factory = KeyFactory.getInstance("ec");
        //解码公钥
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        //解码私钥
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
    }

    /**
     * SM3withSM2签名,验签
     */
    @Test
    public void testSignature() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        //初始化密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ec");
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        keyPairGenerator.initialize(sm2Spec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //Signature 对象在内部使用默认的 SM2 ID 值。
        //Bouncy Castle 还支持参数规范 - SM2ParameterSpec
        // 以允许显式设置用于签名计算的 ID 字符串。
        SM2ParameterSpec sm2ParameterSpec = new SM2ParameterSpec(
                Strings.toByteArray("Signer@Octets.ID"));

        //签名
        String text = "hello";
        System.out.println("原文：" + text);
        //三种写法
        //Signature signature = Signature.getInstance("1.2.156.10197.1.501");
        //Signature verify = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString());
        Signature sign = Signature.getInstance("SM3withSM2");
        sign.setParameter(sm2ParameterSpec);  //这一步非必要
        sign.initSign(keyPair.getPrivate());
        sign.update(text.getBytes());
        byte[] signResult = sign.sign();
        String signHex = Hex.toHexString(signResult);
        System.out.println("签名结果：" + signHex);

        //验签
        Signature verify = Signature.getInstance("SM3withSM2");
        verify.setParameter(sm2ParameterSpec);  //这一步非必要
        verify.initVerify(keyPair.getPublic());
        verify.update(text.getBytes());
        System.out.println("验签结果：" + verify.verify(Hex.decode(signHex)));
    }


    /**
     * RSA密钥传输,方式一
     */
    @Test
    public void testKeyTransportByRsaOaep1() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, IOException {
        //初始化密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //生成对称密钥
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecretKey key = generator.generateKey();
        System.out.println("原始密钥：" + Hex.toHexString(key.getEncoded()));

        //公钥加密AES密钥
        Cipher encryption = Cipher.getInstance("RSA/NONE/OAEPwithSHA256andMGF1Padding");
        encryption.init(Cipher.WRAP_MODE, keyPair.getPublic());
        byte[] keyEncode = encryption.wrap(key);
        System.out.println("加密后的密钥：" + Hex.toHexString(keyEncode));

        //私钥解密AES密钥
        Cipher decrypt = Cipher.getInstance("RSA/NONE/OAEPwithSHA256andMGF1Padding");
        decrypt.init(Cipher.UNWRAP_MODE, keyPair.getPrivate());
        SecretKey secretKey = (SecretKey) decrypt.unwrap(keyEncode,
                key.getAlgorithm(),
                Cipher.SECRET_KEY);
        System.out.println("解密后的密钥：" + Hex.toHexString(secretKey.getEncoded()));
    }

    /**
     * RSA密钥传输,方式二
     */
    @Test
    public void testKeyTransportByRsaOaep2() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException {
        //初始化密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //生成对称密钥
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecretKey key = generator.generateKey();
        System.out.println("原始密钥：" + Hex.toHexString(key.getEncoded()));

        OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                new PSource.PSpecified(Strings.toByteArray("My Label")));

        //公钥加密AES密钥
        Cipher encryption = Cipher.getInstance("RSA");
        encryption.init(Cipher.WRAP_MODE, keyPair.getPublic(), oaepSpec);
        byte[] keyWrapped = encryption.wrap(key);
        System.out.println("加密后的密钥：" + Hex.toHexString(keyWrapped));

        //私钥解密AES密钥
        Cipher decrypt = Cipher.getInstance("RSA");
        decrypt.init(Cipher.UNWRAP_MODE, keyPair.getPrivate(), oaepSpec);
        SecretKey secretKey = (SecretKey) decrypt.unwrap(keyWrapped,
                key.getAlgorithm(),
                Cipher.SECRET_KEY);
        System.out.println("解密后的密钥：" + Hex.toHexString(secretKey.getEncoded()));
    }

    /**
     * ElGamal密钥传输
     */
    @Test
    public void testKeyTransportByElGamalOAEP() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, InvalidKeyException, IllegalBlockSizeException {
        //生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //生成对称密钥
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecretKey key = generator.generateKey();
        System.out.println("原始密钥：" + Hex.toHexString(key.getEncoded()));

        //公钥加密AES密钥
        Cipher encryption = Cipher.getInstance(
                "ElGamal/NONE/OAEPwithSHA256andMGF1Padding");
        encryption.init(Cipher.WRAP_MODE, keyPair.getPublic());
        byte[] keyWrapped = encryption.wrap(key);
        System.out.println("加密后的密钥：" + Hex.toHexString(keyWrapped));

        Cipher decrypt = Cipher.getInstance(
                "ElGamal/NONE/OAEPwithSHA256andMGF1Padding");
        decrypt.init(Cipher.UNWRAP_MODE, keyPair.getPrivate());
        Key secretKey = decrypt.unwrap(keyWrapped, key.getAlgorithm(), Cipher.SECRET_KEY);
        System.out.println("解密后的密钥：" + Hex.toHexString(secretKey.getEncoded()));
    }


    /**
     * RSA-KEM密钥传输
     */
    @Test
    public void testKeyTransportByRSAKEM() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        System.out.println("暂时实现不了，书145页");
    }

    //密钥协商

    //密钥交换

    /**
     * X500Name和X500Principal互相转换
     *
     * @throws IOException
     */
    @Test
    public void testX500NameToX500Principal() throws IOException {
        X500NameBuilder x500Bldr = new X500NameBuilder();
        //X500Name转X500Principal
        X500Principal x500Principal = new X500Principal(x500Bldr.build().getEncoded());
        //X500Principal转X500Name
        X500Name x500Name = X500Name.getInstance(x500Principal.getEncoded());
    }


    /**
     * 工具类：以秒为单位计算日期
     *
     * @param hoursInFuture
     * @return
     */
    private Date calculateDate(int hoursInFuture) {
        long secs = System.currentTimeMillis() / 1000;

        return new Date((secs + (hoursInFuture * 60 * 60)) * 1000);
    }

    private static long serialNumberBase = System.currentTimeMillis();

    /**
     * 工具类：生成序列号
     *
     * @return
     */
    private static BigInteger calculateSerialNumber() {
        return BigInteger.valueOf(serialNumberBase++);
    }

    /**
     * 创建V1版证书
     *
     * @return
     * @throws OperatorCreationException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public X509CertificateHolder createTrustAnchor()
            throws OperatorCreationException, NoSuchAlgorithmException, CertificateException {

        //初始化密钥对
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("EC");
        KeyPair keyPair = kpGen.generateKeyPair();

        String sigAlg = "SM3withSM2";

        X500NameBuilder x500NameBld = new X500NameBuilder(BCStyle.INSTANCE)
                .addRDN(BCStyle.C, "AU")
                .addRDN(BCStyle.ST, "Victoria")
                .addRDN(BCStyle.L, "Melbourne")
                .addRDN(BCStyle.O, "The Legion of the Bouncy Castle")
                .addRDN(BCStyle.CN, "Demo Root Certificate");

        X500Name name = x500NameBld.build();


        X509v1CertificateBuilder certBldr = new JcaX509v1CertificateBuilder(
                name,
                calculateSerialNumber(),
                calculateDate(0),
                calculateDate(24 * 31),
                name,
                keyPair.getPublic());

        ContentSigner signer = new JcaContentSignerBuilder(sigAlg)
                .setProvider("BC").build(keyPair.getPrivate());

        return certBldr.build(signer);




    }

    /**
     * X509CertificateHolder和X509Certificate互相转换
     *
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws OperatorCreationException
     */
    public void convertingX509CertificateHolder() throws CertificateException, NoSuchAlgorithmException, OperatorCreationException {
        X509CertificateHolder certHolder1 = createTrustAnchor();

        //X509CertificateHolder转为X509Certificate
        X509Certificate x509Cert = new JcaX509CertificateConverter().getCertificate(certHolder1);

        //这段主要展示X509Certificate转为X509CertificateHolder
        X509CertificateHolder certHolder2 = new JcaX509CertificateHolder(x509Cert);
    }

    /**
     * CertificateFactory通过流解析x509
     * inStream中提供的证书必须是 DER 编码的，并且可以二进制或可打印 (Base64) 编码提供。
     * 如果证书以 Base64 编码提供，则必须在开头以 -----BEGIN CERTIFICATE----- 为界，并且必须在结尾以 -----END CERTIFICATE----- 为界.
     *
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws OperatorCreationException
     * @throws IOException
     */
    @Test
    public void getX509Certificate() throws CertificateException, NoSuchAlgorithmException, OperatorCreationException, IOException {

        CertificateFactory cFact = CertificateFactory.getInstance("X.509");
        //获取base64编码的x509
        Certificate certificateBase64 = cFact.generateCertificate(new FileInputStream("src/test/resources/cert/base64.cer"));
        System.out.println(certificateBase64);

        //获取der编码的x509
        Certificate certificateDer = cFact.generateCertificate(new FileInputStream("src/test/resources/cert/der.cer"));
        System.out.println(certificateDer);
    }
}
