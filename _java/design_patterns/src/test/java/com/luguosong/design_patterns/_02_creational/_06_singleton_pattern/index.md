---
layout: default
title: 单例
nav_order: 60
parent: 创建型模式
grand_parent: 设计模式（Design Pattern）
---

# 定义

{: .new-title}
> 单例
> 
> `单例`是一种创建型设计模式，让你能够保证一个类只有一个实例，并提供一个访问该实例的全局节点。

# 结构

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230208174329.png)

1. `单例（Singleton）`类声明了一个名为getInstance获取实例的静态方法来返回其所属类的一个相同实例。
