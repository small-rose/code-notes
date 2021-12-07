---
layout: docs
title: 数据结构和算法
nav_order: 2
latex: true
---



[邓俊辉老师课程](https://dsa.cs.tsinghua.edu.cn/~deng/ds/index.htm)，虽然是C++版本的，
问题不大。

视频教程：
[数据结构(上)](https://www.xuetangx.com/course/THU08091000384/7755489?channel=i.area.learn_title)
[数据结构(下)](https://www.xuetangx.com/course/THU08091002048/7755080?channel=i.area.learn_title)

也可以结合[邓俊辉老师Java版本的书](https://dsa.cs.tsinghua.edu.cn/~deng/ds/dsaj/dsaj.pdf)一起看

# 算法概述

`算法`是指基于特定的计算模型，旨在解决某一信息处理问题而设计的一个指令序列。

- 判断一个算法是否是好算法，最重要的一条性质是`效率`
  - `速度尽可能快`
  - `存储空间尽可能少`

## 算法分析

### 复杂度度量

#### 时间复杂度

随着输入规模的扩大，算法的执行时间将如何增长。

执行时间的这一变化趋势可表示为`输入规模`的`一个函数`，称作该算法的`时间复杂度`（time complexity）。
具体地，特定算法处理规模为n的问题所需的时间可记作`T(n)`。

以上定义的T(n)并不明确。为此需要再做一次简化，即从保守估计的角度出发，在规模为n的所有输入中选择执行时间`最长者`作为T(n)，
并以T(n)度量该算法的时间复杂度。

#### 渐进复杂度

- $T(x)=O(f(x))$

表示$f(x)$的增长率大于或等于$T(x)$的增长率

⭐O描述了$T(x)$时间复杂度的`一个上界`。对象$T(x)$时间复杂度的`悲观估计`

存在 `正常数`$c$和$n_0,$当$x\geqslant n_0$时$T(x)\leqslant cf(x)$.

`举例说明：`

假设$T(x)=100x^2$ $f(x)=x^3$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291442694.gif)

当$n_0=100$,$c=1$ 时满足 $T(x)\leqslant cf(x)$

或者当$n_0=50$,$c=2$ 时满足 $T(x)\leqslant cf(x)$

所以可以表示为 $T(x)=O(f(x))$

***

- $T(x)=\Omega(f(x))$

表示$f(x)$的增长率小于或等于$T(x)$的增长率

存在 `正常数`$c$和$n_0,$当$x\geqslant n_0$时$T(x)\geqslant cf(x)$.

`举例说明：`

假设$T(x)=x^3$ $f(x)=100x^2$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291442694.gif)

当$n_0=100$,$c=1$ 时满足 $T(x)\geqslant cf(x)$

或者当$n_0=50$,$c=2$ 时满足 $T(x)\geqslant cf(x)$

所以可以表示为 $T(x)=\Omega(f(x))$

***

- `$T(x)=\Theta(f(x))$`

表示$f(x)$的增长率等于$T(x)$的增长率

当且仅当$T(x)=O(f(x))$和$T(x)=\Omega(f(x))$分别成立，`这里不是同时成立，是分别成立，两个没办法同时成立`

`举例说明：`

假设$T(x)=x$ $f(x)=x+20$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291546016.gif)

当$n_0$为任何值，$c=1$时，$T(x)=O(f(x))$成立

当$n_0=20$，$c=\frac 1 2$时，$T(x)=\Omega(f(x))$成立

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291553170.gif)

因此$T(x)=\Theta(f(x))$

***

- $T(x)=o(fx)$

表示$f(x)$的增长率大于$T(x)$的增长率，`不包含等于的情况`

即$T(x)=O(f(x))$且$T(x)\neq\Theta(f(x))$

*** 

- 法则
  - 如果$T_1(x)=O(f_1(x))$,$T_2(x)=O(f_2(x))$
    - $T_1(x)+T_2(x)=O(f_1(x)+f_2(x))$
    - $T_1(x)+T_2(x)=\max(O(f_1(x))+O(f_2(x)))$
    - $T_1(x)\times T_2(x)=O(f_1(x)\times f_2(x))$
  - 如果$T(x)$是一个$k$次多项式
    - $T(x)=\Theta (x^k)$
  - 对于任意常数$k$,$\log^k x=O(x)$.对数增长非常缓慢

#### 空间复杂

很多时候我们都是更多地甚至仅仅关注于算法的`时间复杂度`，而不必对`空间复杂度`做专门的考查。

### 复杂度分析

- 高效解
  - $O(1)$:常数复杂度
  - $O(\log n)$：对数复杂度
- 有效解
  - $O(n)$:线性复杂度
  - $O(n^c)$:多项式复杂度
- 难解
  - $O(2^n)$:指数复杂度


## 迭代与递归

## 主定理

## 动态规划


# 抽象数据类型

# 向量
