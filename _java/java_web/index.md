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

- `方式一`：直接将项目放到webapps目录下，或将项目打成war包放在webapp目录下
- `方式二`：在server.xml配置文件中配置`项目存放路径`与`虚拟目录`映射关系

```xml
<Server port="8005" shutdown="SHUTDOWN">
  <Service name="Catalina">
    <Engine name="Catalina" defaultHost="localhost">
      <Host name="localhost"  appBase="webapps" unpackWARs="true" autoDeploy="true">
        <!--配置映射关系-->
        <Context path="虚拟目录" docBase="项目路径"/>
      </Host>
    </Engine>
  </Service>
</Server>
```

- `方式三`：在`/conf/Catalina/localhost`目录下创建任意名称的xml文件，在其中指定项目路径。虚拟目录为xml文件名

```xml
<Context docBase="项目路径"/>
```



# 创建web项目

## idea集成Tomcat

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625181254.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625181459.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625181540.png)

## Java动态项目目录结构

- 项目根目录
  - WEB-INF目录
    - web.xml:web项目核心配置文件
    - classes目录：放置字节码文件
    - lib目录：放置依赖jar包

## 创建javaweb项目

- 首先创建一个普通的`maven项目`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625182124.png)

- 为`maven`项目添加`web框架`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625182314.png)

- 在新增的web目录下创建`index.jsp`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625182606.png)

- 基于模块创建工件

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625183000.png)

- 新建tomcat配置，并添加工件

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625183223.png)

- 指定工件后可以修改访问的虚拟目录

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625184434.png)

- 启动tomcat运行

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625183436.png)

一些细节：当前maven项目并没有依赖除jdk之外的其它库，之所以项目能将jsp转为html展示给浏览器，依赖的是tomcat服务器

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625184805.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625184943.png)


## 项目细节

- 热部署配置

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625185451.png)

- idea会为每个tomcat配置定制独立的配置文件

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625185903.png)

- `工件`会存放到项目根路径下的out目录下

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220625190254.png)

- `WEB-INF目录`下的内容是不能被浏览器直接访问的

# Servlet

## 概述

`Servlet（server applet）`:运行在服务端的小程序

为了达到动态资源的效果，我们需要用Java类写一些逻辑达到动态资源的效果。

但随便写一个类Tomcat是不认识的，需要实现统一的接口才行，这个接口就是
