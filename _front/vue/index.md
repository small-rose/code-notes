---
layout: docs
title: Vue3
nav_order: 60
---

# 概述

Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的`渐进式框架`。与其它大型框架不同的是，
Vue 可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。
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
  - 通过浏览器中的`<script src="...">`直接使用，暴露Vue全局，相当于一股脑的导入所有的模块
  - vue.global.js：包含`编译器`和`运行时`的完整构建版本，因此它支持动态编译[模板](#模板语法)
  - vue.runtime.global.js: 只有`运行时`，需要在构建步骤期间预编译[模板](#模板语法)
  - 内联所有 Vue 核心内部包
  - 包含硬编码的 prod/dev 分支
- vue(.runtime).esm-bundler.js
  - 用于通过原生 ES 模块导入使用 (在浏览器中通过 `<script type="module">` 来使用)

本文使用[vue.esm-browser.js](https://cdn.jsdelivr.net/npm/vue@3.2.20/dist/vue.esm-browser.js)

##  npm 安装

在用Vue构建大型应用时推荐使用`npm`安装.`npm`能很好地和诸如 `webpack` 或 `Rollup` 模块打包器配合使用。

```shell
# 最新稳定版
$ npm install vue@next
```

Vue还提供了编写单文件组件的配套工具。如果你想使用单文件组件，那么你还需要安装`@vue/compiler-sfc`:

```shell
$ npm install -D @vue/compiler-sfc
```

大多数情况下，我们更倾向于使用 [Vue CLI](#官方的cli构建) 来创建一个配置最小化的 webpack 构建版本。

## 官方的CLI构建

[仓库](https://github.com/vuejs/vue-cli)

[文档](https://cli.vuejs.org/zh/)

Vue CLI 是一个基于 Vue.js 进行快速开发的完整系统。提供：
- 通过 `@vue/cli` 实现的交互式的项目脚手架
- 通过 `@vue/cli` + `@vue/cli-service-global` 实现的零配置原型开发。
- 一个运行时依赖 (`@vue/cli-service`)，该依赖：
  - 可升级；
  - 基于 webpack 构建，并带有合理的默认配置；
  - 可以通过项目内的配置文件进行配置；
  - 可以通过插件进行扩展。
- 一个丰富的官方插件集合，集成了前端生态中最好的工具。
- 一套完全图形化的创建和管理 Vue.js 项目的用户界面。

- npm安装 Vue CLI：

```shell
yarn global add @vue/cli
# 或
npm install -g @vue/cli
```

- 创建一个项目：

```shell
vue create 项目名
```

也可以使用idea创建vue项目（`也是基于Vue CLI`）：
- 选择新建模块，选择vue.js模块

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110150940260.png)

- 选择Vue3

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110150937389.png)

## Vite

[Vite](https://github.com/vitejs/vite)是一个 web 开发构建工具，由于其原生 ES 模块导入方式，可以实现闪电般的冷服务器启动。
通过在终端中运行以下命令，可以使用 Vite 快速构建 Vue 项目

```shell
# npm 6.x
$ npm init vite@latest <project-name> --template vue

# npm 7+，需要加上额外的双短横线
$ npm init vite@latest <project-name> -- --template vue

$ cd <project-name>
$ npm install
$ npm run dev
```

# Vue Devtools

在使用 Vue 时，我们推荐在你的浏览器上安装`Vue Devtools`，它允许你在一个更友好的界面中审查和调试 Vue 应用。

- vue3.0得下[beta版](https://chrome.google.com/webstore/detail/vuejs-devtools/ljjemllljcmogpfapbkkighbhhppjdbg/related)，否则没法用

# 创建应用实例并指定根组件

## 根组件采用html标签

实际开发场景中，这种方式不常用。一般采用`单文件组件`作为根组件

[示例演示](./helloworld/helloworld.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建一个应用实例</title>
</head>
<body>

<div id="app">
    {{counter}}
</div>

<script type="module">
    import {createApp} from '../js/vue.esm-browser.js'

    const RootComponent = {
        /* 选项 */
        data() {
            return {
                counter: 10
            }
        }
    }
    const app = createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```

## 根组件采用单文件组件

vue单文件组件需要借助

- 使用Vue CLI创建项目
- 11
