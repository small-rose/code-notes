---
layout: default
title: 工厂方法
nav_order: 20
parent: 创建型模式
grand_parent: 设计模式（Design Pattern）
---

# 定义

{: .new-title}
> 工厂方法
> 
> `工厂方法`是一种`创建型设计模式`，其在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型。

# 结构

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221220225026.png)

- `产品（Product）`将会对接口进行声明。对于所有由创建者及其子类构建的对象，这些接口都是通用的。
- `具体产品（Concrete Products）`是产品接口的不同实现。
- `创建者（Creator）`类声明返回产品对象的工厂方法。该方法的返回对象类型必须与产品接口相匹配。
