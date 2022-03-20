---
layout: default 
title: HotSpot虚拟机 
nav_order: 3
---

# 概述

## HotSpot VM整体结构概述

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220310214603.png)

- 类的加载
  - `Class文件`：Java源代码编译后产生的字节码文件
  - `类加载器`：将Class字节码文件加载到内存当中，生成Class对象

- 运行时数据区
  - 所有线程共享
    - `方法区`
    - `堆`
  - 线程隔离
    - `虚拟机栈`
    - `本地方法栈`
    - `程序计数器`
    
- `执行引擎`：将字节码翻译成机器语言
  - 解释器
  - JIT即时编译器
  - 垃圾回收器


## Java代码执行流程

1. Java源代码
2. 编译器
   1. 词法分析
   2. 语法分析
   3. 语法/抽象语法树
   4. 语义分析
   5. 注解抽象语法树
   6. 字节码生成器
3. 类的装载
   1. 加载
   2. 链接
   3. 初始化
4. 字节码校验器
5. 执行引擎
   1. 解析执行
   2. 即时编译执行
6. 机器语言

## 指令架构模型

指令集架构一般分为基于`栈`和`寄存器`的指令集架构。

JVM的`执行引擎`是基于`栈`的指令集架构。

`栈`和`寄存器`指令的区别：

- `栈`指令是`零地址指令`方式分配。
  `寄存器`指令往往是一地址指令，二地址指令和三地址指令为主，完全
  依赖硬件，可移植性差。
- `栈`指令不需要硬件的支持，移植性更好，更好的跨平台。
- `栈`的指令集更小，指令更多。`寄存器`花费更少的指令去完成一项操作

## Jvm生命周期

- 启动
    - 通过`引导类加载器`（bootstrap class loader）创建一个`初始类`（initial class）完成的
- 执行
    - 执行Java程序，实际执行的是一个叫Java虚拟机的进程。
- 结束
    - 程序正常执行结束
    - 遇到异常或错误而异常终止
    - 操作系统错误导致Java虚拟机终止
    - 调用Runtime类或者System类的exit方法，或者Runtime类的halt方法，并且Java安全管理器也允许这次exit或halt操作。
    - JNI Invocation API来加载或卸载

## 各个版本的虚拟机

- 1996年，Java1.0使用`Sun Classic`虚拟机。执行引擎只提供了解释器。效率比较低。JDK1.4被淘汰。
- JDK1.2提供了`Exact VM`
- `Longview Technologies`设计了`HotSpot虚拟机`，1997年该公司被sun收购。JDK1.3时HotSpot成为默认的虚拟机
- BEA的`JRockit虚拟机`专注于服务端应用，不包含解释器，全部代码都靠即时编译器编译后执行。最快的虚拟机
- BEA开发运行在自家Hypervisor系统上的`Liquid VM`
- IBM的`J9虚拟机`
- Oracle在Java ME产品线上两款虚拟机：`CDC/CLDC HotSpot Implementation VM`，`KVM虚拟机`是`CLDC-HI`早期产品，由于其简单，轻便，高度可移植。面向低端设备还有市场
- `Azul VM`是Azul Systems公司在HotSpot基础上进行大量改进。运行在Azul Systems公司专有硬件Vega系统上
- IBM和Intel联合开发的`Apache Harmony`
- 为了在IE3浏览器支持Java Applets,开发了`Microsoft JVM`。
- 阿里巴巴基于OpenJDK HotSpot VM开发了自己的定制版AlibabaJDK
- 2018年，Oracle Labs公开了`Graal VM`,可以作为`任何语言`的运行平台使用

其它相关虚拟机：

- 谷歌开发的`Dalvik VM`应用于Android系统。Android5.0后ART VM替换`Dalvik VM`

# 类加载子系统

## 加载

1. 通过一个class文件的全类名

## 链接

## 初始化



