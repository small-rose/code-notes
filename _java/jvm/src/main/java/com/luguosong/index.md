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

## 概述

`类加载器子系统`负责将Class文件加载存放到`方法区`的内存空间。`方法区`还会存放`运行时常量池信息`，可能还包括`字符串字面量`和`数字常量`。

## class文件结构

## 类加载过程

### 加载阶段（Loading）

1. 获取class文件的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为`方法区`的运行时数据结构
3. 在内存中生成一个代表这个类的`java.lang.Class`对象，作为`方法区`这个类的各种数据的访问入口

### 链接阶段（Linking）

#### 验证（Verify）

- 目的在于确保c1ss文件的字节流中包含信息符合当前虚拟机要求，保证被加载类的正确性：不会危害虚拟机自身安全。
- 主要包括四种验证:
  - `文件格式验证`
    - CA FE BA BE(魔数，Java虚拟机识别)
    - 主次版本号
    - 常量池的常量中是否有不被支持的常量类型
    - 指向常量的各种索引值中是否有指向不存在的常量或不符合类型的常量
  - `元数据验证`
    - 对字节码描述的信息进行语义分析，保证描述符合Java规范
    - 类是否有父类，除了Object之外，所有的类都应该有父类
    - 类的父类是否继承了不允许被继承的类（被final修饰的类）
    - 如果这个类不是 抽象类，是否实现了其父类或接口中要求实现的所有方法。
    - 类的字段，方法是否与父类的产生矛盾。例如方法参数都一样，返回值不同
  - `字节码验证`
    - 通过数据流分析和控制流分析，确定程序语义是合法的，符合逻辑的。
    - 对类的方法体，进行校验分析，保证在运行时不会做出危害虚拟机的行为
    - 保证任意时刻操作数栈的数据类型与指令代码序列都能配合工作，不会出现类似于在操作数栈放了一个int类型的数据，使用时却按照long类型加载到本地变量表中的情况。
    - 保障任何跳转指令都不会跳转到方法体之外的字节码指令上。
  - `符号引用验证`
    - 通过字符串描述的全限定名是否能找到对应的类
    - 符号引用中的类、字段、方法的可访问性是否可被当前类访问

#### 准备（prepare）

- 为`类变量`分配内存并且设置该`类变量`的默认初始值，即`零`值。
- 这里不包含用final修饰的static,因为final在`编译的时候`就会分配了，准备阶段会`显式初始化`。
- 这里不会为`实例变量`分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。

#### 解析(resolve)

- 将常量池内的符号引用转换为直接引用的过程
- 事实上，解析操作往往会伴随着JVM在执行完初始化之后再执行
- 符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java虚拟机规范》的c1ass文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。
- 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中的CONSTANT Class info,CONSTANT Fieldref info,CONSTANT Methodref info

### 初始化阶段（Initialization）

- 初始化阶段就是执行类构造器方法`<clinit>()`的过程。此方法不需定义，是javac编译器自收集类中的所有`类变量`的`赋值动作`和`静态代码块`中的语句合并而来。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220714232129.png)

- 构造器方法中指令按语句在源文件中出现的顺序执行。
- <clinit>()不同于类的构造器。(关联：构造器是虚拟机视角下的`<init>()`)
- 若该类具有父类，JM会保证子类的<clinit>()执行前，父类的<clinit>()已经执行完毕。
- 虚拟机必须保证一个类的<clinit>()方法在多线程下被同步加锁。


## 类加载器分类

### 引导类加载器

使用C/C++编写，

### 自定义类加载器

使用Java语言编写，派生自`ClassLoader`的类加载器都划分为自定义类加载器

#### 扩展类加载器

#### 

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




# 本地方法栈（Native Method Stack）



# 堆(Heap)

# 方法区（Method Area）

# 执行引擎


