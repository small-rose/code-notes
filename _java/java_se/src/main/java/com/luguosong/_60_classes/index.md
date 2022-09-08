---
layout: default
title: 类
nav_order: 60
parent: JavaSE
---

# 声明类

## 组成

类声明包含以下组件：

- 修饰符
- 类名
- 如果有父类，要在父类名称前添加`extends`关键字，一个类只能继承一个父类
- 如果实现一系列接口（用逗号分隔），要在接口前添加`implements`关键字，一个类可以实现多个接口)。
- 类体，花括号之间的部分

# 声明字段

## 组成

字段由下面三部分构成：

- 零个或多个修饰符，如public或private。
- 字段类型。
- 字段名。

## 默认值

字段声明时，不一定总要赋初值。编译器会为那些已经声明但未初始化的字段赋予一个`默认值`。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220908093124.png)

# 声明方法

## 组成

- 修饰符
- 返回类型
- 方法名
- 圆括号内的参数列表
- 异常列表
- 花括号内的方法体

## 方法签名

方法声明中的`方法名`和`参数类型`构成`方法签名`。

## 方法重载

Java可以用不同的`方法签名`来区分方法。也就是说，如果类内的方法的`参数列表`不同，则可以使用`同一个方法名`。

# 构造器

## 概述

每个类都包含构造器，调用这些构造器可以从类创建对象。构造器声明与方法声明有点像，只是构造器`名称与类名一样`，且`没有返回类型`
。

要创建对象，使用`new`运算符调用构造器,为对象创建内存空间并初始化对象的字段。

类不一定要提供构造器，但如果提供构造器，就要特别小心。编译器会为`没有构造器`的任意类`自动创建`
一个无参数的默认构造器。该默认构造器会`调用父类`的`无参数构造器`
。在这种情形下，如果超类没有无参数构造器，编译器就会报错，所以必须先验证超类是否包含无参数构造器。如果一个类没有显式的超类，那么它有一个隐式的Object超类（包含无参数构造器)
。

# 参数传递

## 形参类型

形参可以是基本数据类型，也可以是引用数据类型

## 可变参数

可以想方法传递任意数目的值

要在最后一个参数的类型后面添加省略号`...`，然后添加空格和参数名

处理可变参数与数组相似

调用该方法时可以携带`数组`，也可以携带`实参序列`

```java
class Demo {
    public Polygon polygonFrom(Point... corners) {
        int numberOfSides = corners.length;
        double squareOfSide1, lengthOfSide1;
        squareOfSide1 = (corners[1].x - corners[0].x)
                * (corners[1].x - corners[0].x)
                + (corners[1].y - corners[0].y)
                * (corners[1].y - corners[0].y);
        lengthOfSide1 = Math.sqrt(squareOfSide1);

        // more method body code follows that creates and returns a 
        // polygon connecting the Points
    }
}
```

## 形参名

在作用域内，形参名必须唯一。不能与`其它形参名`或`局部变量`同名。

形参名可以与`字段`同名,与字段同名会`覆盖`字段。

## 传递基本数据类型实参

基本数据类型的实参通过`传值方式`传入方法。

这就意味着，对参数值的任意改变都只能发生在方法的作用域内。方法返回时，形参失效，对形参的改变也将丢失。

## 传递引用数据类型实参

引用数据类型（对象等）也是通过`传值方式`传入方法。

这就是说，当方法返回时，输入的引用仍会引用相同的对象。但是，其值可以在方法内改变。
