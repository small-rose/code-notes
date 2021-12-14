---
layout: default 
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

使用`v-bind`指令进行属性插值，详见[v-bind](#v-bind)

## 指令

指令(Directives)是带有 `v-`前缀的特殊attribute。

### v-bind

属性绑定

`v-bind`可以缩写为`:`

属性插值支持JavaScript表达式

- 格式：
  - `v-bind:属性.修饰符='单个JavaScript 表达式'`
  - `v-bind:[动态属性].修饰符='单个JavaScript 表达式'`
  - `:属性.修饰符='单个JavaScript 表达式'`
  - `:[动态属性].修饰符='单个JavaScript 表达式'`

class属性和style属性指令增强：

- 对象语法
- 数组语法

[示例：](v-bind.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>v-bind指令</title>
  <style>
    .red {
      color: red;
    }

    .blue {
      color: blue;
    }

    .blod {
      font-weight: 900;
    }
  </style>
</head>
<body>

<div id="app">
  <h1>v-bind指令</h1>

  <div v-bind:class="divClass">通过v-bind进行属性插值</div>
  <div :class="divClass">v-bind可以简写为 :</div>

  <div :class="{red:isRed,blue:isBlue}">v-bind对class属性的对象语法增强</div>
  <div :class="[colorClass,fontClass]">v-bind对class属性的数组语法增强</div>

  <div :style="{color:activeColor,fontWeight:fontWeight}">v-bind对style属性的对象语法增强</div>
  <div :style="[baseStyles, overridingStyles]">v-bind对style属性的数组语法增强</div>
</div>

<script src="js/vue.global.js"></script>
<script>
  const RootComponent = {
    /* 选项 */
    data() {
      return {
        divClass: "red",
        isRed: true,
        isBlue: false,
        colorClass: "red",
        fontClass: "blod",
        activeColor: 'red',
        fontWeight: 900,
        baseStyles: {
          color: "red"
        },
        overridingStyles: {
          fontWeight: 900
        }
      }
    }
  }
  //创建应用，并指定根组件
  const app = Vue.createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```


### v-on

用于监听 DOM 事件

- 格式：
  - `v-on:事件名.修饰符='单个JavaScript 表达式'`
  - `v-bind:[动态事件名].修饰符='单个JavaScript 表达式'`
  - `@事件名.修饰符='单个JavaScript 表达式'`
  - `@[动态事件名].修饰符='单个JavaScript 表达式'`

事件处理程序中可以有`多个方法`，这些方法由逗号运算符分隔。

事件[修饰符](https://v3.cn.vuejs.org/api/directives.html#v-on)：

- stop
- prevent
- capture
- self
- once
- passive

使用修饰符时，顺序很重要；相应的代码会以同样的顺序产生。因此，用v-on:click.prevent.self会阻止所有的点击，而v-on:click.self.prevent只会阻止对元素自身的点击。

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>v-on事件监听</title>
  <style>
    button {
      display: block;
      margin: 10px;
    }
  </style>
</head>
<body>

<div id="app">
  <h1>v-on事件监听</h1>
  <button v-on:click="count++">v-on基本用法，{{count}}</button>
  <button @click="count--">v-on：可以简写为 @ {{count}}</button>

  <button @click="greet">事件处理方法</button>

  <button @click="say('hi')">内联处理器中的方法Say hi</button>
  <button @click="say('what')">内联处理器中的方法Say what</button>

  <button @click="warn('Form cannot be submitted yet.', $event)">内联语句处理器中访问原始的DOM事件</button>
</div>

<script src="js/vue.global.js"></script>
<script>
  const RootComponent = {
    /* 选项 */
    data() {
      return {
        count: 0
      }
    },
    methods: {
      greet(event) {
        // `methods` 内部的 `this` 指向当前活动实例
        alert('Hello ' + this.count + '!')
        // `event` 是原生 DOM event
        if (event) {
          alert(event.target.tagName)
        }
      },
      say(message) {
        alert(message)
      },
      warn(message, event) {
        // 现在可以访问到原生事件
        if (event) {
          event.preventDefault()
        }
        alert(message)
      }
    }
  }
  //创建应用，并指定根组件
  const app = Vue.createApp(RootComponent).mount('#app');
</script>

</body>
</html>
```

### v-model

可以用`v-model`指令在表单`<input>`、`<textarea>`及`<select>`元素上创建双向数据绑定。 它负责监听用户的输入事件来更新数据，并在某种极端场景下进行一些特殊处理。

`v-model`本质上不过是语法糖,相当于`v-bind`+`v-on`

`v-model`在内部为不同的输入元素使用不同的`property`并抛出不同的事件：

- input 和 textarea 元素使用 `value` property 和 `input` 事件；
- checkbox 和 radio 使用 `checked` property 和 `change` 事件；
- select 字段将 value 作为 `prop` 并将 `change` 作为事件。

[示例：](v-model.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>v-model指令</title>
</head>
<body>
<div id="app">
  <h1>v-model指令</h1>

  <h2>input双向绑定:</h2>
  <input v-model="message" placeholder="edit me"/>
  <p>Message is: {{ message }}</p>

  <h2>textarea双向绑定</h2>
  <textarea v-model="message2" placeholder="add multiple lines"></textarea>
  <p style="white-space: pre-line;">Multiline message is:{{ message2 }}</p>

  <h2>复选框双向绑定</h2>
  <div id="v-model-multiple-checkboxes">
    <input type="checkbox" id="jack" value="Jack" v-model="checkedNames"/>
    <label for="jack">Jack</label>
    <input type="checkbox" id="john" value="John" v-model="checkedNames"/>
    <label for="john">John</label>
    <input type="checkbox" id="mike" value="Mike" v-model="checkedNames"/>
    <label for="mike">Mike</label>
    <br/>
    <span>Checked names: {{ checkedNames }}</span>
  </div>

  <h2>单选框双向绑定</h2>
  <div id="v-model-radiobutton">
    <input type="radio" id="one" value="One" v-model="picked"/>
    <label for="one">One</label>
    <br/>
    <input type="radio" id="two" value="Two" v-model="picked"/>
    <label for="two">Two</label>
    <br/>
    <span>Picked: {{ picked }}</span>
  </div>

  <h2>select双向绑定</h2>
  <div id="v-model-select" class="demo">
    <select v-model="selected">
      <option disabled value="">Please select one</option>
      <option>A</option>
      <option>B</option>
      <option>C</option>
    </select>
    <p>Selected: {{ selected }}</p>
  </div>
</div>

<script src="js/vue.global.js"></script>
<script>
  const RootComponent = {
    /* 选项 */
    data() {
      return {
        message: '',
        message2: '',
        checkedNames: [],
        picked: '',
        selected: ''
      }
    }
  }
  //创建应用，并指定根组件
  const app = Vue.createApp(RootComponent).mount('#app');
</script>
</body>
</html>
```

### v-if

`v-if`指令用于`条件性地渲染`一块内容。这块内容只会在指令的表达式返回 truthy 值的时候被渲染。

也可以用`v-else`添加一个`else块`。用`v-else-if`添加一个`else-if块`

因为`v-if`是一个指令，所以必须将它添加到`一个元素上`。但是如果想切换多个元素呢？ 此时可以把一个`<template>`元素当做不可见的包裹元素，并在上面使用`v-if`。 最终渲染结果将不包含`<template>`元素。

### v-show

`v-show`只是简单地切换元素的CSS property display。

**注意**，`v-show`不支持`<template>` 元素，也不支持 `v-else`。

### v-for

基于一个`数组`来渲染一个`列表`

使用`item in items`形式的特殊语法，其中`items`是源数据数组，而`item`则是被迭代的数组元素的别名。

尽可能在使用`v-for`时提供`key`属性,以便Vue能跟踪每个节点的身份,从而重用和重新排序现有元素，你需要为每项提供一个唯一`key`。
不要使用`对象`或`数组`之类的非基本类型值作为 `v-for` 的 `key`。请用`字符串`或`数值类型`的值。

在`v-for`块中，我们可以访问所有父作用域的property

v-for还支持一个可选的第二个参数，即`当前项的索引`。

也可以用 `of` 替代 `in` 作为分隔符，因为它更接近JavaScript迭代器的语法：

除了数组还可以用`v-for`来遍历一个对象的property,可以提供第二个的参数为`property名称`(也就是键名 key)

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

- 计算属性是基于它们的响应依赖关系缓存的,`计算属性`只在相关响应式依赖发生改变时它们才会重新求值。 这意味着绑定的`data`不发生改变，多次访问`计算属性`。会立刻返回计算属性的结构，而不必再次计算函数
- 假设我们有一个性能开销比较大的计算属性 list，它需要遍历一个巨大的数组并做大量的计算。然后我们可能有其他的计算属性依赖于 list。如果没有缓存，我们将不可避免的多次执行 list 的 getter！如果你不希望有缓存，请用
  method 来替代。

计算属性默认只有 `getter`，不过在需要时你也可以提供一个 `setter`：

```javascript
// ...
computed: {
  fullName: {
    // getter
    get()
    {
      return this.firstName + ' ' + this.lastName
    }
  ,
    // setter
    set(newValue)
    {
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

## watch

侦听器：用来响应数据变化

相比于计算属性，侦听器：

- 允许我们执行异步操作
- 限制我们执行该操作的频率
- 在我们得到最终结果前，设置中间状态

# 自定义组件

组件示例：

```javascript
// 创建一个Vue 应用
const app = Vue.createApp({})

// 定义一个名为 button-counter 的新全局组件
app.component('button-counter', {
  data() {
    return {
      count: 0
    }
  },
  template: `
    <button @click="count++">
      You clicked me {{ count }} times.
    </button>`
})
```

在这里演示的是一个简单的示例，但是在典型的 Vue 应用中，我们使用`单文件组件`而不是字符串模板。

单文件组件项目会导致本地jekyll编译变慢。下面单文档组件示例在[vue-demo](https://github.com/guosonglu/vue-demo)项目中

创建单文档组件：

```vue

<template>
  <div>
    <button @click="count++">
      You clicked me {{ count }} times.
    </button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      count: 0
    }
  }
}
</script>

<style scoped>

</style>
```

引入组件，并使用：

- import引入组件
- components属性局部注册组件
- 使用components中注册的组件

```vue

<template>
  <hello-world/>
</template>

<script>
import HelloWorld from '@/views/components/helloworld/HelloWorld.vue'

export default {
  name: "index",
  components: {
    HelloWorld
  }
}
</script>

<style scoped>

</style>
```

## 组件注册

### 全局注册

全局注册的组件可以在应用中的任何组件的模板中使用。

```javascript
const app = Vue.createApp({})
app.component(全局组件)
```

### 局部注册

使用`components`注册

```javascript
const ComponentA = {
  /* ... */
}

const ComponentB = {
  components: {
    'component-a': ComponentA
  }
  // ...
}
```

或者：

```javascript
import ComponentA from './ComponentA.vue'

export default {
  components: {
    ComponentA
  }
  // ...
}
```

## 向自定义组件传递数据

子组件通过`props`接受父组件传来的数据。

`单向下行绑定`:父级`prop`的更新会向下流动到子组件中，但是反过来则不行。这样会防止从子组件意外变更父级组件的状态，从而导致你的应用的数据流向难以理解。

每次父级组件发生变更时，子组件中所有的prop 都将会刷新为最新的值。这意味着你`不应该在一个子组件内部改变prop`。如果你这样做了，Vue会在浏览器的控制台中发出警告。

注意在 JavaScript中`对象`和`数组`是通过引用传入的，所以对于一个数组或对象类型的prop来说，在子组件中改变变更这个对象或数组本身将会影响到父组件的状态。

prop在自定义组件中有下面两个措施防止被改变：

- 这个 prop 用来传递一个初始值；`这个子组件接下来希望将其作为一个本地的prop数据来使用`。在这种情况下，最好`定义一个本地的 data property并将这个prop作为其初始值`：

```javascript
export default {
  props: ['initialCounter'],
  data() {
    return {
      counter: this.initialCounter
    }
  }
}
```

- 这个prop以一种原始的值传入且`需要进行转换`。在这种情况下，最好使用这个 prop 的值来定义一个`计算属性`：

```javascript
export default {
  props: ['size'],
  computed: {
    normalizedSize() {
      return this.size.trim().toLowerCase()
    }
  }
}
```

### prop形式

props可以是字符串数组形式：

```javascript
export default {
  props: ['title', 'likes', 'isPublished', 'commentIds', 'author']
}
```

也可以是对象的形式：

```javascript
export default {
  props: {
    title: String,
    likes: Number,
    isPublished: Boolean,
    commentIds: Array,
    author: Object,
    callback: Function,
    contactsPromise: Promise // 或任何其他构造函数
  }
}
```

prop验证：

```javascript
export default {
  props: {
    // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
    propA: Number,
    // 多个可能的类型
    propB: [String, Number],
    // 必填的字符串
    propC: {
      type: String,
      required: true
    },
    // 带有默认值的数字
    propD: {
      type: Number,
      default: 100
    },
    // 带有默认值的对象
    propE: {
      type: Object,
      // 对象或数组默认值必须从一个工厂函数获取
      default() {
        return {message: 'hello'}
      }
    },
    // 自定义验证函数
    propF: {
      validator(value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'warning', 'danger'].includes(value)
      }
    },
    // 具有默认值的函数
    propG: {
      type: Function,
      // 与对象或数组默认值不同，这不是一个工厂函数 —— 这是一个用作默认值的函数
      default() {
        return 'Default function'
      }
    }
  }
}
```

### 各种类型的参数传递

- 自定义子组件

```vue

<template>
  <div>传入静态字符串：{{ title1 }}</div>
  <div>传入变量字符串：{{ title2 }}</div>

  <div>传入静态数字，父组件仍然需要`v-bind`或`:`来告诉Vue这是一个 JavaScript 表达式而不是一个字符串:{{ likes1 }}</div>
  <div>传入变量数字：{{ likes2 }}</div>

  <div>布尔值不传值默认为：{{ isPublished1 }}</div>
  <div>传入静态布尔值，父组件仍然需要`v-bind`或`:`来告诉Vue这是一个 JavaScript 表达式而不是一个字符串:{{ isPublished2 }}</div>
  <div>传入变量布尔值：{{ isPublished3 }}</div>

  <div>传入静态数组，父组件仍然需要`v-bind`或`:`来告诉Vue这是一个 JavaScript 表达式而不是一个字符串:{{ commentIds1 }}</div>
  <div>传入变量数组：{{ commentIds2 }}</div>

  <div>传入静态对象，父组件仍然需要`v-bind`或`:`来告诉Vue这是一个 JavaScript 表达式而不是一个字符串:{{ author1 }}</div>
  <div>传入变量对象：{{ author2 }}</div>
  <div>传入对象的所有property:{{name}} {{company}}</div>
</template>

<script>
export default {
  name: "child",
  props: {
    title1: String,
    title2: String,
    likes1: Number,
    likes2: Number,
    isPublished1: Boolean,
    isPublished2: Boolean,
    isPublished3: Boolean,
    commentIds1: Array,
    commentIds2: Array,
    author1: Object,
    author2: Object,
    name: String,
    company: String
  }
}
</script>

<style scoped>

</style>
```

- 父组件调用

```vue

<template>
  <child
          title1="静态字符串"
          :title2="title"

          :likes1="10"
          :likes2="like"

          is-published1
          :is-published2="true"
          :is-published3="isPublished"

          :comment-ids1="[234, 266, 273]"
          :comment-ids2="commentIds"

          :author1="{name: 'Veronica',company: 'Veridian Dynamics'}"
          :author2="author"
          :="author"


  />
</template>

<script>
import Child from "./child"

export default {
  name: "parent",
  components: {
    Child
  },
  data() {
    return {
      title: "变量字符串",
      like: 20,
      isPublished: false,
      commentIds: [10, 20, 30],
      author: {
        name: "张三",
        company: "alibaba"
      }
    }
  }
}
</script>

<style scoped>

</style>
```

### prop命名规范

props是`驼峰命名法`，父组件调用时使用`短横线分隔命名`（因为html是大小写不敏感的，会把大写转成小写）

### 非Prop的Attribute

非`props`的`Attribute`,将自动添加到子组件根节点的attribute中。

如果子组件具有多个根节点，显式绑定`$attrs`,如下：

```html
<custom-layout id="custom-layout" @click="changeValue"></custom-layout>
```

```javascript
app.component('custom-layout', {
  template: `
    <header>...</header>
    <main v-bind="$attrs">...</main>
    <footer>...</footer>
  `
})
```

## 父组件监听子组件事件

步骤：

- 调用内建的`$emit`方法并传入事件名称来触发一个事件
- 父组件通过`v-on`或`@`捕获事件

## 通过插槽分发内容

子组件通过`slot`接受父组件传来的内容

## 动态组件


