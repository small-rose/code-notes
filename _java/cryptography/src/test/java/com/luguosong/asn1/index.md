---
layout: default
title: Asn1
nav_order: 10
parent: 密码学
---

# 对象标识符（OID）

`对象标识符`（英语：object identifiers，缩写OID）是由国际电信联盟（ITU）和国际标准化组织（ISO/IEC）标准化的一个标识符机制，用来在全球范围内使用一个明确的永久名称命名任何对象、概念或“事物”。

一个OID对应`OID树`或层次结构中的一个`节点`，而节点由ITU的OID标准X.660正式定义。整个树的`根`主要包含下列三个子节点：

- `0`：ITU-T
- `1`：ISO
- `2`：joint-iso-itu-t

OID树中的每个节点都是由一系列的点分隔的整数表示，这些整数对应由根到一系列主节点到节点的路径。例如，表示英特尔公司的OID如下所示：

`1.3.6.1.4.1.343`

这对应OID树中的下列路径：

- 1 ISO
- 1.3 识别组织
- 1.3.6 美国国防部
- 1.3.6.1 网络
- 1.3.6.1.4 私有
- 1.3.6.1.4.1 IANA企业编号
- 1.3.6.1.4.1.343 英特尔公司

OID路径的文本表示形式也很常见。例如，

`iso.identified-organization.dod.internet.private.enterprise.intel`

树中的每个节点都由一个分配机构控制，该机构可以在该节点下定义子节点，并为子节点委托分配机构。延续上一个例子，根节点`1`下的节点号由ISO分配； `1.3.6`下的节点由美国国防部分配；`1.3.6.1.4.1`下的节点由互联网号码分配局（IANA）分配；`1.3.6.1.4.1.343`下的节点由英特尔公司分配，依此类推。

`1.2.156`下的节点表示中国，其下的节点可以在[国家OID注册中心](http://www.china-oid.org.cn/)注册查询。


# 模块结构

```
module_name { object_identifier }
DEFINITIONS tagging TAGS ::=
BEGIN
EXPORTS export_list ;
IMPORTS import_list ;
body
END
```

- `EXPORTS`：指明本模块中定义的哪些类型（值）可以被其他模块引用。
  - 如果模块中没有`EXPORTS`部分，则表示所有类型（值）都可以被其他模块引用；
  - `EXPORTS`后跟的类型（值）列表为空，形如`EXPORTS ;`，则表示模块中所有定义的类型（值）都不能被其他模块引用。
- `IMPORTS`：指明本模块从其他模块引用的类型（值）定义

举例说明：

```
Module2　DEFINITIONS　::= 
BEGIN 
EXPORTS Type2; 
IMPORTS Type1, value FROM Module1; 
Type2 ::= INTEGER (0..value)　
　　Type3 ::= Type1 
END
```

新定义的模块名是`Module2`，紧接着是关键字`DEFINITIONS`，然后是`：：=`，包含在`BEGIN和END`关键字之间的都是模块体。本模块从`Module1`模块中引用了类型`Type1`和值`value`，`Type2`和`Type3`是模块中定义的类型，并且`Type2`可以被其他模块引用。


# 类型概览

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/ASN1Sequence.png)

# 简单类型

## BOOLEAN(布尔类型)

表示一个`true`或`false`值。由`ASN1Boolean`表示，`ASN1Boolean.true`和`ASN1Boolean.false`表示两个常量。

## ENUMERATED（枚举）

由`ASN1Enumerated`表示

## INTEGER(整数)

由`ASN1Integer`表示

## NULL（空）

一个显式的空值，由`ASN1Null`表示

## OBJECT IDENTIFIER（对象标识符）

由`ASN1ObjectIdentifier`表示

## UTCTime(UTC时间)

[协调世界时](https://zh.wikipedia.org/wiki/%E5%8D%8F%E8%B0%83%E4%B8%96%E7%95%8C%E6%97%B6)

此数据类型仅在 1950-01-01 00:00:00 UTC 到 2049-12-31 23:59:59 UTC 期间有效

由`ASN1UTCTime`和`DERUTCTime`表示

## GeneralizedTime(Generalized时间)

本地时间数据类型，由`ASN1GeneralizedTime`和`DERGeneralizedTime`表示

# 位字符串类型

## BIT STRING（比特串）

比特串是以比特为单位任意的字符串（0，1串）

第一部分是填充位的数量，后跟组成实际位串的八位字节串。

在`DER`编码中，填充位应全部为零。

由 `ASN1BitString` 和 `DERBitString` 表示。

## OCTET STRING(字节串)


# 字符字符串类型

# 编码规则

## Basic Encoding Rules

## Distinguished Encoding Rules

## Canonical Encoding Rules
