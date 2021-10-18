---
layout: docs
title: 数据结构
nav_order: 2
parent: 数据结构与算法
latex: true
---

# 抽象数据类型

`抽象数据类型`（abstract data type,ADT）:带有`一组操作`的一些`对象的集合`

# 表 ADT

定义:形如$A_1,A_2,A_3,A_4,...,A_(N-1)$的结构，这个表的大小是N。

操作：
- printList:打印表
- makeEmpty:清空表
- find:返回某一项首次出现的位置
- insert:从表的某个位置插入
- remove:从表的某个位置删除
- findKth:返回某个位置上的元素


## 数组

对于表的所有这些操作可以通过数组实现。

定义：由相同类型的元素的集合所组成，分配一块连续的内存来存储。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110181055637.png)

操作时间分析：
- printList：$O(N)$
- makeEmpty: $O(1)$
- find:$O(N)$
- insert:$O(N)$
- remove:$O(N)$
- findKth:$O(1)$

## 链表

为了避免`数组`插入和删除的线性开销，我们需要保证`表`可以不连续存储，否则表的每个部分都可能整体移动。

定义：有一系列节点组成，这些节点不必在内存中相连。每一个节点均包含表元素和到包含该元素后继元的节点的`链`（link），
我们称之为next链。最后一个单元的next链引用null。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110181056868.png)

操作时间分析：
- printList：$O(N)$
- makeEmpty: $O(1)$
- find:$O(N)$
- insert:$O(1)$
- remove:$O(1)$
- findKth:$O(N)$

链表的增加和删除花费常量时间，相比数组要少。但是findKth却要花费线性时间，比数组费时。
