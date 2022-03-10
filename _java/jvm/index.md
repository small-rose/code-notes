---
layout: default
title: Java虚拟机
nav_order: 3
---

# HotSpot VM整体结构概述

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


# Java代码执行流程

1. Java源代码
2. 编译器
   1. 词法分析
   2. 语法分析
   3. 
