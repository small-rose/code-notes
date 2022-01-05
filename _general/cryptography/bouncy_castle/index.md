---
layout: default
title: Bouncy Castle密码库
nav_order: 密码学
parent: 密码学
---

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










