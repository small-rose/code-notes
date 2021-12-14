---
layout: default
title: 数据结构
nav_order: 1
parent: 数据结构与算法
latex: true
---

# 数学基础

## 定义



## 常见函数增长率

| 增长率递增排序 | 名称       |
| -------------- | ---------- |
| $c$          | 常数       |
| $\log x$     | 对数       |
| $\sqrt x$    | 根号       |
| $\log^2 x$   | 对数的平方 |
| $x$          | 线性       |
| $x\log x$    |            |
| $x^2$        | 二次方     |
| $x^3$        | 三次方     |
| $2^x$        | 指数       |

# 运行时间计算

为了简化分析，做以下约定：
- 抛弃一些前导的常数
- 抛弃低阶项
- 要做的就是计算大$O$运行时间

由于大$O$是一个上界，因此不能低估程序的运行时间。

实际上，分析的结果为程序在`一定的时间范围内能够终止运行`提供了保障。`程序可以提前结束，但绝不可能错后`


## 法则

### for循环

运行时间=内部运行时间$\times $迭代次数。

运行时间为：$O(N)$

### 嵌套for循环

从里向外分析这些循环。在一组嵌套循环内部的一条语句总的运行时间=该语句运行时间$\times$所有for循环大小的乘积

n为嵌套层数，运行时间为：$O(N^n)$

### 顺序语句

各个部分运行时间的最大值。

```java
class Demo {
  public static void main(String[] args) {
    for (int i = 0; i < n; i++) {
        a[i] = 0;
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
          a[i] += a[j] + i + j;
      }
    }
  }
}
```

上面这段代码，先花费$O(N)$,接着是$O(N^2)$，因此总量是:$O(N^2)$

### if-else语句

各个部分运行时间的最大值。


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
