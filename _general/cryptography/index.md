---
layout: default
title: 密码学
nav_order: 40
---

# JCA

## 概述

JCA（Java Cryptography Architecture）是Java平台一个主要部分。是一个使用Java 编程语言处理密码学的框架。
它是 Java安全API的一部分，最初是在JDK 1.1 的包中引入的。

## 设计原则

- 实现独立性
  - 应用程序不需要自己实现安全性。 相反，他们可以从Java平台请求安全服务。安全服务在Provider中实现，通过标准接口接入Java平台。 应用程序可能依靠多个独立的提供者来提供安全功能。
- 实现互操作性
  - Provider可以跨应用程序进行互操作。 具体而言，应用程序不绑定到特定的提供者，而提供者也不绑定到特定的应用程序。
- 算法独立性和可扩展性
  - Java平台包含许多内置的Provider，这些Provider实现了当今广泛使用的一组基本的安全服务。但是，一些应用程序可能依赖尚未实施的新兴标准或专有服务。 Java平台支持安装实现这些服务的定制提供程序。

## 加密服务提供者（CSP）

`java.security.Provider`是所有安全提供程序的基类。

```java
md = MessageDigest.getInstance("SHA-256");
md = MessageDigest.getInstance("SHA-256", "ProviderC");
```

下图说明了请求`SHA-256`消息摘要实现。这些图显示了实现各种消息摘要算法（`SHA-256`，`SHA-384`和`SHA-512`）
的三个不同的提供者。供应商按照优先顺序从左至右排列（1-3）。在第一个示例中，应用程序请求SHA-256算法实现而不指定提供者名称。
提供程序按优先顺序搜索，并返回提供该特定算法ProviderB的第一个提供程序的实现。 在第二个图中，
应用程序请求来自特定提供者ProviderC的SHA-256算法实现。 这次ProviderC的实现被返回，
即使具有更高优先级的提供者ProviderB也提供SHA-256实现。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202201181410412.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202201181410750.png)

## 引擎类

引擎类为特定类型的密码服务提供接口，而不依赖于特定的密码算法或提供者。

- `SecureRandom`：用于生成随机或伪随机数字。
- `MessageDigest`：用于计算指定数据的消息摘要（散列）。
- `Signature`：使用密钥初始化，这些签名用于签署数据并验证数字签
- `Cipher`：用密钥初始化，用于加密/解密数据。存在各种类型的算法：对称批量加密（例如AES），非对称加密（例如RSA）和基于密码的加密（例如PBE）。
- `Message` Authentication Codes（MAC）：与MessageDigests一样，它们也会生成散列值，但是首先使用密钥初始化以保护消息的完整性。
- `KeyFactory`：用于将Key类型的现有不透明密钥转换为密钥规范（底层密钥材料的透明表示），反之亦然。
- `SecretKeyFactory`：用于将SecretKey类型的现有不透明加密密钥转换为密钥规范（底层密钥材料的透明表示），反之亦然。 SecretKeyFactorys是专门的KeyFactorys，只能创建密钥（对称）。
- `KeyPairGenerator`：用于生成一对适用于指定算法的公钥和私钥。
- `KeyGenerator`：用于生成与指定算法一起使用的新密钥。
- `KeyAgreement`：由两方或多方使用，商定和建立一个特定的密钥，用于特定的密码操作。
- `AlgorithmParameters`：用于存储特定算法的参数，包括参数编码和解码。
- `AlgorithmParameterGenerator`：用于生成适合于指定算法的一组AlgorithmParameters。
- `KeyStore`：用于创建和管理密钥库。密钥库是密钥的数据库。密钥库中的私钥具有与其关联的证书链，用于验证相应的公钥。密钥库还包含来自可信实体的证书。
- `CertificateFactory`：用于创建公钥证书和证书吊销列表（CRL）。
- `CertPathBuilder`：用于构建证书链（也称为证书路径）。
- `CertPathValidator`：用于验证证书链。
- `CertStore`：用于从存储库中检索证书和CRL。

## Provider类

它具有访问提供程序名称，版本号和其他信息的方法。 请注意，除了注册加密服务的实现外，
Provider类还可以用于注册可能被定义为JDK安全API或其扩展之一的其他安全服务的实现。

## SecureRandom类

SecureRandom类是提供随机数生成器（RNG）功能的引擎类。



# ASN.1

`ASN.1`(Abstract Syntax Notation One)是一种描述对象如何编码以便传输的语言，起源于20世纪80年代。

绝大多数涉及密码学的标准使用ASN.1。

## 基本语法

### 注释

```text
/*注释样式一*/

--注释样式二--
```

### 对象标识符

由一系列用点分隔的数字组成。

例如，NIST使用sha3-512的ECDSA的对象标识符是`2.16.840.1.101.3.4.3.12`。

### 模块结构

```text
module_name { object_identifier }
DEFINITIONS tagging TAGS ::=
BEGIN
EXPORTS export_list ;
IMPORTS import_list ;
body
END
```

`module_name`和`object_identifier`用于标识被定义的模块。

`tagging`定义了模块中使用的标签类型。

`body`包含模块中定义的结构的局部定义

