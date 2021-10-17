---
layout: docs 
title: Vue3 
nav_order: 60 
latex: true
---

# 概述

Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的`渐进式框架`。与其它大型框架不同的是， Vue 可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。
另一方面，当与`现代化的工具链`以及各种`支持类库`结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。

# 案例说明

本博客一般情况下`博客文档`和`相关源代码`是在同一目录下的。

Vue项目实际开发环境以`npm`或`CLI`创建项目，并使用`单文档组件`
。这将到在jekyll本地编译速度严重降低，因此，跟单文档组件相关的案例单独新建工程，[案例地址](https://github.com/guosonglu/vue-demo)

# Vue安装

最新版本：

![](https://img.shields.io/npm/v/vue/next.svg)

[版本更新说明](https://github.com/vuejs/vue-next/blob/master/CHANGELOG.md)

## CDN包的形式导入

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

## npm 安装

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

[Vite](https://github.com/vitejs/vite)是一个 web 开发构建工具，由于其原生 ES 模块导入方式，可以实现闪电般的冷服务器启动。 通过在终端中运行以下命令，可以使用 Vite 快速构建 Vue 项目

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

-
vue3.0得下[beta版](https://chrome.google.com/webstore/detail/vuejs-devtools/ljjemllljcmogpfapbkkighbhhppjdbg/related)，否则没法用

# 创建应用实例并指定根组件

## 根组件采用html标签

实际开发场景中，这种方式不常用。一般采用`单文件组件`作为根组件

[示例演示](helloworld.html)

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

<script src="js/vue.global.js"></script>
<script>
    const RootComponent = {
        /* 选项 */
        data() {
            return {
                counter: 10
            }
        }
    }
    //创建应用，并指定根组件
    const app = Vue.createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```

## 根组件采用单文件组件

vue单文件组件需要借助`compiler-sfc`进行预编译，这需要`npm`环境,[相关说明](#案例说明)

- 使用Vue CLI创建项目
- 创建单文档根组件App.vue（自动生成）

```vue

<template>
  <div>{{ title }}</div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      title: "hello world"
    }
  }
}
</script>

<style>

</style>
```

- 在main.js中创建应用实例，并指定根组件

```js
import {createApp} from 'vue'
import App from './App.vue'

createApp(App).mount('#app')
```

# 模板语法

Vue.js 使用了基于 HTML 的模板语法，允许开发者声明式地将 DOM 绑定至底层组件实例的数据。所有 Vue.js 的模板都是合法的 HTML，所以能被遵循规范的浏览器和 HTML 解析器解析。

在底层的实现上，Vue 将模板编译成虚拟 DOM 渲染函数。结合响应性系统，Vue 能够智能地计算出最少需要重新渲染多少组件，并把 DOM 操作次数减到最少。

如果你熟悉虚拟 DOM 并且偏爱 JavaScript 的原始力量，你也可以不用模板，`直接写渲染 (render) 函数`，使用可选的 JSX 语法。

## 插值

### 文本

使用`双大括号`进行文本插值

使用`v-once`，执行一次性地插值，当数据改变时，插值处的内容不会更新。

使用`v-html`插值将输出`html代码`，而不是`普通文本`。

文本插值支持JavaScript表达式

[示例：](interpolations-text.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>插值文本</title>
</head>
<body>

<div id="app">
    <div>文本插值:{{msg}}</div>
    <div v-once>这个将不会改变:{% raw %} {{msg}}{% endraw %}</div>
    <button @click="msg += 1">改变msg的值</button>

    <p>使用普通文本插值:{% raw %} {{ rawHtml }}{% endraw %} </p>
    <p>使用v-html文本插值: <span v-html="rawHtml"></span></p>

    <div>文本插值支持JavaScript 表达式：{% raw %} {{msg + 1}}{% endraw %}</div>
</div>

<script src="js/vue.global.js"></script>
<script>

    const RootComponent = {
        /* 选项 */
        data() {
            return {
                msg: 10,
                rawHtml: '<span style="color: red">This should be red.</span>'
            }
        }
    }

    //创建应用，并指定根组件
    const app = Vue.createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```

### 属性

使用`v-bind`进行属性插值

`v-bind`可以缩写为`:`

属性插值支持JavaScript表达式

[示例：](interpolations-attribute.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>属性插值</title>
    <style>
        .blue {
            color: #0000FF;
        }

        .darkblue {
            color: #00008B;
        }
    </style>
</head>
<body>

<div id="app">
    <div v-bind:class="color">v-bind属性插值</div>
    <div :class="color">v-bind省略写法:</div>
    <div :class="'dark'+color">支持javascript表达式</div>
</div>

<script src="js/vue.global.js"></script>
<script>
    const RootComponent = {
        /* 选项 */
        data() {
            return {
                color: "blue"
            }
        }
    }
    //创建应用，并指定根组件
    const app = Vue.createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```

## 指令

指令(Directives)是带有 `v-`前缀的特殊attribute。

### v-bind

属性绑定

参考上面的[属性插值](#属性)

- 格式：
    - `v-bind:属性.修饰符='单个JavaScript 表达式'`
    - `v-bind:[动态属性].修饰符='单个JavaScript 表达式'`
    - `:属性.修饰符='单个JavaScript 表达式'`
    - `:[动态属性].修饰符='单个JavaScript 表达式'`

### v-on

用于监听 DOM 事件

- 格式：
    - `v-on:事件名.修饰符='单个JavaScript 表达式'`
    - `v-bind:[动态事件名].修饰符='单个JavaScript 表达式'`
    - `@事件名.修饰符='单个JavaScript 表达式'`
    - `@[动态事件名].修饰符='单个JavaScript 表达式'`

# 组件选项

## data()

是一个函数

创建组件实例时调用该函数，以`$data`的形式存储在组件实例中, 为方便起见，该对象的任何`顶级property`也直接通过组件实例暴露出来

假设现在组件有一个变量counter：

```javascript
data()
{
    return {
        counter: 10
    }
}
```

我们打印组件,的确发现在`$data`下和`顶级property`下都有counter变量：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211016163748.png)

## methods

我们用`methods`选项向组件实例添加方法，它应该是一个包含所需方法的对象：

```javascript
const app = Vue.createApp({
  data() {
    return { count: 4 }
  },
  methods: {
    increment() {
      // `this` 指向该组件实例
      this.count++
    }
  }
})

const vm = app.mount('#app')

console.log(vm.count) // => 4

vm.increment()

console.log(vm.count) // => 5
```

- 可以在模板支持`JavaScript表达式`的任何地方调用方法

Vue没有内置支持防抖和节流，但可以使用[Lodash](https://lodash.com/)等库来实现。如果某个组件仅使用一次，可以在methods中直接应用防抖。

但是，这种方法对于可复用组件有潜在的问题，因为它们都共享相同的防抖函数。为了使组件实例彼此独立，可以在生命周期钩子的 created 里添加该防抖函数。

## computed

模板内的表达式的表达式不应该特别复杂，对于任何包含响应式数据的复杂逻辑，你都应该使用`计算属性`。

`computed`与`methods`相比：
- 计算属性是基于它们的响应依赖关系缓存的,`计算属性`只在相关响应式依赖发生改变时它们才会重新求值。
这意味着绑定的`data`不发生改变，多次访问`计算属性`。会立刻返回计算属性的结构，而不必再次计算函数
- 假设我们有一个性能开销比较大的计算属性 list，它需要遍历一个巨大的数组并做大量的计算。然后我们可能有其他的计算属性依赖于 list。如果没有缓存，我们将不可避免的多次执行 list 的 getter！如果你不希望有缓存，请用 method 来替代。

计算属性默认只有 `getter`，不过在需要时你也可以提供一个 `setter`：

```javascript
// ...
computed: {
  fullName: {
    // getter
    get() {
      return this.firstName + ' ' + this.lastName
    },
    // setter
    set(newValue) {
      const names = newValue.split(' ')
      this.firstName = names[0]
      this.lastName = names[names.length - 1]
    }
  }
}
// ...
```

现在再运行 `vm.fullName = 'John Doe'` 时，`setter` 会被调用，`vm.firstName` 和 `vm.lastName` 也会相应地被更新。

这个`setter`个人感觉没啥用，不如直接使用方法

# 组件




