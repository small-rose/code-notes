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

# 类加载器子系统（Class Loader SubSystem）

## 类加载过程

### 加载（Loading）

1. 获取class文件的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. 在内存中生成一个代表这个类的`java.lang.Class`对象，作为方法区这个类的各种数据的访问入口

### 链接（Linking）

#### 验证（Verify）

- 目的在于确保c1ss文件的字节流中包含信息符合当前虚拟机要求，保证被加载类的正确性：不会危害虚拟机自身安全。
- 主要包括四种验证，`文件格式验证`，`元数据验证`，`字节码验证`，`符号引用验证`。

#### 准备（prepare）

- 为`类变量`分配内存并且设置该`类变量`的默认初始值，即`零`值。
- 这里不包含用final修饰的static,因为final在编译的时候就会分配了，准备阶段会显式初始化。
- 这里不会为`实例变量`分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。

#### 解析(resolve)

- 将常量池内的符号引用转换为直接引用的过程
- 事实上，解析操作往往会伴随着JVM在执行完初始化之后再执行
- 符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java虚拟机规范》的c1ass文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。
- 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中的CONSTANT Class info,CONSTANT Fieldref info,CONSTANT Methodref info

### 初始化（Initialization）

- 初始化阶段就是执行类构造器方法<clinit>()的过程
- 此方法不需定义，是javac编译器自收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来。
- 构造器方法中指令按语句在源文件中出现的顺序执行。
- <clinit>()不同于类的构造器。(关联：构造器是虚拟机视角下的<init>())
- 若该类具有父类，JM会保证子类的<clinit>()执行前，父类的<clinit>()已经执行完毕。
- 虚拟机必须保证一个类的<clinit>()方法在多线程下被同步加锁。

## 类加载器分类

### 引导类加载器

### 自定义类加载

派生自

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


