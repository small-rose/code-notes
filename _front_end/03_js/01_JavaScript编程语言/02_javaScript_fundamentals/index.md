---
layout: default
title: JavaScript基础知识
nav_order: 20
parent: JavaScript编程语言
grand_parent: javascript
latex: true
---

# use strict

`ES5`
规范增加了新的语言特性并且修改了一些已经存在的特性。为了保证旧的功能能够使用，大部分的修改是默认不生效的。你需要一个特殊的指令`use strict`
来明确地激活这些特性。

现代JavaScript支持`class`和`module`—— 高级语言结构，它们会自动启用`use strict`
。因此，如果我们使用它们，则无需添加`use strict`指令。

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

# 数据类型

javascript是`动态类型（dynamically typed）`的编程语言。虽然编程语言中有不同的数据类型，但是你定义的变量并不会在定义后，被限制为某一数据类型。

## Number类型

`Number类型`可以分为整数和浮点数

`Infinity`表示无穷大

`NaN`代表一个计算错误

## BigInt类型

在 JavaScript 中，`number`类型无法安全地表示大于 ($2^{53}$-1)（即 9007199254740991），或小于 -($2^{53}$-1) 的整数。

通过将`n`附加到整数字段的末尾来创建`BigInt值`

## String类型

包含字符串的三种方式：

- 双引号：\""Hello\"
- 单引号：\'Hello\'
- 反引号：\`Hello\`

`反引号`是功能扩展引号。它们允许我们通过将变量和表达式包装在`${…}`中，来将它们嵌入到字符串中。

## Boolean类型（逻辑类型）

boolean类型仅包含两个值：`true` 和 `false`。

## null值

JavaScript中的null不是一个`对不存在的object的引用`或者`null指针`。

JavaScript 中的null仅仅是一个代表`无`、`空`或`值未知`的特殊值。

## undefined值

如果一个变量已被声明，但`未被赋值`，那么它的值就是`undefined`

## Object类型

object用于储存`数据集合`和`更复杂的实体`。

## Symbol类型

symbol类型用于创建对象的`唯一标识符`。

# 类型转换

## 字符串转换

```javascript
// 字符串转换
let value = true;
console.log(typeof value);
value=String(value);
console.log(typeof value);
```

```shell
boolean
string
```

## 数字类型转换

```javascript
alert( "6" / "2" ); // 3, string 类型的值被自动转换成 number 类型后进行计算
```

```javascript
// 数字类型转换
let str = "123";
console.log(typeof str); // string
let num = Number(str); // 变成 number 类型 123
console.log(typeof num); // number
```



# typeof运算符

typeof运算符返回`参数的类型`。

# 操作符

# 控制流

# 数组

# 函数
