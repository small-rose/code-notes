---
layout: default
title: 原型
nav_order: 50
parent: 创建型模式
grand_parent: 设计模式（Design Pattern）
---

# 定义

{: .new-title}
> 原型
> 
> `原型`是一种创建型设计模式，使你能够复制已有对象，而又无需使代码依赖它们所属的类。


# 结构

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230208110242.png)

1. `原型（Prototype）`接口将对克隆方法进行声明。在绝大多数情况下，其中只会有一个名为clone克隆的方法。
2. `具体原型（ConcretePrototype）`类将实现克隆方法。除了将原始对象的数据复制到克隆体中之外，该方法有时还需处理克隆过程中的极端情况，例如克隆关联对象和梳理递归依赖等等。
3. `客户端（Client）`可以复制实现了原型接口的任何对象。


