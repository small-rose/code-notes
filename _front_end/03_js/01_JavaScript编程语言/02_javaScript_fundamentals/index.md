---
layout: default
title: JavaScript基础知识
nav_order: 20
parent: JavaScript编程语言
grand_parent: javascript
---

# use strict

`ES5`规范增加了新的语言特性并且修改了一些已经存在的特性。为了保证旧的功能能够使用，大部分的修改是默认不生效的。你需要一个特殊的指令`use strict`来明确地激活这些特性。

现代JavaScript支持`class`和`module`—— 高级语言结构，它们会自动启用`use strict`。因此，如果我们使用它们，则无需添加`use strict`指令。

```javascript
"use strict";

// your code here
```

```javascript
function myFunction() {
  "use strict";
  // your code here
}
```

# 变量

{: .note-title}
> 命名规范
> 
> - 变量名称必须仅包含字母、数字、符号 $ 和 _。
> - 首字符必须非数字。

{: .warning-title}
> let和var的区别
> 
> - 作用域：var是`函数作用域`，let是`块级作用域`
> - 变量提升：var声明的变量在声明前即可使用，let需要先声明再使用
> - 变量声明：var可以重复声明变量，let不可以

