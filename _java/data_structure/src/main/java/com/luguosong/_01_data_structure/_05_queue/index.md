---
layout: default
title: 队列
nav_order: 50
parent: 数据结构
grand_parent: 数据结构和算法
---

# 概述

`队列`是一种特殊的线性表，只能在`头尾两端`进行操作。

- `队尾（rear）`:只能从队尾添加元素，一般叫做enQueue,入队
- `对头（front）`:只能从队头移除元素，一般叫做deQueue,出队

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221029111250.png)


# 官方实现

JDK中`java.util.LinkedList`实现了`java.util.Queue`接口。


