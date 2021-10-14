---
layout: docs
title: Vue
nav_order: 60
---

# 概述

Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的`渐进式框架`。与其它大型框架不同的是，
Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。
另一方面，当与`现代化的工具链`以及各种`支持类库`结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。

# 安装

最新版本：

![](https://img.shields.io/npm/v/vue/next.svg)

[版本更新说明](https://github.com/vuejs/vue-next/blob/master/CHANGELOG.md)

##  CDN包的形式导入

对于制作原型或学习，你可以这样使用最新版本：

```html
<script src="https://unpkg.com/vue@next"></script>
```

对于生产环境，我们推荐链接到一个明确的版本号和构建文件，以避免新版本造成的不可预期的破坏。

这种方法idea敲代码没提示，不推荐

## 下载JavaScript文件并自行托管

[下载地址1](https://cdn.jsdelivr.net/npm/vue@next/dist/)

[下载地址2](https://unpkg.com/browse/vue@3.2.20/dist/)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110141527081.png)

这里面有很多文件，主要分为一下两种：
- vue(.runtime).global(.prod).js
  - 通过浏览器中的 <script src="..."> 直接使用，暴露 Vue 全局，相当于一股脑的导入所有的模块
  - vue.global.js：包含`编译器`和`运行时`的完整构建版本，因此它支持动态编译[模板](#模板语法)
  - vue.runtime.global.js: 只有`运行时`，需要在构建步骤期间预编译[模板](#模板语法)
  - 内联所有 Vue 核心内部包
  - 包含硬编码的 prod/dev 分支
- vue(.runtime).esm-bundler.js
  - 用于通过原生 ES 模块导入使用 (在浏览器中通过 `<script type="module">` 来使用)

##  npm 安装

## 官方的CLI构建

# Vue Devtools

在使用 Vue 时，我们推荐在你的浏览器上安装`Vue Devtools`，它允许你在一个更友好的界面中审查和调试 Vue 应用。

- vue3.0得下[beta版](https://chrome.google.com/webstore/detail/vuejs-devtools/ljjemllljcmogpfapbkkighbhhppjdbg/related)，否则没法用

# 模板语法
