---
layout: default
title: 密钥和证书存储
nav_order: 100
parent: 密码学
---

# 早期密钥存储类型

## JKS

JCA的原始密钥库格式，提供一定程度的防篡改保护。

通常用`.jks`作为后缀名

可以在不知道密钥库密码的情况下使用密钥工具检查密钥库的大部分内容

没有密钥库密码无法恢复私钥

```java
class Demo{
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
}
```

## JCEKS

随`JCE`引入。通过`Triple-DES`保护密钥。

通常用`.jceks`作为后缀名

可以在不知道密钥库密码的情况下使用密钥工具检查密钥库的大部分内容

在没有密码的情况下无法恢复受保护的秘密或私钥

## BKS

与JCEKS格式类似。通过`Triple-DES`保护密钥。

可以在不知道密钥库密码的情况下使用密钥工具检查`BKS`密钥库的大部分内容，尽管没有密码就无法恢复私钥

使用 `PKCS#12 PBKDF` 将密码转换为 BKS 中的密钥。

## UBER

`UBER`格式基于`BKS`，但与`BKS`不同的是，它会将密钥库存储为加密的blob，并使用`Twofish`加密算法进行保护。这意味着如果不使用密码，则无法使用Java密钥工具访问密钥库。

# keytool

keytool是一个Java实用程序,它提供密钥库的命令行处理。默认情况下，它与`JKS`密钥库一起使用

- -delete             删除条目
- -exportcert         导出证书
- -genkeypair         生成密钥对
- -genseckey          生成密钥
- -gencert            根据证书请求生成证书
- -importcert         导入证书或证书链
- -importpass         导入口令
- -importkeystore     从其他密钥库导入一个或所有条目
- -keypasswd          更改条目的密钥口令
- -list               列出密钥库中的条目
- -printcert          打印证书内容
- -printcertreq       打印证书请求的内容
- -printcrl           打印 CRL 文件的内容
- -storepasswd        更改密钥库的存储口令

## 查看密钥库信息

```shell
keytool -list -keystore basic.jks -storepass storePass
```

输出结果：

```text
密钥库类型: jks

key, 2022-11-28, PrivateKeyEntry,
证书指纹 (SHA1): 75:D9:1C:9E:34:B8:5E:95:12:37:EB:6B:6D:C2:50:37:8A:99:E1:DD

Warning:
JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore basic.jks -destkeystore basic.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
```

## 产生公私钥

```shell
# 生成公私钥对，同时会生成自签名证书
keytool -genkey -alias key -keyalg RSA -keystore rsa.jks -keysize 2048
```

## 生成证书请求

```shell
keytool -certreq -alias key -file csr.txt -keystore rsa.jks
```

## 导入从证书颁发机构接收到的证书


