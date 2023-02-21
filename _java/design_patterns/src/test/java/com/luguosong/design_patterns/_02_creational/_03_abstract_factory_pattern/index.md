---
layout: default
title: 抽象方法
nav_order: 30
parent: 创建型模式
grand_parent: 设计模式（Design Pattern）
---

# 定义

{: .new-title}
> 抽象方法
> 
> `抽象工厂`是一种创建型设计模式，它能创建一系列相关的对象，而无需指定其具体类。

# 结构

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230207225426.png)

1. `抽象产品（AbstractProduct）`为构成系列产品的一组不同但相关的产品声明接口。
2. `具体产品（ConcreteProduct）`是抽象产品的多种不同类型实现。所有变体都必须实现相应的抽象产品。
3. `抽象工厂（AbstractFactory）`接口声明了一组创建各种抽象产品的方法。
4. `具体工厂（ConcreteFactory）`实现抽象工厂的构建方法。每个具体工厂都对应特定产品变体，且仅创建此种产品变体。
5. 尽管具体工厂会对具体产品进行初始化，其构建方法签名必须返回相应的抽象产品。这样，使用工厂类的客户端代码就不会与工厂创建的特定产品变体耦合。`客户端（Client）`只需通过抽象接口调用工厂和产品对象，就能与任何具体工厂/产品变体交互。
