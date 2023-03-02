---
layout: default
title: DOM
nav_order: 10
parent: WebApi
grand_parent: javascript
---

# 简介

`DOM（文档对象模型）`是JavaScript操作HTML和XML文档的API。通过DOM，JavaScript可以对文档中的任何元素进行访问、操作和修改，例如添加、删除或修改页面的文本、样式、结构和事件处理程序。

`DOM`将文档表示为一个`树形结构`，称为`节点树`，其中每个节点代表文档中的一个元素、属性或文本片段。树的根节点称为`文档节点`，它是整个文档的`父节点`。文档节点下的子节点包括HTML元素节点、文本节点和注释节点等。

JavaScript可以使用DOM API查询和访问文档中的节点，例如`getElementById()`，`getElementsByTagName()`和`getElementsByClassName()`等方法。通过这些方法可以获取元素的属性和内容，并且可以动态地修改元素的属性和内容，从而实现动态的网页效果。

除此之外，JavaScript还可以在DOM节点上添加`事件处理程序`，例如`click`、`mouseover`和`keydown`等事件，以响应用户的交互操作。在事件处理程序中，可以访问事件对象，从而获取有关事件的详细信息，例如点击的元素、按下的键以及鼠标的位置等。

总的来说，DOM是JavaScript的核心API之一，它使JavaScript能够与`HTML和XML文档进行交互`，并且可以让开发者创建丰富的交互式和动态的`Web应用程序`。

# 获取元素

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="div1" class="text">hello</div>
<div id="div2" class="text">world</div>
<script>
    // 根据Id获取元素
    let div1 = document.getElementById("div1");
    // 根据标签名获取元素的集合
    let divs1 = document.getElementsByTagName("div");
    // 根据类名获取元素的集合
    let divs2 = document.getElementsByClassName("text");
    //获取符合条件的第一个元素
    let div2 = document.querySelector(".text");
    let div3 = document.querySelector("div");
    let div4 = document.querySelector("#div2");
    //获取符合条件的所有元素对象
    let divs3 = document.querySelectorAll("div");
    //获取body元素
    document.body;
    //获取html元素对象
    document.documentElement
</script>
</body>
</html>
```

# 事件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<button id="btn">测试事件</button>
<script>
    //获取事件源
    let btn = document.querySelector("#btn");
    // 事件类型+事件处理程序
    btn.onclick=function () {
        alert("hello");
    }
</script>
</body>
</html>
```

# 操作元素
