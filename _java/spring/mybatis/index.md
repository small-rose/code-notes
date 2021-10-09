---
layout: docs
title: MyBatis
nav_order: 1000
parent: Spring相关
---

# 概述

功能：访问数据库

**_特点：_**
- 不屏蔽SQL
- 提供强大灵活的映射机制
- 提供了使用Mapper的接口编程

案例版本：`3.5.7`

# MyBatis核心组件

- `SqlSessionFactoryBuilder`(构造器)：它会根据配置或者代码来生成SqlSessionFactory，采用分步构建的Builder模式。
- `SqlSessionFactory`(工厂接口)：依靠它来生成SqlSession，使用的是`工厂模式`
- `SqlSession`(会话)
- `SQL Mapper`(映射器)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091642870.png)

# SqlSessionFactory

每一个MyBatis应用都是以一个`SqlSessionFactory`的实例为中心的。

`SqlSessionFactory`的唯一作用：生产`MyBatis`的核心接口对象`SqlSession`

**`SqlSessionFactory`是一个接口，它有两个实现类：**
- `DefaultSqlSessionFactor`
- `SqlSessionManager`:使用在多线程环境中，具体实现依靠`DefaultSqlSessionFactor`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091650091.png)

## 使用XML构建SqlSessionFactory


