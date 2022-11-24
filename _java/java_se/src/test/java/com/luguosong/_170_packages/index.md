---
layout: default
title: 程序包
nav_order: 170
parent: JavaSE
---

# 概述

{: .new-title}
> 定义
> 
> 一个`程序包`是相关类型分组，提供访问保护和命名空间管理。

利用程序包可以更容易地查找和使用类型，避免命名冲突，并控制访问。

# 程序包命名

- 程序包的名称全用小写字母
- 公司用`反向域名`作为程序包名的起始部分
- 程序包可以使用下划线

# 程序包成员使用

- 用完全限定名指代程序包成员

```java
class Demo{
    public static void main(String[] args) {
        Integer integer = new java.lang.Integer(10);
    }
}
```

- 导入包成员

```java
import java.lang.Integer;
```

- 导入整个程序包

```java
import java.lang.*;
```

{: .warning.title}
> 提示
> 
> 导入整个包，并不包含包下面的子包
