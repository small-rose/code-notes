---
layout: docs 
title: 算法分析 
nav_order: 1 
parent: 数据结构与算法 
latex: true
---

# 数学基础

## 定义

### $T(x)=O(f(x))$

表示$f(x)$的增长率大于或等于$T(x)$的增长率

存在 `正常数`$c$和$n_0,$当$x\geqslant n_0$时$T(x)\leqslant cf(x)$.

`举例说明：`

假设$T(x)=100x^2$ $f(x)=x^3$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291442694.gif)

当$n_0=100$,$c=1$ 时满足 $T(x)\leqslant cf(x)$

或者当$n_0=50$,$c=2$ 时满足 $T(x)\leqslant cf(x)$

所以可以表示为 $T(x)=O(f(x))$

### $T(x)=\Omega(f(x))$

表示$f(x)$的增长率小于或等于$T(x)$的增长率

存在 `正常数`$c$和$n_0,$当$x\geqslant n_0$时$T(x)\geqslant cf(x)$.

`举例说明：`

假设$T(x)=x^3$ $f(x)=100x^2$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291442694.gif)

当$n_0=100$,$c=1$ 时满足 $T(x)\geqslant cf(x)$

或者当$n_0=50$,$c=2$ 时满足 $T(x)\geqslant cf(x)$

所以可以表示为 $T(x)=\Omega(f(x))$

### $T(x)=\Theta(f(x))$

表示$f(x)$的增长率等于$T(x)$的增长率

当且仅当$T(x)=O(f(x))$和$T(x)=\Omega(f(x))$分别成立，`这里不是同时成立，是分别成立，两个没办法同时成立`

`举例说明：`

假设$T(x)=x$ $f(x)=x+20$

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291546016.gif)

当$n_0$为任何值，$c=1$时，$T(x)=O(f(x))$成立

当$n_0=20$，$c=\frac 1 2$时，$T(x)=\Omega(f(x))$成立

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109291553170.gif)

因此$T(x)=\Theta(f(x))$

### $T(x)=o(fx)$

表示$f(x)$的增长率大于$T(x)$的增长率，`不包含等于的情况`

即$T(x)=O(f(x))$且$T(x)\neq\Theta(f(x))$

## 法则

- 如果$T_1(x)=O(f_1(x))$,$T_2(x)=O(f_2(x))$
  - $T_1(x)+T_2(x)=O(f_1(x)+f_2(x))$
  - $T_1(x)+T_2(x)=\max(O(f_1(x))+O(f_2(x)))$
  - $T_1(x)\times T_2(x)=O(f_1(x)\times f_2(x))$
- 如果$T(x)$是一个$k$次多项式
  - $T(x)=\Theta (x^k)$
- 对于任意常数$k$,$\log^k x=O(x)$.对数增长非常缓慢

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
