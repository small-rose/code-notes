---
layout: default
title: 装饰模式
nav_order: 40
parent: 结构型模式
grand_parent: 设计模式（Design Pattern）
---

# 定义

{: .note-title}
> 装饰模式
> 
> 装饰是一种`结构型设计模式`，允许你通过将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为。

# 结构

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221220164633.png)

- `部件（Component）`声明封装器和被封装对象的公用接口。
- `具体部件（Concrete Component）`类是被封装对象所属的类。它定义了`基础行为`，但装饰类可以改变这些行为。
- `基础装饰（Base Decorator）`类拥有一个指向被封装对象的引用成员变量。该变量的类型应当被声明为通用部件接口，这样它就可以引用具体的部件和装饰。装饰基类会将所有操作委派给被封装的对象。
  - `基础装饰（Base Decorator）`继承`部件（Component）`保证了装饰类依然属于是部件
  - `基础装饰（Base Decorator）`聚合`部件（Component）`保证了装饰类可以在原部件基础上进行装饰
- `具体装饰类（Concrete Decorators）`定义了可动态添加到部件的额外行为。具体装饰类会重写装饰基类的方法，并在调用父类方法之前或之后进行额外的行为。
- `客户端（Client）`可以使用多层装饰来封装部件，只要它能使用通用接口与所有对象互动即可。


# 使用场景

- 对某个类增加新功能，不影响原来的类