## 基本类型

大部分简单类型只有一种编码方式，一次无论使用DER或BER，它们的编码方式始终是相同的。`UTCTime`和`GeneralizedTime`除外。

### BOOLEAN

表示一个true和false。

Bouncy Castle密码库中由`ASN1Boolean`表示。`ASN1Boolean.TRUE`和`ASN1Boolean.FALSE`表示两个常量。

### ENUMERATED

枚举，无符号。是`INTEGER`的一种特殊情况

Bouncy Castle密码库中由`ASN1Enumerated`表示

### INTEGER

表示任意大小的整数，有符号。

Bouncy Castle密码库中由`ASN1Integer`表示。

### NULL

一个显示的空值。

- 与Java的NULL不同
  - Java的NULL表示缺失的概念，意味着没有编码的存在。
  - 这里NULL有一个真正的编码来表示它

Bouncy Castle密码库中由`ASN1Null`或遗留的`DERNull`表示。

### OBJECT IDENTIFIER

对象标识符

Bouncy Castle密码库中由`ASN1ObjectIdentifier`表示

### UTCTime

协调世界时

Bouncy Castle密码库中由`ASN1UTCTime`表示

### GeneralizedTime

有4位数字年份，可用于表示任意精度的秒数。

Bouncy Castle密码库中由`ASN1GeneralizedTime`或`DERGeneralizedTime`表示。

## String类型

### 比特字符串

#### BIT STRING

允许存储任意长度比特的字符串。

它们被编码为两部分：
- 第一部分是填充位数。在DER编码中，填充位都应该为0
- 后面是一串八位字节组成的实际的位串

Bouncy Castle密码库中由`ASN1BitString`和`DERBitString`表示

#### OCTET STRING

允许存储任意的八进制字符串。

Bouncy Castle密码库中由`ASN1OctetString`、`BEROctetString`和`DEROctetString`表示

`BEROctetStringGenerator`和`BEROctetStringParser`用于处理`BEROctetString`的类

### 字符串类型

#### BMPString

Basic Multilingual Plane

字符集由ISO10646表示，与Unicode表示的字符集相同。

Bouncy Castle密码库中由`ASN1BMPString`和`DERBMPString`表示

#### GeneralString

它可以包含ISO2375编码字符集国际注册表中描述的任何字符，包括控制字符。

Bouncy Castle密码库中由`ASN1GeneralString`和`DERGeneralString`表示。

#### GraphicString

这个字符串类型来源于与`GeneralString`相同的ISO2375编码字符集，但是没有控制字符。只允许打印字符。

Bouncy Castle密码库中由`ASN1GraphicString`和`DERGraphicString`表示。

#### IA5String

由`International Alphabet 5`中的字符组成。这是一个旧的ITU-T（国际电信联盟电信标准化部门）建议。
现在，它们被认为涵盖了整个`ASCII`字符集

Bouncy Castle密码库中由`ASN1IA5String`和`DERIA5String`表示。

#### NumericString

只包含0到9和空格

Bouncy Castle密码库中由`ASN1NumericString`和`DERNumericString`表示。

#### PrintableString

从ASCII的一个子集中绘制字符。包含`A`到`Z`，`a`到`z`，`0`到`9`,` `,`’`,`(`,`)`,`+`,`-`,`.`,`:`,`=`,`?`,`/`,`,`。

Bouncy Castle密码库中由`ASN1PrintableString`和`DERPrintableString`表示。

#### TeletexString

最初被称为`T61String`。它是8位字符类型，它支持通过使用以ASCII ESC开头的字符序列（转义字符）来更改字符集。

Bouncy Castle密码库中由`ASN1T61String`和`DERT61String`表示。

#### UniversalString

为了国际化而添加。默认情况下，Bouncy Castle会将这些字符串转换为显示字符编码的HEX字符串。

Bouncy Castle密码库中由`ASN1UniversalString`和`DERUniversalString`表示

#### UTF8String

使用UTF8编码，是完全国际化的推荐字符串类型。

Bouncy Castle密码库中由`ASN1UTF8String`和`DERUTF8String`。

#### VideotexString

设计用于视频文本系统并容纳可以使用控制代码构建简单图像的8位字符。

Bouncy Castle密码库中由`ASN1VideotexString`和`DERVideotexString`

#### VisibleString

最初，这种类型旨在仅包含来自ISO 646的字符，但自1994年以来，它已被解释为包含没有控制字符的纯ASCII。

Bouncy Castle密码库中由`ASN1VisibleString`和`DERVisibleString`表示

## 容器类型

### SET

Bouncy Castle密码库中由`ASN1Set`表示。

### SEQUENCE

Bouncy Castle密码库中由`ASN1Sequence`表示。

`ASN1Sequence`是抽象类，其子类有

## 选择类型

Bouncy Castle密码库提供`ASN1Choice`标记接口。

## 编码规则

ASN1编码系统的核心是围绕基本编码规则（BER）构建的，DER和CER都是BER的子集。

- BER三种编码ASN1的方法
  - 原始长度
  - 构造的确定长度
  - 构造的不定长度

## 自定义对象










