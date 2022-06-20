---
layout: default
title: JVM
nav_order: 30
---

# 概述

Java虚拟机（Java Virtual Machine）。Java字节码文件的运行环境

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619111037.png)

- 让Java可以一次编写，到处运行
- 自动管理内存，垃圾回收功能

# Java内存结构图

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619170909.png)

- 类加载器子系统：加载class文件
- 执行引擎：
- 

# 类加载器子系统（Class Loader SubSystem）

## 加载（Loading）

1. 获取class文件的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. 

## 链接（Linking）

## 初始化（Initialization）

# 程序计数器（PC Register）

Program Counter Register程序计数器（寄存器）

通过`寄存器`记住下一条指令的执行地址

- 线程私有
- 不会存在内存溢出

# 虚拟机栈（JVM Stacks）

## 概述

`虚拟机栈`是线程`运行时`的内存空间。

`栈帧`：`虚拟机栈`中存放`栈帧`，`栈帧`表示每个方法运行时需要的内存。

每个线程只能有一个`活动栈帧`，也就是栈顶的`栈帧`，对应着当前正在执行的那个方法。


## 实例说明

```java
/**
 * @author luguosong
 * @date 2022/6/19
 */
public class StacksHello {
    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        method2(1,2);
    }

    private static int method2(int a, int b) {
        int c = a + b;
        return c;
    }
}
```

- 程序执行，`main方法`入栈

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619121116.png)

- `main方法`调用`method1方法`，`method1方法`入栈

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619121518.png)

- `method1方法`调用`method2方法`，`method2方法`入栈

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619122420.png)

- `method2方法`执行完，出栈

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619122536.png)

- `method1方法`执行完，出栈

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220619122624.png)

- 最后，`main方法`出栈

## 栈内存溢出

- 栈帧过多
  - 错误的方法的递归调用
- 栈帧过大

## 线程诊断


