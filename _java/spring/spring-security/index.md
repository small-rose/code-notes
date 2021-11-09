---
layout: docs
title: Spring Security
nav_order: 400
parent: Spring相关
---
# 概述

基于Spring Boot：`2.3.2.RELEASE`
Spring Security 版本：`5.3.3.RELEASE`

Spring Security的版本并不需要亲自指定，[父工程](#父工程)继承自`spring-boot-starter-parent`，`spring-boot-starter-parent`中已经帮我们做好了版本管理

[官方文档](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/)

[中文文档](http://docs.jcohy.com/docs/spring-security/5.3.0.RELEASE/html5/zh-cn)

参考书：[深入浅出Spring Security](https://book.douban.com/subject/35383505/)

视频教程：[尚硅谷SpringSecurity框架教程](https://www.bilibili.com/video/BV15a411A7kP?from=search&seid=17934619456497753665&spm_id_from=333.337.0.0)

# 过滤器

在Spring Security中，认证、授权等功能都是基于过滤器来完成的。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081800610.png)

过滤器并不是直接放在Web项目的原生过滤器链中，而是通过一个`FilterChainProxy`来统一管理。
Spring Security中的过滤器链通过`FilterChainProxy`嵌入到Web项目的原生过滤器链中。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081801770.png)

在Spring Security中，这样的过滤器链可能有多个。当存在多个过滤器链时，多个过滤器链之间要指定优先级，
当请求到达后，会从`FilterChainProxy`进行分发，先和哪个过滤器链匹配上，就用哪个过滤器链进行处理。
当系统中存在多个不同的认证体系时，那么使用多个过滤器链就非常有效。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081805124.png)

`FilterChainProxy`通过Spring框架提供的`DelegatingFilterProxy`整合到原生过滤器链中。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081806459.png)

# 认证

## 快速入门

- 创建名为`spring-security-hello`的Spring Boot项目

- pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <version>2.3.2.RELEASE</version>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>spring-security-hello</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--spring security-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

</project>
```

- Spring Boot启动类

```java
package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luguosong
 * @date 2021/11/9 9:30
 */
@SpringBootApplication
public class SpringSecurityHello {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityHello.class, args);
    }
}
```

- Controller

```java
package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 * @date 2021/11/9 9:31
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
```

- 运行项目，访问`/hello`,系统会跳转到登录认证界面

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090936106.png)

- 默认用户名为`user`,密码在控制台打印

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090942678.png)

- 使用用户名密码登录成功后可以访问'/hello'

## 快速入门分析

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090944966.png)

- 过程分析：
  1. 客户端（浏览器）发起请求去访问`/hello`接口，这个接口默认是需要认证之后才能访问的
  2. 请求经过Spring Security的过滤器链，在`FilterSecurityInterceptor`过滤器中被拦截下来，
  因为系统发现用户未认证。接下来抛出`AccessDeniedException`异常。
  3. `ExceptionTranslationFilter`过滤器捕获`AccessDeniedException`异常，
  并调用`LoginUrlAuthenticationEntryPoint#commence`方法给客户端返回`302`，
  要求客户端重定向到`/login`页面。
  4. 客户端发送`/login`请求,`DefaultLoginPageGeneratingFilter`过滤器拦截请求，返回`登录界面`


- 过程中Spring Security背后默默做了什么：
  - 创建一个名为`springSecurityFilterChain`的过滤器，并注入到`Spring容器`中，这个过滤器将负责所有的安全管理，
  包括用户的认证、授权、重定向到登录页面等（`springSecurityFilterChain`实际上代理了Spring Security中的过滤器链）。
  - 创建UserDetailsService实例，负责提供用户数据。
  - 给用户生成一个默认的登录页面
  - 开启CSRF攻击防御
  - 开启会话固定攻击防御
  - 集成X-XSS-Protection
  - 集成X-Frame-Options以防止单击劫持

# 授权
