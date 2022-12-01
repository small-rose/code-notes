package com.luguosong.cryptography._10_key_and_certificate_storage;

import org.bouncycastle.crypto.util.PBKDFConfig;
import org.bouncycastle.crypto.util.ScryptConfig;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;

import static com.luguosong.cryptography._10_key_and_certificate_storage.KeyStoreUtils.createSelfSignedCredentials;

/**
 * @author luguosong
 * @date 2022/11/28
 */
public class Demo {

    /**
     * 初始化
     */
    @BeforeAll
    public static void init() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    /**
     * JKS密钥库
     *
     * @throws Exception
     */
    @Test
    public void testJKS() throws Exception {
        PrivateCredential cred = createSelfSignedCredentials();

        KeyStore store = KeyStore.getInstance("JKS");

        //初始化密钥存储库
        store.load(null, null);

        //存储私钥和证书
        store.setKeyEntry("key", cred.getPrivateKey(), "keyPass".toCharArray(),
                new Certificate[]{cred.getCertificate()});

        //创建文件输出流
        FileOutputStream fOut = new FileOutputStream("src/test/resources/store/basic.jks");

        //写入文件
        store.store(fOut, "storePass".toCharArray());

        //关闭文件输出流
        fOut.close();
    }

    /**
     * PKCS12
     */
    @Test
    public void testPKCS12() throws Exception {
        PrivateCredential cred = createSelfSignedCredentials();

        KeyStore store = KeyStore.getInstance("PKCS12", "BC");

        store.load(null, null);

        store.setKeyEntry("key", cred.getPrivateKey(), null,
                new Certificate[]{cred.getCertificate()});

        FileOutputStream fOut = new FileOutputStream("src/test/resources/store/basic.p12");

        store.store(fOut, "storePass".toCharArray());

        fOut.close();
    }

    /**
     * BCFKS
     *
     * @throws Exception
     */
    @Test
    public void testBCFKS() throws Exception{
        PrivateCredential cred = createSelfSignedCredentials();

        KeyStore store = KeyStore.getInstance("BCFKS", "BC");

        store.load(null, null);

        store.setKeyEntry("key", cred.getPrivateKey(), "keyPass".toCharArray(),
                new Certificate[] { cred.getCertificate() });

        FileOutputStream fOut = new FileOutputStream("src/test/resources/store/basic.fks");

        store.store(fOut, "storePass".toCharArray());

        fOut.close();
    }

    /**
     * BCFKSwithScrypt
     * @throws Exception
     */
    @Test
    public void testBCFKSwithScrypt() throws Exception{
        PrivateCredential cred = createSelfSignedCredentials();

        // 指定加密参数
        PBKDFConfig config = new ScryptConfig.Builder(1024, 8, 1)
                .withSaltLength(20).build();

        KeyStore store = KeyStore.getInstance("BCFKS", "BC");

        // 初始化空存储以使用 scrypt
        store.load(new BCFKSLoadStoreParameter.Builder()
                .withStorePBKDFConfig(config)
                .build());

        store.setKeyEntry("key", cred.getPrivateKey(), "keyPass".toCharArray(),
                new Certificate[] { cred.getCertificate() });

        FileOutputStream fOut = new FileOutputStream("src/test/resources/store/scrypt.fks");

        store.store(fOut, "storePass".toCharArray());

        fOut.close();
    }
}
