---
layout: docs
title: Html
nav_order: 10
---

# 概述

[Html](https://zh.wikipedia.org/wiki/HTML)(`超文本标记语言，Hyper Text Markup Language`)是一种用于创建网页的标准标记语言.

# Html标签

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

告诉浏览器关于web页面的信息

### title标签

浏览器窗口的标题

## body标签

页面主体
