---
layout: default
title: JavaWeb
nav_order: 100
---

# JavaEE Web服务器软件

## 概述

Web服务器一般指网站服务器，是指驻留于因特网上某种类型计算机的程序，可以处理浏览器等Web客户端的请求并返回相应响应，也可以放置网站文件，让全世界浏览；可以放置数据文件，让全世界下载。

`JavaEE Web服务器软件`是支持`JavaEE规范`的Web服务器软件

## 种类

JavaEE Web服务器软件：

- `oracle`的`webLogic`:大型的JavaEE服务器，支持所有JavaEE规范
- `IBM`的`webSphere`:大型的JavaEE服务器，支持所有JavaEE规范
- `JBOSS`:大型的JavaEE服务器，支持所有JavaEE规范
- `Apache Software Foundation` 的`Tomcat`:中小型JavaEE服务器，开源免费，仅支持少量JavaEE规范

其它Web服务器软件（非JavaEE）：

- `Apache Software Foundation` 的 `Apache HTTP Server`
- `Microsoft Corporation` 的 `Internet Information Server (IIS)`
- `Google LLC（Alphabet Inc. 子公司）`的 `Google Web Server`
- `NGINX, Inc.`（已被 `F5 Networks, Inc.` 收购）的 `NGINX`
  - `淘宝网（隶属于阿里巴巴集团）`改良自 `NGINX` 的 `Tengine`
- `lighttpd`
- `Cherokee`
- `Microsoft Corporation 的 FrontPage`

# Tomcat

## 下载安装

需要先安装`Java运行环境`

[Tomcat官网](https://tomcat.apache.org/)

- 官网下载压缩包
- 解压即可（解压目录建议不要有中文）

## 启动Tomcat

点击`startup.bat`即可启动，通过`127.0.0.1:8080`访问Tomcat主页

- 可能问题
  - 黑窗口一闪而过：没有配置JAVA_HOME环境变量
  - 启动报错：可能是端口被占用

## 项目部署方式

- 方式一：

