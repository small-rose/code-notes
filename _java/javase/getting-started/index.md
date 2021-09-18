---
layout: docs
title: 起步
parent: JavaSE
nav_order: 1
---

# 关于Java

技术既是一种编程语言，又是一个平台。

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108271048586.jpeg)

## Java发展史

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108271056669.jpeg)

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108271404392.png)


- `1991年` 由James Gosling博士领导的绿色计划（Green Project）开始启动，这个计划的产品就是Java语言的前身：`Oak`
- `1995年` `Oak`语言改名为`Java`,`Java 1.0`版本发布
- `1996年` JDK 1.0发布，首届JavaOne大会
  - 纯解释执行的Java虚拟机实现（Sun Classic VM）
  - Applet
  - AWT
- `1997年` JDK 1.1发布
  - JAR文件格式
  - JDBC
  - JavaBeans
  - RMI
  - 内部类
  - 反射
- `1998年` JDK 1.2发布，Java技术体系拆分为三个方向：`J2SE`，`J2EE`，`J2ME`
  - EJB
  - Java Plug-in
  - Java IDL
  - Swing
  - JVM第一次内置了JIT（Just In Time）
  - 添加了strictfp关键
  - Collections集合类
- `2000年` JDK 1.3发布
  - `HotSpot称为正式虚拟机`
  - 数学运算类库
  - Timer API 库
  - JNDI服务从扩展服务提升为平台级服务
  - CORBA IIOP来实现RMI的通信协议
  - Java 2D API
  - JavaSound类库
- `2002年` JDK 1.4发布
  - 正则表达式
  - 异常链
  - NIO
  - 日志类
  - XML解析器
  - XSLT转换器
- `2004年` JDK 5发布
  - 自动装箱
  - 泛型
  - 动态注解
  - 枚举
  - 可变长参数
  - 遍历循环（foreach循环）
  - 改进了Java的内存模型
  - java.util.concurrent并发包
- `2006年` JDK 6发布,J2EE、J2SE、J2ME命名改为Java EE 6、Java SE 6、Java ME 6，`OpenJDK`组织建立，JDK开源
  - 初步的动态语言支持
  - 编译期注解处理器
  - 微型HTTP服务器API
  - 锁与同步、垃圾收集、类加载等方面的实现都有相当多的改动
- `2011年` JDK 7发布
  - 提供新的G1收集器
  - 加强对非Java语言的调用支持（JSR-292，这项特性在到JDK 11还有改动）
  - 可并行的类加载架构
- `2014年` JDK 8发布,`这是一个LTS版本的JDK`
  - Lambda表达式的支持
  - 内置Nashorn JavaScript引擎
  - 新的时间、日期API
  - 彻底移除HotSpot的永久代
- `2017年` JDK 9发布
  - Jigsaw
  - JS Shell、JLink、JHSDB增强
  - 整顿了HotSpot各个模块各自为战的日志系统
- `2018年3月` JDK 10发布
  - 内部重构
  - 本地类型推断
- `2018年9月` JDK 11发布,`这是一个LTS版本的JDK`
  - ZGC
- `2019年3月` JDK 12发布
  - Switch表达式
  - Java微测试套件
  - Shen-andoah垃圾收集器(历史上唯一进入了OpenJDK发布清单，但在OracleJDK中无法使用的功能。)

## 作为编程语言
[Java白皮书](https://www.oracle.com/java/technologies/language-environment.html)

### Java编译过程

> 在 Java 编程语言中，所有源代码首先编写在以 .java 扩展名结尾的纯文本文件中。 然后这些源文件被 javac 编译器编译成 .class 文件。 .class 文件不包含处理器的原生代码； 相反，它包含字节码——Java 虚拟机  (Java VM) 的机器语言。 然后，java 启动器工具使用 Java 虚拟机的实例运行您的应用程序。

![Java编译过程](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108092131409.gif)

> 由于 Java VM 可用于许多不同的操作系统，因此相同的 .class 文件能够在 Microsoft Windows、Solaris™ 操作系统 (Solaris OS)、Linux 或 Mac OS 上运行。 一些虚拟机，例如 Java SE HotSpot at a Glance，在运行时执行额外的步骤来提升应用程序的性能。 这包括各种任务，例如查找性能瓶颈和重新编译（到本机代码）经常使用的代码部分。

![通过JVM,一个程序可以在多个平台运行](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108092154284.gif)

## 作为平台

> 平台是程序运行的硬件或软件环境。 我们已经提到了一些最流行的平台，如 Microsoft Windows、Linux、Solaris OS 和 Mac OS。 大多数平台都可以描述为操作系统和底层硬件的组合。 Java 平台与大多数其他平台的不同之处在于它是一个纯软件平台，运行在其他基于硬件的平台之上。

`平台有两个组件：`
1. Java Virtual Machine JVM虚拟机
2. Java应用程序接口

`Java应用程序接口和JVM虚拟机将程序与底层硬件隔离:`

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202108092204754.gif)

> 作为一个独立于平台的环境，Java平台可能比本机代码慢一些，然而，编译器和虚拟机技术的进步使得性能可以接近本机代码，而且不会威胁可移植性

# Java技术能做什么

## 开发工具

> 开发工具提供了编译、运行、监控、调试和记录应用程序所需的一切。 作为一名新开发人员，您将使用的主要工具是 javac 编译器、java 启动器和 javadoc 文档工具。

## 应用程序编程接口

> API 提供了 Java 编程语言的核心功能。 它提供了大量有用的类，可以在您自己的应用程序中使用。 它涵盖了从基本对象到网络和安全性，再到 XML 生成和数据库访问等所有内容。 核心API非常大； 要了解它所包含的内容，请参阅 [Java Platform Standard Edition 8](https://docs.oracle.com/javase/8/docs/index.html) 文档。

## 部署技术

> JDK 软件提供了标准机制，例如 Java Web Start 软件和 Java Plug-In 软件，用于将您的应用程序部署到最终用户。

## 界面工具包

> JavaFX、Swing 和 Java 2D 工具包使创建复杂的图形用户界面 (GUI) 成为可能。

## 集成库

> 诸如 `Java IDL API`、`JDBC API`、`Java 命名和目录接口 (JNDI) API`、`Java RMI` 和基于 Internet 间 ORB 协议技术（Java RMI-IIOP 技术）的 Java 远程方法调用等集成库支持数据库访问 和操纵远程对象。

# 特点总结

1. 程序度量（类计数、方法计数等）的比较表明，用 Java 编程语言编写的程序可以比用 C++ 编写的相同程序小四倍。
2. 一次编写，到处运行 Write once, run anywhere
3. 使用 Java Web Start 软件，用户只需单击鼠标即可启动您的应用程序。 启动时的自动版本检查可确保用户始终使用最新版本的软件。 如果有可用更新，Java Web Start 软件将自动更新其安装
