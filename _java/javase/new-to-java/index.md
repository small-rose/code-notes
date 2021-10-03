---
layout: docs 
title: Java基础
nav_order: 1 
parent: JavaSE 
latex: true
---

# 概述

## 关于Java

### 作为编程语言

.Java源代码文件通过编译器编译成.class字节码文件。Java虚拟机识别并运行.class文件

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/getStarted-compiler.gif)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/helloWorld.gif)

### 作为平台

平台是程序运行的硬件或软件环境

`Java平台两大组件：`

- Java虚拟机
- Java应用程序接口（API）

# 面向对象的编程概念

## 对象

`两大属性：`

- 字段：它处于什么状态
- 方法：他会发生什么行为

`方法`对对象内部 `字段`进行操作，是对象之间通讯的基本机制。这种隐藏对象内部 `状态`（字段），要求 所有通讯都通过对象 `方法`实现的行为，是面向对象编程的基本原则，称为 `数据封装`

将代码构建成独立软件对象的优点：

- 模块化
- 信息隐蔽
- 代码重用
- 可插拔易于调试

## 类

`类`是创建 `对象`的蓝图，`对象`是 `类`的实例。

`类`中不需要有main方法，因为类并不是一个完整的 `应用程序`。仅仅是程序可能用到的蓝图。

## 继承

`类`可以 `继承`其它类的状态（字段）和行为（方法）

每个类只能有一个 `直接的父类`。子类将会拥有父类的 `字段`和 `方法`

格式为关键字extends+要继承的类名，如下：

```java
class MountainBike extends Bicycle {

}
```

## 接口

对象通过 `方法`与外界沟通，也就是说，`方法`是对象与外界的 `接口`。

`接口`被定义为一组 `方法体为空`的相关方法的集合。

## 包

包是类和接口的 `命名空间`。

# 语言基础

## 变量

变量分类：

- `实例变量`：`非静态字段`。对于每个实例（对象）而言，他们都是唯一的
- `类变量`：`静态字段`。static修饰符声明的字段。不管被实例化多少次，类变量都不会改变。此外，也可以添加关键字final表示永远不会改变。
- `局部变量`：`方法中的临时状态`称为局部变量。局部变量只在方法体中有效。
- `参数`：方法的参数。

### 变量命名

- 区分大小写
- 字母开头
- 不支持空格
- 不能是关键字或保留字
- 驼峰命名法sss
- 如果是常量值，每个字符都大写，下划线分隔。`static final int NUM_GEARS=6`

### 基本数据类型

Java是静态类型的程序语言，所有的变量在使用前都必须先声明类型和名称。

Java有 `8种`基本数据类型

| 名称    | 类型                           | 长度 | 最小值      | 最大值       | 默认值(`只有字段有默认值`) |
| ------- | ------------------------------ | ---- | ----------- | ------------ | ------------------------ |
| byte    | 整数                           | 8    | -128        | 127          | 0                        |
| short   | 整数                           | 16   | -32768      | 32767        | 0                        |
| int     | 整数                           | 32   | $-2^{31}$ | $2^{31}-1$ | 0                        |
| long    | 整数                           | 64   | $-2^{63}$ | $2^{63}-1$ | 0L                       |
| float   | 单精度**IEEE 754**浮点数 | 32   | -           | -            | 0.0f                     |
| double  | 双精度**IEEE 754**浮点数 | 64   | -           | -            | 0.0d                     |
| boolean | 布尔值                         | 1    | -           | -            | false                    |
| char    | 字符                           | 16   | \u0000      | \uffff       | null                     |

- `字面量`（literal）:基本数据类型创建时不需要`new`，基本类型是`内嵌在Java语言中`的特殊数据类型。`字`是用源代码表示的固定值。字不需要计算，直接由代码表示。
    - 整型字面量
        - `26L` 表示long类型
        - `26` 十进制int类型
        - `0x1a` 十六进制int类型
        - `0b11010` 二进制int类型
        - `1234_5678_9012` 可以在数字之间增加下划线增加可读性
    - 浮点字面量
        - `123.4` 不加任何后缀`默认`表示double类型
        - `123.4d` double类型
        - `1.234e2` 科学计数标记
        - `123.4f` float类型
    - 类字面量:`类.class`,表示类型本身的对象
        - String.class
    - 字符字面量和字符串字面量
    - null

### 数组

数组是`固定数目`的`单一数据类型`的容器对象。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/objects-tenElementArray.gif)

数组中每个项都称为`元素`（element）。每个元素都用数字`索引`(index)访问，索引从0开始。

#### 数组声明

告诉编译器该变量可以用于保存特定类型的数组：

```java
class Demo {
    //数组声明
    int[] anArray;
}
```

#### 创建数组

分配一定数量特定类型的内存空间的数组，并将该数组赋值给变量：

```java
class Demo {
    //创建数组
    int[] anArray = new int[10];
    //快捷方式定义数组
    int[] anArray = {
            100, 200, 300,
            400, 500, 600
    };
}
```

#### 数组赋值

```java
class Demo {
    int[] anArray = new int[10];

    public static void main(String[] args) {
        //数组赋值
        anArray[0] = 100;
    }
}
```

#### 访问元素

通过数字索引

```java
class Demo {
    int[] anArray = new int[10];

    public static void main(String[] args) {
        anArray[0] = 100;
        //访问数组
        System.out.println(anArray[0]);
    }
}
```

#### 数组的长度

```java
class Demo {
    int[] anArray = new int[10];

    public static void main(String[] args) {
        //数组长度
        anArray.length;
    }
}
```

#### 多维数组

```java
class MultiDimArrayDemo {
    public static void main(String[] args) {
        String[][] names = {
                {"Mr. ", "Mrs. ", "Ms. "},
                {"Smith", "Jones"}
        };
        // Mr. Smith
        System.out.println(names[0][0] + names[1][0]);
        // Ms. Jones
        System.out.println(names[0][2] + names[1][1]);
    }
}
```

#### 数组复制

System类有`arraycopy`方法，用于数组之间数据的高效复制：

```java
class ArrayCopyDemo {
    public static void main(String[] args) {
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto"};

        String[] copyTo = new String[7];
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
    }
}
```

执行结果：

```text
Cappuccino Corretto Cortado Doppio Espresso Frappucino Freddo 
```

#### Arrays类

`java.util.Arrays`提供了一些数组操作方法。

- `copyOfRange()`: 数组复制
- `binarySearch()`: 搜索特定值，返回索引
- `equals()`: 两数组是否相等
- `fill()`: 在数组的每个索引位置填上指定值
- 

```java
class ArraysDemo {
    public static void main(String[] args) {
        String[] copyFrom1 = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo"};
        String[] copyForm2 = {
                "Marocchino", "Ristretto"
        };

        //查找
        System.out.println(Arrays.binarySearch(copyFrom1, "Cortado"));
        //复制
        String[] copyTo = Arrays.copyOfRange(copyFrom1, 2, 9);
        System.out.println(Arrays.toString(copyTo));
        //比较
        System.out.println(Arrays.equals(copyFrom1, copyForm2));
        // 在每个索引位置填上指定值
        Arrays.fill(copyTo, "Cortado");
        System.out.println(Arrays.toString(copyTo));
        //升序排序之顺序排序：
        int[] ints1 = {3, 5, 1, 2};
        Arrays.sort(ints1);
        System.out.println(Arrays.toString(ints1));
        //升序排序之
        int[] ints2 = {30, 50, 10, 20};
        Arrays.parallelSort(ints2);
        System.out.println(Arrays.toString(ints2));
    }
}
```



