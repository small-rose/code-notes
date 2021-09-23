---
layout: docs
title: LaTeX编写数学公式
nav_order: 200
parent: ide和环境配置
latex: true
---
# 简介

[LaTeX](https://www.latex-project.org/)（/ˈlɑːtɛx/，常被读作/ˈlɑːtɛk/或/ˈleɪtɛk/，
写作“LATEX”），是一种基于 `TEX`的排版系统，由美国计算机科学家莱斯利·兰伯特在20世纪80年代初期开发，
利用这种格式系统的处理，即使用户没有排版和程序设计的知识也可以充分发挥由TEX所提供的强大功能，
不必一一亲自去设计或校对，能在几天，甚至几小时内生成很多具有书籍质量的印刷品。
对于生成 `复杂表格`和 `数学公式`，这一点表现得尤为突出。因此它非常适用于生成高印刷质量的科技和 `数学、 物理文档`。这个系统同样适用于生成从简单的信件到完整书籍的所有其他种类的文档。
LaTeX使用TeX作为它的格式化引擎

# 数学公式

## 希腊字母

| 小写希腊字母    | LaTeX       | 大写希腊字母 | LaTex    |
| :-------------- | :---------- | ------------ | -------- |
| $\alpha$      | \alpha      | $A$        | A        |
| $\beta$       | \beta       | $B$        | B        |
| $\gamma$      | \gamma      | $\Gamma$   | \Gamma   |
| $\delta$      | \delta      | $\Delta$   | \Delta   |
| $\epsilon$    | \epsilon    | $E$        | E        |
| $\varepsilon$ | \varepsilon | $E$        | E        |
| $\zeta$       | \zeta       | $Z$        | Z        |
| $\eta$        | \eta        | $H$        | H        |
| $\theta$      | \theta      | $\Theta$   | \Theta   |
| $\vartheta$   | \vartheta   | $\Theta$   | \Theta   |
| $\iota$       | \iota       | $I$        | I        |
| $\kappa$      | \kappa      | $K$        | K        |
| $\lambda$     | \lambda     | $\Lambda$  | \Lambda  |
| $\mu$         | \mu         | $M$        | M        |
| $\nu$         | \nu         | $N$        | N        |
| $\xi$         | \xi         | $\Xi$      | \Xi      |
| $o$           | o           | $O$        | O        |
| $\pi$         | \pi         | $\Pi$      | \Pi      |
| $\varpi$      | \varpi      | $\Pi$      | \Pi      |
| $\rho$        | \rho        | $P$        | P        |
| $\varrho$     | \varrho     | $P$        | P        |
| $\sigma$      | \sigma      | $\Sigma$   | \Sigma   |
| $\varsigma$   | \varsigma   | $\Sigma$   | \Sigma   |
| $\tau$        | \tau        | $T$        | T        |
| $\upsilon$    | \upsilon    | $\Upsilon$ | \Upsilon |
| $\phi$        | \phi        | $\Phi$     | \Phi     |
| $\varphi$     | \varphi     | $\Phi$     | \Phi     |
| $\chi$        | \chi        | $X$        | X        |
| $\psi$        | \psi        | $\Psi$     | \Psi     |
| $\omega$      | \omega      | $\Omega$   | \Omega   |

## 上下标

- 上标

| 上标          | LaTeX   |
| :------------ | :------ |
| $ a^2 $     | a^2     |
| $ x^{y+z} $ | x^{y+z} |

- 下标

| 下标          | LaTeX   |
| :------------ | :------ |
| $ a_2 $     | a_2     |
| $ x_{y+z} $ | x_{y+z} |

## log对数

| 对数            | LaTex       |
| --------------- | ----------- |
| $\log e$      | \log e      |
| $\log_{10} f$ | \log_{10} f |

## 分数

| 分数                         | LaTex                     |
| ---------------------------- | ------------------------- |
| $\frac a b$                | \frac a b                 |
| $\frac {a+b} {c+d}$        | \frac {a+b} {c+d}         |
| $\tfrac a {c+\tfrac a e }$ | \tfrac a {c+\\tfrac a e } |
| $\cfrac a {c+\cfrac a e }$ | \cfrac a {c+\\cfrac a e } |

## 极限

| 极限                                     | LaTex                                  |
| ---------------------------------------- | -------------------------------------- |
| $\lim_{x \to \infty} \frac{1}{n(n+1)}$ | \lim_{x\\to \\infty} \frac{1}{n(n+1)} |

## 求和

| 求和                 | LaTex            |
| -------------------- | ---------------- |
| $\sum_{k=1}^N k^2$ | \sum_{k=1}^N k^2 |
