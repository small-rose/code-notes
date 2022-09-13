---
layout: default
title: 变量
nav_order: 20
parent: JavaSE
---

# 变量分类

- 实例变量（非静态字段）
- 类变量（静态字段）
- 局部变量
- 参数

# 变量命名

- 变量区分大小写
- 可以是任意合法标识符
- 命名时不支持空格
- 可以是`字母`、`$`、`_`开头，通常采用`字母`开头
- 命名最好使用完整的单词
- 第一个单词首字母小写，后续单词首字母大写

# 基本数据类型

Java是`静态类型`的程序语言，所有变量在使用前必须先声明。

Java支持`八种`基本数据类型

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220830111703.png)

# 字

## 概述

初始化基本类型的变量时不能使用关键字`new`
。基本类型是内嵌在Java语言中的特殊数据类型，它们不需要用类来创建。`字（或文字，literal)`是用源代码表示的固定值。字不需要计算，直接由代码表示。

## 整型字

整型字默认就是`int类型`,`int类型`的整型字不能超出int的取值范围。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220908101743.png)

以`L`或`l`结尾的整型字表示`long类型`

```java
class Demo {
    /**
     * 整型字，默认就是int类型
     */
    int i = 2147483647;

    /**
     * 整形字结尾加上L，表示long类型
     */
    long l = 9223372036854775807L;
}
```

byte、short、int和long类型的值可以由`int类型字`创建,long类型由`long类型字`创建

```java
class Demo {
    /**
     * int类型的字，可以自动向上转型为long数据类型
     */
    long l2 = 2147483647;

    /**
     * 数字之间可以采用下划线分隔
     */
    int i2 = 2_147_483_647;


    /**
     * 127是int类型的字，int类型的字可以创建byte类型变量
     * 但要注意，字的范围必须在byte类型范围内，否则得强制类型转换
     */
    byte b1 = 'b';
    byte b2 = 127;
    byte b3 = (byte) 128; //128已经超出了byte的取值范围，需要进行强制类型转换

    /**
     * 32767是int类型的字,int类型的字可以创建short类型变量
     * 但要注意，字的范围必须在short类型范围内，否则得强制类型转换
     */
    short s1 = 32767;
    short s2 = (short) 32768;
}
```

整型字可以由不同进制表示：

```java
class Demo {
    /**
     * 数字 26，十进制
     */
    int decVal = 26;
    /**
     * 数字 26，十六进制
     */
    int hexVal = 0x1a;
    /**
     * 数字 26，二进制
     */
    int binVal = 0b11010;
}
```

## 浮点字

```java
class Demo {
    /**
     * 浮点字默认表示double类型
     */
    double d1 = 19.1;

    /**
     * 如果要声明float类型，可以在浮点字后面加f
     * 或者进行强制类型转换
     */
    float f = 19.1f;
    float f2 = (float) 19.1;

    /**
     * 科学计数法浮点字
     */
    double d2 = 1.234e2; //123.4
}
```

## 字符字

char和String类型的字可以包含任意`Unicode(UTF-16)`字符

```java
class Demo {
    /**
     * 字符字
     */
    char c1 = 'c'; //字符
    char c2 = '\u0063'; //转义字符
    char c3 = 99; //int

    /**
     * 字符串字
     */
    String string1 = "ccc";
}
```

## null

可以用作任意`引用类型`的值

## 类字

`类名.class`

表示类型本身的对象

