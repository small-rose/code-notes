---
layout: default
title: 枚举
nav_order: 150
parent: JavaSE
---

# 性质

- 枚举的本质是类，所有枚举类型最终都隐式继承自`java.lang.Enum`
- 枚举定义完常量后，还可以定义成员变量和方法等内容
- 枚举的构造方法必须是`无修饰符`或者`private`,这意味着我无法在外部手动通过`new`创建枚举对象。
