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

# 包装类

{: .warning-title}
> 基本数据类型存在的问题：
> 
> - 基本数据类型无法表示null（空）
> - 不能调用方法
> - 参数如果是`引用类型`，无法传递`基本数据类型`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221129091642.png)

{: .note-title}
> 自动装箱、拆箱
> 
> `自动装箱`：Java编译器会自动调用valueOf方法。将基本数据类型转为包装类
> 
> `自动拆箱`：Java编译器会自动调用xxxValue方法，将包装类转为基本数据类型

{: .note-title}
> 包装类的判等
> 
> 不要使用`==`、`!=`。应该使用`equals`
> 
> 包装类内部存在一个内部类`缓存区`，当值在一定范围内不会新建对象，而是从`缓存区`中取
> 
> 当包装类在缓存区范围内，`==`比较结果与`equals`一致。
> 
> 当超过缓存范围，因为包装类为引用类型，`==`比较的是引用地址值，即使值相同，`==`的比较结果也可能不同。


# 原码、反码、补码

整数类型实际存储以`补码`方式存储

```java
/**
 * 原码、反码、补码
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class BinaryDemo {
    public static void main(String[] args) {
        //源码：10000000 00000000 00000000 00000101
        //反码：11111111 11111111 11111111 11111010
        //补码：11111111 11111111 11111111 11111011
        System.out.println(Integer.toBinaryString(-5));
        //源码：10000000 00000000 00000000 00000100
        //反码：11111111 11111111 11111111 11111011
        //补码：11111111 11111111 11111111 11111100
        System.out.println(Integer.toBinaryString(-4));
        //源码：10000000 00000000 00000000 00000011
        //反码：11111111 11111111 11111111 11111100
        //补码：11111111 11111111 11111111 11111101
        System.out.println(Integer.toBinaryString(-3));
        //源码：10000000 00000000 00000000 00000010
        //反码：11111111 11111111 11111111 11111101
        //补码：11111111 11111111 11111111 11111110
        System.out.println(Integer.toBinaryString(-2));
        //源码：10000000 00000000 00000000 00000001
        //反码：11111111 11111111 11111111 11111110
        //补码：11111111 11111111 11111111 11111111
        System.out.println(Integer.toBinaryString(-1));
        //源码：00000000 00000000 00000000 00000000
        //反码：00000000 00000000 00000000 00000000
        //补码：00000000 00000000 00000000 00000000
        System.out.println(Integer.toBinaryString(0));
        //源码：00000000 00000000 00000000 00000001
        //反码：00000000 00000000 00000000 00000001
        //补码：00000000 00000000 00000000 00000001
        System.out.println(Integer.toBinaryString(1));
        //源码：00000000 00000000 00000000 00000010
        //反码：00000000 00000000 00000000 00000010
        //补码：00000000 00000000 00000000 00000010
        System.out.println(Integer.toBinaryString(2));
        //源码：00000000 00000000 00000000 00000011
        //反码：00000000 00000000 00000000 00000011
        //补码：00000000 00000000 00000000 00000011
        System.out.println(Integer.toBinaryString(3));
        //源码：00000000 00000000 00000000 00000100
        //反码：00000000 00000000 00000000 00000100
        //补码：00000000 00000000 00000000 00000100
        System.out.println(Integer.toBinaryString(4));
        //源码：00000000 00000000 00000000 00000101
        //反码：00000000 00000000 00000000 00000101
        //补码：00000000 00000000 00000000 00000101
        System.out.println(Integer.toBinaryString(5));
    }
}
```

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


