---
layout: docs 
title: Html 
nav_order: 10
---

# 概述

[Html](https://zh.wikipedia.org/wiki/HTML)(`超文本标记语言，Hyper Text Markup Language`)是一种用于创建网页的标准`标记语言`.

参考书籍：[HTML5权威指南](https://book.douban.com/subject/25786074/)

# Html文件结构

最基本的html文件结构：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

</body>
</html>
```

## !DOCTYPE声明

用来告知 Web 浏览器页面使用了哪种 HTML 版本。

- HTML 5

```html
<!DOCTYPE html>
```

- HTML 4.01 Strict

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
```

- HTML 4.01 Transitional

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
```

- HTML 4.01 Frameset

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
```

- XHTML 1.0 Strict

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
```

- XHTML 1.0 Transitional

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
```

- XHTML 1.0 Frameset

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
```

- XHTML 1.1

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
```

## html标签

告诉浏览器这是一个html文件，不加这个标签的话，有些浏览器可以接受，但有些浏览器是不允许的

## head标签

`元数据`告诉浏览器关于web页面的信息

### title标签

浏览器窗口的标题

## body标签

页面主体

# 全局属性

全局属性可以用在任何一个元素上

## accesskey属性

设置用来选择页面上元素的快捷键,Windows中按下`Alt键`+`accesskey属性`值可以快速访问元素

```html

<form>
    Name:<input type="text" name="name" accesskey="n">
    <p/>
    Password:<input type="password" name="password" accesskey="p">
    <p/>
    <input type="submit" value="Login" accesskey="s">
</form>
```

效果：

***

<form>
    Name:<input type="text" name="name" accesskey="n">
    <p/>
    Password:<input type="password" name="password" accesskey="p">
    <p/>
    <input type="submit" value="Login" accesskey="s">
</form>

***

## class属性

用来将元素归类

### CSS利用class属性

```html
<style>
    .class-2 {
        background-color: grey;
        color: white;
        padding: 5px;
        margin: 2px;
    }
    .class-1 {
        font-size: x-large;
    }
</style>

<a class="class-1 class-2" href="https://www.baidu.com/">百度</a>
<p/>
<a class="class-2" href="https://book.douban.com/">豆瓣读书</a>
```

效果：

***

<style>
    .class-2 {
        background-color: grey;
        color: white;
        padding: 5px;
        margin: 2px;
    }
    .class-1 {
        font-size: x-large;
    }
</style>
<a class="class-1 class-2" href="https://www.baidu.com/">百度</a>
<p/>
<a class="class-2" href="https://book.douban.com/">豆瓣读书</a>

***

### 在脚本中使用class属性

```html
<a href="https://www.baidu.com/">百度</a>
<p/>
<a class="class-3" href="https://book.douban.com/">豆瓣读书</a>

<script>
    const elems = document.getElementsByClassName("class-3");
    for (let i = 0; i < elems.length; i++) {
        const x = elems[i];
        x.style.border = "thin solid black";
        x.style.backgroundColor = "white";
        x.style.color = "black";
    }
</script>
```

效果：

***
<a href="https://www.baidu.com/">百度</a>
<p/>
<a class="class-3" href="https://book.douban.com/">豆瓣读书</a>

<script>
    const elems = document.getElementsByClassName("class-3");
    for (let i = 0; i < elems.length; i++) {
        const x = elems[i];
        x.style.border = "thin solid black";
        x.style.backgroundColor = "white";
        x.style.color = "black";
    }
</script>
***

## contenteditable属性

让用户能修改页面上的内容

```html
<p contenteditable="true">这段文字可以编辑</p>
```

效果：

***

<p contenteditable="true">这段文字可以编辑</p>

***

## dir属性

文字方向

```html
<p dir="rtl">This is right-to-left</p>
<p dir="ltr">This is left-to-right</p>
```

效果：

***

<p dir="rtl">This is right-to-left</p>
<p dir="ltr">This is left-to-right</p>

***


